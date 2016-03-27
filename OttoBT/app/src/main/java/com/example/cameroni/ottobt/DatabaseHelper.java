package com.example.cameroni.ottobt;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

/**
 * Created by pheon on 3/26/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

private static final String DATABASE_NAME="OttoBT";
private static final int DATABASE_VERSION=1;

public void onCreate(SQLiteDatabase OttoBT){
    OttoBT.execSQL(CREATE_TABLE_ABPULSE);
}
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.v(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS OttoBT");
        onCreate(db);

    }

    private static final String CREATE_TABLE_ABPULSE = "create table if not exists "
            +"ABPulseDBNotifications (NotificationNumber integer primary key autoincrement, "
            +"NotificationName text not null, NotificationType text not null, "
            +"NotificationDate text null, NotificationTime text not null, "
            +"NotificationDays integer null, NotificationRepeatSchedule integer null, "
            +"NotificationDuration integer null, NotificationAtEndTime integer null, "
            +"NotificationSound text null, NotificationAppToOpen text null, "
            +"NotificationEnabled integer not null, HarmanSpeakerColor text null, "
            +"HarmanSpeakerPattern text null, HarmanSpeakerBrightness integer null, "
            +"HarmanSpeakerVolume integer null, HarmanSpeakerSpeed integer null) ";
    private static final String CREATE_TABLE_DEVICEMANAGER = "create tabke if not exists"
            +"DevicePriorityTable (DeviceIdNumber integer primary key autoincrement, "
            +"DeviceName text not null, DeviceMacAddress text not null, PriorityNumber integer null)";



}
