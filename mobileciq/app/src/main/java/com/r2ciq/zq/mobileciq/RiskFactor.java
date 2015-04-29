package com.r2ciq.zq.mobileciq;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by gibson_wong on 4/24/2015.
 */
//http://echo.jsontest.com/item/kek
public class RiskFactor extends ActionBarActivity{
    private ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfstudio);

        getRFItems();

        //getRFItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.add_studioItem:
                addRFItem();
                return true;
            case R.id.go_home:
                goHome();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goHome() {
        Intent i = new Intent(RiskFactor.this, HomePage.class);
        startActivity(i);
        finish();
    }

    private void addRFItem() {
        Log.w("kek","notimplemented");
    }

    private void getRFItems(){
        //SendRequest req = new SendRequest();
        //Pa

    }
}
