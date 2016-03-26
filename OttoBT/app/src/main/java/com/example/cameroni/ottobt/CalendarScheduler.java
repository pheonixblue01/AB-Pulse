package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/26/2016.
 */
import java.util.ArrayList;


public class CalendarScheduler {

    public ArrayList<CalendarDate> scheduledDates = new ArrayList<CalendarDate>();
    private ArrayList<CalendarDate> newScheduleStore = new ArrayList<CalendarDate>();
    public RepeatType repeatType = RepeatType.WEEKLY;

    public class CalendarDate {
        public CalendarDate(int second, int minute, int hour, int day, int week, int month, int year){
            this.second = second;
            this.minute = minute;
            this.hour = hour;
            this.day = day;
            this.month = month;
            this.year = year;
        }

        public int second, minute, hour, day, week, month, year;
    }

    public enum RepeatType {
        YEARLY, MONTHLY, WEEKLY, DAILY, NONE
    }

    public void Run(CalendarDate firedEventDate) {
        UnsubscribeEvent(firedEventDate);
        switch(repeatType){
            case YEARLY:
                firedEventDate.year += 1;
                SubscribeEvent(firedEventDate);
                break;
            case MONTHLY:
                firedEventDate.month += 1;
                SubscribeEvent(firedEventDate);
                break;
            case WEEKLY:
                firedEventDate.week += 1;
                SubscribeEvent(firedEventDate);
                break;
            case DAILY:
                firedEventDate.day += 1;
                SubscribeEvent(firedEventDate);
                break;
            case NONE:
                break;
            default:
                break;

        }
    }

    public void SubscribeEvent(CalendarDate date){

    }

    public void UnsubscribeEvent(CalendarDate date){

    }
}
