package com.r2ciq.zq.mobileciq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gibson_wong on 4/29/2015.
 */
public class SpaceAdapter extends ArrayAdapter<SpaceItem>{
    public SpaceAdapter(Context context, int resource, ArrayList<SpaceItem> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.space_item, null);
        }

        SpaceItem item = getItem(position);

        ImageView icon = (ImageView) v.findViewById(R.id.itemicon);
        TextView name = (TextView) v.findViewById(R.id.itemtopLine);
        TextView prop = (TextView) v.findViewById(R.id.itembotLine);

        if (item != null){
            icon.setImageDrawable(item.spaceItemIcon);
            name.setText(item.spaceItemName);
            prop.setText(item.spaceItemDescription);
        }
        else {
            icon.setImageDrawable(getContext().getResources()
                                              .getDrawable(android.R.drawable.btn_star, null));
            name.setText("Default");
            name.setText("kek");
        }

        return v;
    }
}
