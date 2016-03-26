package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/26/2016.
 */

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.camera2.CameraManager;
import android.os.Parcelable;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.locks.Lock;


public class Otto_BluetoothManager {
    public Otto_BluetoothManager(){

    }

    public void StartBTManager(Context context){
        this.context = context;
        adapter = BluetoothAdapter.getDefaultAdapter();
        GetAvailableDevices();

        devicePriority.addAll(adapter.getBondedDevices());
    }


    public void GetAvailableDevices(){
        if(!adapter.isEnabled())
            adapter.enable();

        else if(!adapter.isDiscovering())
            adapter.startDiscovery();


        reciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action));{
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    availableDevices.add(device);
                }
            }
        };
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        context.registerReceiver(reciever, filter);

        timer.schedule(new TimerTask(){
                          @Override
                          public void run(){
                              context.unregisterReceiver(reciever);
                              ConnectToDevice();
                          }
                       }, 5000);
    }


    private boolean ConnectToDevice(){
        for(int i = 0; i < devicePriority.size(); i++){
            if(devicePriority.get(i).getName().compareTo("JBL Pulse 2") == 0)
                try {
                    BluetoothSocket socket = devicePriority.get(i).createRfcommSocketToServiceRecord(devicePriority.get(i).getUuids()[0].getUuid());//devicePriority.get(i).getUuids()[0].getUuid());
                    socket.connect();
                }
                catch(IOException e){}
        }
        //((TextView)((Activity)context).findViewById(R.id.tv1)).setText(device.getName());
        /*
        try {
            device.createRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
        }
        catch(IOException e){

        }
        */
        return false;
    }


    public Context context;
    public BluetoothAdapter adapter;
    private ArrayList<BluetoothDevice> trustedDevices = new ArrayList<BluetoothDevice>();
    private ArrayList<BluetoothDevice> availableDevices = new ArrayList<BluetoothDevice>();
    private ArrayList<BluetoothDevice> devicePriority = new ArrayList<BluetoothDevice>();
    private Timer timer = new Timer();
    private BroadcastReceiver reciever;
}
