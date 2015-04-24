package com.r2ciq.zq.mobileciq;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by gibson_wong on 4/24/2015.
 */
public class StudioSwitcher extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        //todo: technically should abstract strings
        if (intent.getAction().equals("SELECTSTUDIO")) {
            Studiotype startingstudio = Studiotype.valueOf(intent.getStringExtra("launchstudio"));
            switch (startingstudio) {
                case RiskFactor:
                    Intent i = new Intent(context, RiskFactor.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                    return;
                case Product:
                case Simulation:
                case Scenario:
                case Dashboard:
                    Log.w("kek", "lololol unimplemented hohohoho");
                    return;
                default:
                    Log.w("kek", "not even a choice bro");
                    return;
            }
        }
    }
}
