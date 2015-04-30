package com.r2ciq.zq.mobileciq;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.JsonArray;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by gibson_wong on 4/24/2015.
 */
//http://echo.jsontest.com/item/kek
public class RiskFactor extends ActionBarActivity{
    private ArrayList<SpaceItem> items;
    private SpaceAdapter itemsAdp;

    private ListView spaceView;
    private ImageView spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfstudio);

        items = new ArrayList<SpaceItem>();
        spaceView = (ListView)this.findViewById(R.id.rfSpaceView);
        spinner = (ImageView) this.findViewById(R.id.spinner);

        itemsAdp = new SpaceAdapter(this,R.layout.space_item,items);
        spaceView.setAdapter(itemsAdp);
        getRFItems();
    }

    private void getRFItems(){
        showSpinner();
        sendRequest("http://10.185.16.140/AppServices/api/RiskFactor/RetrieveSpaces");
        //sendRequest("http://10.185.16.140/AppServices/api/Login/AuthenticateX");
    }

    private void showSpinner() {
        spinner.setVisibility(View.VISIBLE);
        AnimationDrawable spinDrawable = (AnimationDrawable) spinner.getDrawable();
        // Start the animation (looped playback by default).
        spinDrawable.start();
    }

    private void revert() {
        AnimationDrawable spinDrawable = (AnimationDrawable) spinner.getDrawable();
        // Start the animation (looped playback by default).
        spinDrawable.stop();
        spinner.setVisibility(View.GONE);
        spaceView.setVisibility(View.VISIBLE);
    }

    private JSONArray parseToJSON (Response response) throws JSONException, IOException {
        JSONArray itemsJson = null;
        itemsJson = new JSONArray(response.body().string());
        return itemsJson;
    }

    private void loadItems(JSONArray jsonObject) throws JSONException {
        //this part is sketchy, but can be altered to properly set according to item type
        //or based on a more complex json parsing function (that can be abstracted and reused)
        //to parse more complex JSON arrays

        for (int i = 0; i<jsonObject.length(); i++) {

            SpaceItem newItem = new SpaceItem(getDrawable(R.drawable.evoico),jsonObject.getString(i), "Evolution");

            items.add(newItem);
        }
    }


    private void sendRequest(String url) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout

        Request request = new Request.Builder()
                .url(url)
                //.addHeader("Accept", "application/json")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.w("kek", e);
                revert();
            }


            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    revert();
                    return;
                }
                try {
                    loadItems(parseToJSON(response));
                    updateChanges();
                } catch (JSONException e) {
                    e.printStackTrace();
                    revert();
                }
            }
        });
    }

    private void updateChanges() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                itemsAdp.notifyDataSetChanged();
                revert();
            }
        });
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
}
