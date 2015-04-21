package com.r2ciq.zq.mobileciq;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomePageItem extends RelativeLayout{
    public HomePageItem(Context context){
        super (context);
        init(context);
    }

    public void init (Context context){
        LayoutInflater.from(context).inflate(R.layout.home_page_item, this);
    }

    public void setIcon (int iconId){
        ImageView iv = (ImageView) findView(ImageView.class);
        iv.setImageResource(iconId);
    }

    public void setName (String name){
        TextView tv = (TextView) findView(TextView.class);
        tv.setText(name);
    }

    //TODO: any others things? maybe drawing?

    //what is reflection
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

