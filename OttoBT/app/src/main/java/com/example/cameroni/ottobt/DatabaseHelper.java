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
    OttoBT.execSQL(CREATE_OTTOBT_TABLE);
}
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase OttoBT, int oldVersion, int newVersion){
        Log.v(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
        OttoBT.execSQL("DROP TABLE IF EXISTS OttoBT");
        onCreate(OttoBT);

    }

    private static final String CREATE_OTTOBT_TABLE =
            " CREATE TABLE IF NOT EXISTS `OttoDB`.`OttoDBNotifications` ( "
                    +" `NotificationNumber` INT NOT NULL AUTO_INCREMENT COMMENT '', "
                    +" `NotificationName` VARCHAR(45) NULL COMMENT '', "
                    +" `NotificationType` VARCHAR(45) NULL COMMENT '', "
                    +" `NotificationDate` DATE NULL COMMENT '', "
                    +" `NotificationTime` TIME(4) NULL COMMENT '', "
                    +" `NotificationDays` INT NULL COMMENT '', "
                    +" `NotificationRepeatSchedule` INT NULL COMMENT '', "
                    +" `NotificationDuration` INT NULL COMMENT '', "
                    +" `NotificationAtEndTime` TINYINT(1) NULL COMMENT '', "
                    +" `NotificationSound` VARCHAR(45) NULL COMMENT '', "
                    +" `NotificationAppToOpen` VARCHAR(45) NULL COMMENT '', "
                    +" `NotificationEnabled` TINYINT(1) NULL COMMENT '', "
                    +" `HarmonSpeakerColor` VARCHAR(45) NULL COMMENT '', "
                    +" `HarmonSpeakerPattern` VARCHAR(45) NULL COMMENT '', "
                    +" `HarmonSpeakerBrightness` INT NULL COMMENT '', "
                    +" `HarmonSpeakerVolume` INT NULL COMMENT '', "
                    +" `HarmonSpeakerSpeed` INT NULL COMMENT '', "
                    +" PRIMARY KEY (`NotificationNumber`)  COMMENT '') ";




}
