package com.r2ciq.zq.mobileciq;

import android.os.StrictMode;
import android.util.Log;

import org.apache.http.io.*;
import com.squareup.okhttp.*;

import java.io.IOException;

/**
 * Created by gibson_wong on 4/24/2015.
 */
@Deprecated
public class SendRequest {
    OkHttpClient client = new OkHttpClient();

    public String SubmitLoginCredentials (String url) throws IOException{
        class PulledCredentials {
            public String credentials;

            public String getCredentials() {
                return credentials;
            }

            public void setCredentials(String responseBody){
                credentials = responseBody;
            }
        }

        final PulledCredentials responseBody = new PulledCredentials();

        Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.w ("kek", e);
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    responseBody.setCredentials(response.body().string());
                }
            });
            return responseBody.getCredentials();
        }
}
