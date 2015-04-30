package com.r2ciq.zq.mobileciq;

import android.graphics.drawable.Drawable;

/**
 * Created by gibson_wong on 4/29/2015.
 */
public class SpaceItem {
    public Drawable spaceItemIcon;
    public String spaceItemName;
    public String spaceItemDescription;
    public SpaceItem (Drawable ico, String name, String d){
        spaceItemDescription = d;
        spaceItemIcon = ico;
        spaceItemName = name;
    }
}
