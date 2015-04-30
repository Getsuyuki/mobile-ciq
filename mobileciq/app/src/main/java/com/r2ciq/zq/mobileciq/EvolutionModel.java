package com.r2ciq.zq.mobileciq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by David on 30/04/2015.
 */
public class EvolutionModel extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evo_model);
        //le pull intent data

    }

    private void retrieveItems (){
        //le implement
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // set Menu Name :) =>
        getMenuInflater().inflate(R.menu.menu_item_page, menu);
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
            case R.id.save_studioItem:
                saveRFItem();
                return true;
            case R.id.go_home:
                goHome();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveRFItem() {
        //JSSSSOONNNNN TIMEEEEEEE
    }

    private void goHome() {
        Intent i = new Intent(EvolutionModel.this, HomePage.class);
        startActivity(i);
        finish();
    }
}

