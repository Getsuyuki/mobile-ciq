package com.r2ciq.zq.mobileciq;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class StudioSelectorLayout extends HorizontalScrollView
{
    private static final int SWIPE_MIN_DISTANCE = 5;
    private static final int SWIPE_THRESHOLD_VELOCITY = 300;

    private ArrayList<RelativeLayout> mStudios = null;
    private GestureDetector mGestureDetector;
    private int selectedStudio = 0;
    private int studioWidth;

    public StudioSelectorLayout(Context context) {
        super(context);
    }

    public StudioSelectorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public StudioSelectorLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public void init (Context context, AttributeSet attrs){
        TypedArray a = context.getTheme().obtainStyledAttributes(
                        attrs,
                        R.styleable.StudioSelectorLayout,0,0);
        Resources res = getResources();

        createStudioItems(res.getIntArray(
                            a.getResourceId(R.styleable.StudioSelectorLayout_icons, 0)),
                          res.getStringArray(
                            a.getResourceId(R.styleable.StudioSelectorLayout_names, 0)));
        setStudioItems(mStudios);

        setOnTouchListener(new OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mGestureDetector.onTouchEvent(event)) {
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP ||
                         event.getAction() == MotionEvent.ACTION_CANCEL){
                    onSwipe(v);
                    return true;
                }
                else{
                    return false;
                }
            }
        });
    }

    public void createStudioItems (int[] icons, String[] names){
        ArrayList<RelativeLayout> items = null;
        int iconId;
        String name;

        for (int i = 0; i<icons.length; i++){
            iconId = icons[i];
            name = names[i];
            items.add(configureStudioItem(iconId, name));
        }
        this.mStudios = items;
    }

    public HomePageItem configureStudioItem(int iconId, String name){
        HomePageItem studio = new HomePageItem(getContext());
        studio.setIcon(iconId);
        studio.setName(name);
        studio.setStyleParams();

        return studio;
    }

    public void setStudioItems (ArrayList<RelativeLayout> items) {
        LinearLayout studioItemsWrapper = new LinearLayout(getContext());
        studioItemsWrapper.setLayoutParams(new ViewGroup.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT
        ));
        studioItemsWrapper.setOrientation(LinearLayout.HORIZONTAL);

        this.addView(studioItemsWrapper);

        for (int i = 0; i < items.size(); i++) {
            studioItemsWrapper.addView(items.get(i));
        }
    }

    //http://www.velir.com/blog/index.php/2010/11/17/android-snapping-horizontal-scroll/

    private boolean onSwipe (View view){
        int scrollX = getScrollX();
        selectedStudio = ((scrollX + (studioWidth/2))/studioWidth);
        int scrollTo = selectedStudio*studioWidth;
        smoothScrollTo(scrollTo, 0);
        return true;
    }

    class MGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
           try{
               if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                   selectedStudio = (selectedStudio < (mStudios.size() - 1))? selectedStudio + 1:mStudios.size() -1;
                   smoothScrollTo(selectedStudio*studioWidth, 0);
                   return true;
               }
               //left to right
               else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                   selectedStudio = (selectedStudio > 0)? selectedStudio - 1:0;
                   smoothScrollTo(selectedStudio*studioWidth, 0);
                   return true;
               }
           }
           catch (Exception e){
               Log.e("Fling", "There was an error processing the Fling event:" + e.getMessage());
           }

            return false;
        }
    }

}
