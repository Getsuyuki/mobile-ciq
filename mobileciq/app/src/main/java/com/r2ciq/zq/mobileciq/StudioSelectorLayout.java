package com.r2ciq.zq.mobileciq;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class StudioSelectorLayout extends HorizontalScrollView
{
    private static final int SWIPE_MIN_DISTANCE = 5;
    private static final int SWIPE_THRESHOLD_VELOCITY = 300;

    private ArrayList<HomePageStudioItem> mStudios = null;
    private GestureDetector mGestureDetector;
    private int selectedStudio = 0;

    public StudioSelectorLayout(Context context) {
        super(context);
    }

    public StudioSelectorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StudioSelectorLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void createStudioItems (TypedArray icons, String[] names){
        ArrayList<HomePageStudioItem> items = null;
        Drawable icon = null;
        String name = null;
        //for loop to go through values and create each of the custom views
            configureStudioItem(icon, name);

        this.mStudios = items;
    }

    public void configureStudioItem(Drawable icon, String name){
        //create and configure each item (le format)
    }

    public void setStudioItems (ArrayList<HomePageStudioItem> items) {
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
    private void onSwipe (){
        //code for swipe
    }

    class MGestureDetector extends GestureDetector.SimpleOnGestureListener{
        //@Override
        public boolean onFling(){
            return true;
        }
    }

}
