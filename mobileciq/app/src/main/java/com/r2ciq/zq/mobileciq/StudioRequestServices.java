package com.r2ciq.zq.mobileciq;

import android.os.StrictMode;
import android.util.Log;

import org.apache.http.io.*;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;

/**
 * Created by gibson_wong on 4/24/2015.
 */

public class StudioRequestServices {
    //instance?
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public Response getSpaceChild (String url) throws IOException{
        Request request = new Request.Builder()
                    .url(url)
                    .build();

        Response response = client.newCall(request).execute();

        return response;
    }

    public boolean saveSpaceChild (String url, String data) throws IOException  {
        RequestBody body = RequestBody.create(JSON, data);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        return response.isSuccessful();
    }
}
