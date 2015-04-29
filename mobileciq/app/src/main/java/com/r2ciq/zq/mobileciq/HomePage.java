package com.r2ciq.zq.mobileciq;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;


public class HomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        StudioSelectorLayout menuBar = (StudioSelectorLayout)findViewById(R.id.mainmenu);

        menuBar.setViewStyles();

        //LayoutInflater.inflate(R.layout.activity_home_page, );
    }
    /*todo: implement activity starter for risk factor
      todo: implement list from parsded JSON (GET / Parse)
      todo: display simple view for another parsed data
      todo: allow input into serverside (POST)
      todo: implement serverside
      todo: find out how that works
    */
}
