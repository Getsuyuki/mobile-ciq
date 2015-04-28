package com.r2ciq.zq.mobileciq;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class CIQAuth extends Activity {
    private EditText username;
    private EditText password;
    private TextView authMessageArea;
    private ImageView authSpinner;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciq_auth);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        authMessageArea = (TextView) findViewById(R.id.authResponseArea);
        authSpinner = (ImageView) findViewById(R.id.authSpinner);
    }

    public void submitCredentials (View view)
    {
        authMessageArea.setVisibility(View.GONE);
        showSpinner();
        disableFields();
        String body = null;
        try {
            body = buildJSon(username.getText().toString() ,password.getText().toString());
        } catch (JSONException e) {
            jsonFailure();
        }
        //SubmitAuthRequest("http://echo.jsontest.com/UserName/jweber/Password/password");
        SubmitAuthRequest("http://10.185.16.140/AppServices/api/Login/AuthenticateX", body);
        //startAppRequest();
        //will not perform network requests before serverside is set up
    }

    private void disableFields() {
        username.setEnabled(false);
        password.setEnabled(false);
    }

    private void showSpinner() {
        authSpinner.setVisibility(View.VISIBLE);
        AnimationDrawable spinner = (AnimationDrawable) authSpinner.getDrawable();
        // Start the animation (looped playback by default).
        spinner.start();
    }

    private void jsonFailure() {
        authMessageArea.setVisibility(View.VISIBLE);
        authMessageArea.setText("Somehow your name/pw are not legit");
    }

    private void revert() {
        username.setEnabled(true);
        password.setEnabled(true);
        AnimationDrawable spinner = (AnimationDrawable)authSpinner.getDrawable();
        spinner.stop();
        authSpinner.setVisibility(View.GONE);
        authMessageArea.setVisibility(View.VISIBLE);
        authMessageArea.setText("Login Failed");  //the pokemon failure handling is real (/._.)/
    }

    private String buildJSon(String user, String pw) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("UserName", user);
        json.put("Password", pw);

        return json.toString();
    }

    //This can be altered in the future to build actual outgoing auth. request
    private void SubmitAuthRequest (String url, String bodyJSon) {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(15, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(15, TimeUnit.SECONDS);

        RequestBody body = RequestBody.create(JSON, bodyJSon);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.w ("kek", e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        revert();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                authenticate(response);
            }
        });
    }

    public void authenticate(Response response){
        try {
            JSONObject receivedResponse = new JSONObject(response.body().string());
            Log.w("kek", receivedResponse.toString());
            startAppRequest();
        }
        catch (JSONException | IOException e) {
            Log.w("kek", e);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    revert();
                }
            });
        }
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