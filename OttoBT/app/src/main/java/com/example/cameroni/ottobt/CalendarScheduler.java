package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/26/2016.
 */
import java.util.ArrayList;
import java.util.GregorianCalendar;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class CalendarScheduler {
    public CalendarScheduler(Otto_Notification masterNotification){
        this.masterNotification = masterNotification;
    }

    public ArrayList<CalendarDate> scheduledDates = new ArrayList<CalendarDate>();
    public RepeatType repeatType = RepeatType.NONE;

    public Otto_Notification masterNotification;




    private ID_Manager idManager = new ID_Manager();





    public enum RepeatType {
        YEARLY, MONTHLY, WEEKLY, DAILY, NONE
    }



    public void Run(CalendarDate firedEventDate) {
        firedEventDate.UnsubscribeNotification();
        switch(repeatType){
            case YEARLY:
                firedEventDate.set(firedEventDate.get(GregorianCalendar.YEAR) + 1,
                        firedEventDate.get(GregorianCalendar.MONTH),
                        firedEventDate.get(GregorianCalendar.DAY_OF_MONTH),
                        firedEventDate.get(GregorianCalendar.HOUR_OF_DAY),
                        firedEventDate.get(GregorianCalendar.MINUTE),
                        firedEventDate.get(GregorianCalendar.SECOND));
                firedEventDate.SubscribeNotification();
                break;
            case MONTHLY:
                firedEventDate.set(firedEventDate.get(GregorianCalendar.YEAR),
                        firedEventDate.get(GregorianCalendar.MONTH) + 1,
                        firedEventDate.get(GregorianCalendar.DAY_OF_MONTH),
                        firedEventDate.get(GregorianCalendar.HOUR_OF_DAY),
                        firedEventDate.get(GregorianCalendar.MINUTE),
                        firedEventDate.get(GregorianCalendar.SECOND));
                firedEventDate.SubscribeNotification();
                break;
            case WEEKLY:
                firedEventDate.set(firedEventDate.get(GregorianCalendar.YEAR),
                        firedEventDate.get(GregorianCalendar.MONTH),
                        firedEventDate.get(GregorianCalendar.DAY_OF_MONTH) + 7,
                        firedEventDate.get(GregorianCalendar.HOUR_OF_DAY),
                        firedEventDate.get(GregorianCalendar.MINUTE),
                        firedEventDate.get(GregorianCalendar.SECOND));
                firedEventDate.SubscribeNotification();
                break;
            case DAILY:
                firedEventDate.set(firedEventDate.get(GregorianCalendar.YEAR),
                        firedEventDate.get(GregorianCalendar.MONTH),
                        firedEventDate.get(GregorianCalendar.DAY_OF_MONTH),
                        firedEventDate.get(GregorianCalendar.HOUR_OF_DAY) + 1,
                        firedEventDate.get(GregorianCalendar.MINUTE),
                        firedEventDate.get(GregorianCalendar.SECOND));
                firedEventDate.SubscribeNotification();
                break;
            case NONE:
                break;
            default:
                break;
        }

        masterNotification.RunNotification();
    }

    public void SubscribeNotifications(){
        for(int i = 0; i < scheduledDates.size(); i++)
            scheduledDates.get(i).SubscribeNotification();
    }

    public void UnsubscribNotifications(CalendarDate date){
        for(int i = 0; i < scheduledDates.size(); i++)
            scheduledDates.get(i).UnsubscribeNotification();
    }

    public void AddCalendarDate(CalendarDate date, boolean notificationOn){
        if(!scheduledDates.contains(date)) {
            date.SetID(idManager.GetID());
            scheduledDates.add(date);
            if (notificationOn)
                date.SubscribeNotification();
        }
    }

    public void RemoveCalendarDate(CalendarDate date){
        if(scheduledDates.contains(date)) {
            date.UnsubscribeNotification();
            idManager.ReturnID(date.GetID());
            date.SetID(-1);
            scheduledDates.remove(date);
        }
    }

    public CalendarDate GetCalendarDate(int id){
        if(id < scheduledDates.size() && id >= 0)
            return scheduledDates.get(id);
        return null;
    }
}
