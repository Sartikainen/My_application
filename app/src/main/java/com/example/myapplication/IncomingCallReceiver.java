package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class IncomingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        if(number != null) {
            Log.e("MY_LOG", "Number: " + number);
        }
    }
}
