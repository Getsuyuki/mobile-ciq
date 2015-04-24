//package com.r2ciq.zq.mobileciq;
//
//import android.os.AsyncTask;
//import org.json.JSONException;
//import org.json.JSONObject;
///**
//* Created by gibson_wong on 4/24/2015.
//*/
//public class PrefetchData extends AsyncTask<Void, Void, Void> {
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        // before making http calls
//
//    }
//
//    @Override
//    protected Void doInBackground(Void... arg0) {
//            /*
//             * Will make http call here This call will download required data
//             * before launching the app
//             * example:
//             * 1. Downloading and storing in SQLite
//             * 2. Downloading images
//             * 3. Fetching and parsing the xml / json
//             * 4. Sending device information to server
//             * 5. etc.,
//             */
//        JsonParser jsonParser = new JsonParser();
//        String json = jsonParser
//                .getJSONFromUrl("http://api.androidhive.info/game/game_stats.json");
//
//        Log.e("Response: ", "> " + json);
//
//        if (json != null) {
//            try {
//                JSONObject jObj = new JSONObject(json)
//                        .getJSONObject("game_stat");
//                now_playing = jObj.getString("now_playing");
//                earned = jObj.getString("earned");
//
//                Log.e("JSON", "> " + now_playing + earned);
//
//            } catch (JSONException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void result) {
//        super.onPostExecute(result);
//        // After completing http call
//        // will close this activity and lauch main activity
//        Intent i = new Intent(SplashScreen.this, MainActivity.class);
//        i.putExtra("now_playing", now_playing);
//        i.putExtra("earned", earned);
//        startActivity(i);
//
//        // close this activity
//        finish();
//    }
//
//}
