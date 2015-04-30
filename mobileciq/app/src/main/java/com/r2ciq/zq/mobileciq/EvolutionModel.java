package com.r2ciq.zq.mobileciq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by David on 30/04/2015.
 * this class does NOT validate input, but its should
 */
public class EvolutionModel extends ActionBarActivity {
    public class RequestData{
        public JSONObject data;
        public String url;
    }
    private String Id;
    private String Name;

    private EditText rfs;
    private EditText horizon;
    private EditText estimationmethod;
    private EditText decayfactor;
    private EditText timewindow;
    private EditText mda;

    private final RequestData rd = new RequestData();
    private StudioRequestServices ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evo_model);

        rfs = (EditText) findViewById(R.id.rfsTB);
        horizon = (EditText) findViewById(R.id.hTB);
        estimationmethod = (EditText) findViewById(R.id.emTB);
        decayfactor = (EditText) findViewById(R.id.dfTB);
        timewindow = (EditText) findViewById(R.id.twTB);
        mda = (EditText) findViewById(R.id.mdaTB);

        Id = getIntent().getStringExtra("ID");
        Name = getIntent().getStringExtra("NAME");

        ss = new StudioRequestServices();

        retrieveItem();
    }

    private void retrieveItem (){
        boolean isAdd = getIntent().getBooleanExtra("ADD", false);

        if (!isAdd) {
            rd.url = buildReqChild();
            new Thread (new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONRetrievedItem(ss.getSpaceChild(rd.url));
                        displayRetrievedItem();
                    } catch (IOException e) {
                        Log.w ("kek","get failed");
                    } catch (JSONException e) {
                        Log.w ("kekJSON",e);
                    }
                }
            }).start();
        }
    }

    private void JSONRetrievedItem(Response spaceChild) throws IOException, JSONException {
        JSONObject response = new JSONObject (spaceChild.body().string());
        rd.data = response;
    }

    private void displayRetrievedItem(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
//                    rfs.getActionBar().setTitle(rd.data.getString("Name"));
                    rfs.setText(rd.data.getString("Rfs"), TextView.BufferType.EDITABLE);
                    horizon.setText(rd.data.getString("Horizon"), TextView.BufferType.EDITABLE);
                    estimationmethod.setText(rd.data.getString("EstimationMethod"),TextView.BufferType.EDITABLE);
                    decayfactor.setText(rd.data.getString("DecayFactor"),TextView.BufferType.EDITABLE);
                    timewindow.setText(rd.data.getString("TimeWindow"),TextView.BufferType.EDITABLE);
                    mda.setText(rd.data.getString("Mda"),TextView.BufferType.EDITABLE);
                }
                catch(JSONException e){
                    Log.w ("kek","more json fails");
                }
            }
        });
    }

    private String buildReqChild() {
        return "http://10.185.16.140/AppServices/api/RiskFactor/DiffusionModel/"+ Id;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // set Menu Name :) =>
        getMenuInflater().inflate(R.menu.menu_item_page, menu);
        Intent intent = getIntent();
        setTitle(intent.getStringExtra("NAME"));
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
        rd.data = serialize();
        rd.url = "http://10.185.16.140/AppServices/api/RiskFactor/SaveDiffusionModel";
        new Thread (new Runnable() {
            @Override
            public void run() {
                try {
                    if (!ss.saveSpaceChild(rd.url, rd.data.toString()))
                        Log.w ("kek","savefailed");
                } catch (IOException e) {
                    Log.w("kek", "save failed");
                }
            }
        }).start();
    }

    private JSONObject serialize() {
        JSONObject json = new JSONObject();
        try {
            json.put("ID", Id);
            json.put("Name", Name);
            json.put("Rfs", rfs.getText().toString());
            json.put("Horizon", horizon.getText().toString());
            json.put("EstimationMethod", estimationmethod.getText().toString());
            json.put("DecayFactor", decayfactor.getText().toString());
            json.put("TimeWindow", timewindow.getText().toString());
            json.put("Mda", mda.getText().toString());
        }catch (JSONException e) {Log.w ("kek","wtf how u fail this?");}

        return json;
    }

    private void goHome() {
        Intent i = new Intent(EvolutionModel.this, HomePage.class);
        startActivity(i);
        finish();
    }
}

