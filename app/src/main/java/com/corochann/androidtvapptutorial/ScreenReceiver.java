package com.corochann.androidtvapptutorial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.net.Uri;

public class ScreenReceiver extends BroadcastReceiver {

    // thanks Jason
    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            // do whatever you need to do here
            Intent intent_tempa = new Intent(context,SplashScreen.class);
            context.startActivity(intent_tempa);
//            Intent intent_temp = new Intent(context,MainActivity.class);
//            context.startActivity(intent_temp);
            wasScreenOn = false;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            // and do whatever you need to do here
            wasScreenOn = true;
        }
    }

}
