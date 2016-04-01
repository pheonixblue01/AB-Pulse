package com.example.cameroni.ottobt;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ActivityManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_manager);
        initAddActivitymButton();
        initHomeButton();
        initAlarmsButton();
        initAlertsButton();
        initActivitiesButton();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void initAlarmsButton(){
        Button list = (Button) findViewById(R.id.AlarmsButton);
        list.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                Intent intent = new Intent(ActivityManager.this, AlarmManager.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        list.setEnabled(true);
    }
    private void initAlertsButton(){
        Button list = (Button) findViewById(R.id.AlertsButton);
        list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityManager.this, AlertManager.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        list.setEnabled(true);
    }
    private void initActivitiesButton(){
        Button list = (Button) findViewById(R.id.ActivitiesButton);
        list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityManager.this, ActivityManager.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        list.setEnabled(false);
    }
    private void initHomeButton(){
        Button list = (Button) findViewById(R.id.HomeButton);
        list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityManager.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        list.setEnabled(true);
    }
    private void initAddActivitymButton(){
        Button list = (Button) findViewById(R.id.AddActivityButton);
        list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityManager.this, EditActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        list.setEnabled(true);
    }
}
