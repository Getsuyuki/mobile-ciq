package com.r2ciq.zq.mobileciq;

import org.apache.http.io.*;
import com.squareup.okhttp.*;

import java.io.IOException;

/**
 * Created by gibson_wong on 4/24/2015.
 */
public class SendRequest {
    OkHttpClient client = new OkHttpClient();

    public String SubmitLoginCredentials (String url) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
