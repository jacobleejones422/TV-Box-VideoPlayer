package com.corochann.androidtvapptutorial;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.Binder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class UpdateService extends Service {

    private static final String TAG_BOOT_EXECUTE_SERVICE = "BOOT_BROADCAST_SERVICE";

    @Override
    public void onCreate() {
        super.onCreate();
        // REGISTER RECEIVER THAT HANDLES SCREEN ON AND SCREEN OFF LOGIC
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        if (!screenOn) {
            // YOUR CODE
        } else {
//            this.start
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

//        String message = "RunAfterBootService onStartCommand() method.";
//
//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
//
//        Log.d(TAG_BOOT_EXECUTE_SERVICE, "RunAfterBootService onStartCommand() method.");
//
//        return super.onStartCommand(intent, flags, startId);

        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        final BroadcastReceiver mReceiver = new ScreenReceiver();
        registerReceiver(mReceiver, filter);
        return super.onStartCommand(intent, flags, startId);
    }
    public class LocalBinder extends Binder {
        UpdateService getService() {
            return UpdateService.this;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}