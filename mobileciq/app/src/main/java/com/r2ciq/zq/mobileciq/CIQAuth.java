package com.r2ciq.zq.mobileciq;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class CIQAuth extends Activity {
    private EditText userNameInput;
    private EditText passWordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciq_auth);
    }

    public void submitCredentials (View view){
       
    }
}