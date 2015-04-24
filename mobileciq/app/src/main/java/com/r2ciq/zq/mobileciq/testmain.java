package com.r2ciq.zq.mobileciq;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * Created by gibson_wong on 4/24/2015.
 */
public class testmain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        LinearLayout ll = new LinearLayout(this);
        setContentView(R.layout.kektest);
        StudioSelectorLayout ssl = (StudioSelectorLayout) this.findViewById(R.id.mainmenu);

        ssl.setViewStyles();
        //ll.addView(ssl);
        //setContentView( ll );

        //LayoutInflater.inflate(R.layout.activity_home_page, );
    }
}
