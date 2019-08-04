package com.gl.ledcontrol;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.gl.ledcontrolsrvc.ILCService;
import vendor.gl.ledcontrol.V1_0.LedState;
import vendor.gl.ledcontrol.V1_0.Leds;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LedControl";
    private static final String SRV_PKG = "com.gl.ledcontrolsrvc";
    private static final String SRV_NAME = "LCService";

    private ServiceConnection mSrvConn = null;
    private ILCService mService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "-> onCreate()");
        connectToService();
        Switch led1sw = findViewById(R.id.ledswitch1);
        Switch led2sw = findViewById(R.id.ledswitch2);
        Switch led3sw = findViewById(R.id.ledswitch3);
        Switch led4sw = findViewById(R.id.ledswitch4);

        led1sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(LOG_TAG, "-> onLED1StateChange(" + isChecked + ")");
                switchLedState(Leds.LED_GREEN_1, isChecked);
            }
        });
        led2sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(LOG_TAG, "-> onLED2StateChange(" + isChecked + ")");
                switchLedState(Leds.LED_GREEN_2, isChecked);
            }
        });
        led3sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(LOG_TAG, "-> onLED3StateChange(" + isChecked + ")");
                switchLedState(Leds.LED_GREEN_3, isChecked);
            }
        });
        led4sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(LOG_TAG, "-> onLED4StateChange(" + isChecked + ")");
                switchLedState(Leds.LED_GREEN_4, isChecked);
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "-> onDestroy()");
        if (mSrvConn != null) {
            Log.d(LOG_TAG, "-> unbindService()");
            unbindService(mSrvConn);
        }
        super.onDestroy();
    }

    private void connectToService(){
        Log.d(LOG_TAG, "-> connectToService()");
        mSrvConn =  new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "-> onServiceConnected");
                mService = ILCService.Stub.asInterface(binder);
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "-> onServiceDisconnected");
            }
        };
        Intent intent = new Intent(SRV_PKG + "." + SRV_NAME);
        intent.setPackage(SRV_PKG);
        bindService(intent, mSrvConn, BIND_AUTO_CREATE);
    }

    private void switchLedState(byte led, boolean enable) {
        byte state = enable ? LedState.LED_STATE_ON : LedState.LED_STATE_OFF;
        try {
            mService.setLedState(led, state);
        } catch (RemoteException e) {
            Log.e(LOG_TAG, "Unable to change LED state");
            e.printStackTrace();
        }
    }
}
