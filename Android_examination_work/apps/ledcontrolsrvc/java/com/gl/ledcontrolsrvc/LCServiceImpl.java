package com.gl.ledcontrolsrvc;

import android.util.Log;
import vendor.gl.ledcontrol.V1_0.ILedControl;

public class LCServiceImpl extends ILCService.Stub {
    private static final String LOG_TAG = "LCServiceImpl";
    private ILedControl lchal = null;

    public void setLedState(byte led, byte state) {
        Log.d(LOG_TAG, "-> setLedState(" + led + ", " + state + ")");
        try {
            if (lchal == null) {
                lchal = ILedControl.getService(true);
            }
            lchal.setLedState(led, state);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "Fail to call service");
        }
    }
}
