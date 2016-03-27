package com.example.cameroni.ottobt;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.PendingIntent;

/**
 * Created by Cameroni on 3/26/2016.
 */
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.app.Service;

public class CalendarDate extends GregorianCalendar implements ID_Owner{
    public CalendarDate(CalendarScheduler masterScheduler, int year, int month, int day, int hour, int minute, int seconds){
        super(year, month, day, hour, minute,seconds);
        this.masterScheduler = masterScheduler;
        notificationReceiver = new NotificationReceiver(this);
    }

    public CalendarDate(CalendarScheduler masterScheduler, Date date){
        setTime(date);
        this.masterScheduler = masterScheduler;
    }

    public CalendarScheduler masterScheduler;


    public NotificationReceiver notificationReceiver;
    public PendingIntent notificationPendingIntent;



    /*
    public class NotificationHandlerService extends Service {

    }
    */

    public void Run(){
        masterScheduler.Run(this);
    }

    public void SubscribeNotification(){
        //notificationReceiver = new NotificationReceiver(this);
        /*
        final CalendarDate date = this;
        notificationReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                MainActivity.debugOutput.setText("FIRED AND WORKING!");
                masterScheduler.Run(date);
            }
        };
        */

        Intent notificationIntent = new Intent(MainActivity.mainActivity, NotificationReceiver.class);
        notificationIntent.putExtra("NOTIFICATION_ID", masterScheduler.masterNotification.GetID());
        notificationIntent.putExtra("DATE_ID", GetID());
        notificationPendingIntent = PendingIntent.getBroadcast(MainActivity.mainActivity,0,notificationIntent, PendingIntent.FLAG_ONE_SHOT);//PendingIntent.FLAG_UPDATE_CURRENT);
        WakeUpManager.SubscribeNotification(this.getTimeInMillis(), notificationPendingIntent);
    }

    public void UnsubscribeNotification(){
        WakeUpManager.UnsubscribeNotification(notificationPendingIntent);
    }


    protected int id = -1;
    public int GetID(){ return id; }
    public void SetID(int id){ this.id = id; }
}
