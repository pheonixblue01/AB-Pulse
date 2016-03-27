package com.example.cameroni.ottobt;

import java.util.ArrayList;

/**
 * Created by Cameroni on 3/26/2016.
 */

import com.harman.pulsesdk.DeviceModel;
import com.harman.pulsesdk.ImplementPulseHandler;
import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseHandlerInterface;
import com.harman.pulsesdk.PulseNotifiedListener;
import com.harman.pulsesdk.PulseThemePattern;

import android.media.MediaPlayer;
import android.provider.*;

public abstract class Otto_Notification implements ID_Owner{
    public static void Init(){

    }

    public abstract void RunNotification();

    public boolean notificationOn = true;
    public CalendarScheduler scheduler = new CalendarScheduler(this);
    public String preferredMac = "";
    public int bluetoothPriorityIndex = 0;

    public boolean pulseEnabled = false;
    public PulseColor pulseColor;
    public PulseThemePattern pulseThemePattern;
    public int pulseBrightness = 50;

    public MediaPlayer alarmPlayer;
    public boolean playAlaram = false;

    protected int id = -1;
    public int GetID(){ return id; }
    public void SetID(int id){ this.id = id; }



    public void AddCalendarDate(CalendarDate date){
        scheduler.AddCalendarDate(date, notificationOn);
    }

    public void RemoveCalendarDate(CalendarDate date){
        scheduler.RemoveCalendarDate(date);
    }

    public void PlayAlarm(){
        if(alarmPlayer != null){
            alarmPlayer.release();
            alarmPlayer = null;
        }
        alarmPlayer = MediaPlayer.create(MainActivity.applicationContext, android.provider.Settings.System.DEFAULT_RINGTONE_URI);//MediaPlayer.create(this, notification);
        alarmPlayer.setLooping(true);
        alarmPlayer.start();
    }

    public void StopAlarm(){
        if(alarmPlayer != null) {
            alarmPlayer.release();
            alarmPlayer = null;
        }
    }


    public static ArrayList<Otto_Notification> allNotifications = new ArrayList<Otto_Notification>();
    private static ID_Manager idManager = new ID_Manager();

    public static Object[] GetAllNotifications(){
        return allNotifications.toArray();
    }

    public static Otto_Notification GetNotification(int id){
        if(id < allNotifications.size() && id >= 0)
            return allNotifications.get(id);
        return null;
    }

    public static void AddNotification(Otto_Notification notification){
        if(!allNotifications.contains(notification)) {
            notification.SetID(idManager.GetID());
            allNotifications.add(notification);
        }
    }

    public static void RemoveNotifiaction(Otto_Notification notification){
        if(allNotifications.contains(notification)) {
            idManager.ReturnID(notification.GetID());
            notification.SetID(-1);
            allNotifications.remove(notification);
        }
    }



    public CalendarDate GetCalendarDate(int id){
        return scheduler.GetCalendarDate(id);
    }


    public void PulseConnectionSuccessful(){

    }


    public void PusleConnectionFailed(){

    }


}
