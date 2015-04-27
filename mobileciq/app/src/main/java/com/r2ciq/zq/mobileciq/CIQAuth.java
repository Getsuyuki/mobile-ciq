package com.r2ciq.zq.mobileciq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class CIQAuth extends Activity {
    private EditText username;
    private EditText password;
    private TextView authMessageArea;
    private ImageView authSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciq_auth);
    }

    public void submitCredentials (View view)
    {
        SubmitAuthRequest();

        //will not perform network requests before serverside is set up
    }

    //This can be altered in the future to build actual outgoing auth. request
    public void SubmitAuthRequest()
    {
        SendRequest rq = new SendRequest();
        try {
            JSONObject response = new JSONObject(rq.SubmitLoginCredentials("http://echo.jsontest.com/user/jweber/pass/password"));
            Log.w("kek",response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.w("kek","JSON Failure");
        } catch (IOException e) {
            e.printStackTrace();
            Log.w("kek","IO Failure");
        }
        startAppRequest();
    }

    //1. before server is set up, it will do nothing
    //2. after server is set up, it will perform http post via async while spinner loads
    public void startAppRequest ()
    {
        Intent i = new Intent(CIQAuth.this, HomePage.class);
        //Intent i = new Intent(CIQAuth.this, testmain.class);
        startActivity(i);
        finish();
    }
}
//link: https://github.com/square/okhttp/wiki/Recipes