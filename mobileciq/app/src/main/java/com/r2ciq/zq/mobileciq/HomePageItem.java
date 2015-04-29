package com.r2ciq.zq.mobileciq;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomePageItem extends RelativeLayout{
    private int mSize;
    private String name;

    public HomePageItem(Context context){
        super (context);
        init(context);
    }

    public void init (Context context){
        LayoutInflater.from(context).inflate(R.layout.home_page_item, this);
    }

    public void setIcon (int iconId){
        ImageView iv = (ImageView) this.findViewById(R.id.home_page_icon);
        iv.setImageResource(iconId);
        this.invalidate();
    }

    public void setName (String name){
        TextView tv = (TextView) this.findViewById(R.id.home_page_label);
        tv.setText(name);
        this.name = name;
        this.invalidate();
    }

    public String getName (){ return this.name;}

    public void setStyleParams () {
        setLayoutDimensions();
        //formatInnerViews();
        this.setGravity(Gravity.CENTER);
        invalidate();
    }

    public int getmSize () {
        return mSize;
    }

    private void setLayoutDimensions(){
        int imageWidth = measureImage();
        mSize = imageWidth + 200;
        this.setLayoutParams(new LayoutParams(mSize
                                              ,LayoutParams.MATCH_PARENT));
    }

    private int measureImage(){
        ImageView iv = (ImageView) this.findViewById(R.id.home_page_icon);
        return iv.getDrawable().getIntrinsicWidth();
    }

    @Deprecated
    private void formatInnerViews (){
        TextView tv = (TextView) this.findViewById(R.id.home_page_label);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, R.id.home_page_icon);
        tv.setLayoutParams(lp);
    }

    //what is reflection
    @Deprecated
    private View findView (Class viewClass){
        View v = null;
        int viewCount = this.getChildCount();

        for (int i = 0; i <= viewCount; i++) {
             v = this.getChildAt(i);
            if (viewClass.isInstance(v)) {
                return v;
            }
        }
        return null;
    }
}
