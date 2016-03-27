package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/26/2016.
 */
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.harman.pulsesdk.DeviceModel;
import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseThemePattern;
import com.harman.pulsesdk.PulseHandlerInterface;
import com.harman.pulsesdk.PulseNotifiedListener;
import com.harman.pulsesdk.ImplementPulseHandler;


import android.content.Intent;
import android.util.Log;

public class Otto_Activity extends Otto_Notification {

    public String appName;

    public Timer timer = new Timer();
    public long duration = 0;

    @Override
    public void RunNotification(){
       Otto_BluetoothManager.SetupConnection(preferredMac, pulseEnabled);
        if(pulseEnabled && Otto_PulseHandler.isConnected()){
            Otto_PulseHandler.SetColor(pulseColor);
            Otto_PulseHandler.SetPattern(pulseThemePattern);
            Otto_PulseHandler.SetBrightness(pulseBrightness);
        }

        if(playAlaram) {
            PlayAlarm();
        }
        else {
            if (appName != null) {
                try {
                    Intent launchIntent = MainActivity.applicationContext.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                    MainActivity.applicationContext.startActivity(launchIntent);
                } catch (RuntimeException e) {
                    Log.i("ERROR", "Unable to start application!");
                }
            }
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(playAlaram)
                        PlayAlarm();
                }
            }, duration);

        }

        //Otto_BluetoothManager.DisconnectFromDevice();
    }

    public void StopAlaram(){
        if(alarmPlayer != null) {
            alarmPlayer.release();
            alarmPlayer = null;
        }
        if (appName != null) {
            try {
                Intent launchIntent = MainActivity.applicationContext.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                MainActivity.applicationContext.startActivity(launchIntent);
            } catch (RuntimeException e) {
                Log.i("ERROR", "Unable to start application!");
            }
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                PlayAlarm();
            }
        }, duration);
    }


}