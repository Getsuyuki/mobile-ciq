package com.r2ciq.zq.mobileciq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


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
        createAuthRequest();
        sendAuthRequest();
        //will not perform network requests before serverside is set up
    }

    //This can be altered in the future to build actual outgoing auth. request
    public void createAuthRequest()
    {

    }

    //1. before server is set up, it will do nothing
    //2. after server is set up, it will perform http post via async while spinner loads
    public void sendAuthRequest ()
    {
        Intent i = new Intent(CIQAuth.this, HomePage.class);
        //Intent i = new Intent(CIQAuth.this, testmain.class);
        startActivity(i);
        finish();
    }
}