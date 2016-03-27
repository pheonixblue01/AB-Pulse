package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/26/2016.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.Intent;

public class WakeUpManager {
    public static AlarmManager alarmManager;

    public static void Init(){
        alarmManager = (AlarmManager)MainActivity.applicationContext.getSystemService(Context.ALARM_SERVICE);
    }

    public static void SubscribeNotification(long triggerAtTime, PendingIntent notificationPendingIntent){
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, notificationPendingIntent);
    }

    public static void UnsubscribeNotification(PendingIntent notificationPendingIntent){
        alarmManager.cancel(notificationPendingIntent);
    }
}
