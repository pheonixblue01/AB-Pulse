package com.example.cameroni.ottobt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Cameroni on 3/26/2016.
 */
public class NotificationReceiver extends BroadcastReceiver {
    public NotificationReceiver(){}

    public NotificationReceiver(CalendarDate masterDate){
        this.masterDate = masterDate;
    }

    public CalendarDate masterDate;
    private int notificationID;
    private int dateID;

    @Override
    public void onReceive(Context context, Intent intent) {
        notificationID = intent.getIntExtra("NOTIFICATION_ID", -1);
        dateID = intent.getIntExtra("DATE_ID", -1);
        Otto.OttoInit();
        if(notificationID != -1 && dateID != -1)
            Otto_Notification.GetNotification(notificationID).GetCalendarDate(dateID).Run();
        //MainActivity.
        //masterDate.Run();
    }
}