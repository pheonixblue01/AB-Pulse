package com.example.cameroni.ottobt;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AlarmManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);

        Button alarmbutton = (Button) findViewById(R.id.AlarmsButton);
        alarmbutton.setEnabled(false);
        Button alertbutton = (Button) findViewById(R.id.AlertsButton);
        alertbutton.setEnabled(true);
        Button activitybutton = (Button) findViewById(R.id.ActivitiesButton);
        activitybutton.setEnabled(true);
        Button homepagebutton = (Button) findViewById(R.id.HomeButton);
        homepagebutton.setEnabled(true);
        Button AddAlarmButton = (Button) findViewById(R.id.AddAlarmButton);
        AddAlarmButton.setEnabled(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }




    private void alarmsTabButton(){
        Intent intent = new Intent(AlarmManager.this, AlarmManager.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);      }
    private void alertsTabButton(){
        Intent intent = new Intent(AlarmManager.this, AlertManager.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);      }
    private void activitiesTabButton(){
        Intent intent = new Intent(AlarmManager.this, ActivityManager.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);      }
    private void homeTabButton(){
        Intent intent = new Intent(AlarmManager.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);      }
    private void AddAlarmButton(){
        Intent intent = new Intent(AlarmManager.this, EditAlarm.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
