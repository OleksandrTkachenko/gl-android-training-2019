package com.gl.ledcontrolsrvc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LCService extends Service {
    private static final String LOG_TAG = "LCService";
    private LCServiceImpl mBinder = null;

    public LCService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "-> onCreate()");
        mBinder = new LCServiceImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "-> onBind()");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //super.onStartCommand(intent, flags, startId);
        Log.d(LOG_TAG, "-> onStartCommand()");
        return START_STICKY;
    }
}
