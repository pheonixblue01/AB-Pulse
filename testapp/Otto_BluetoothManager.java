package com.example.cameroni.testapp;

/**
 * Created by Cameroni on 3/25/2016.
 */

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;


public class Otto_BluetoothManager {
    public Otto_BluetoothManager(){

    }

    public void StartBTManager(){
        adapter = BluetoothAdapter.getDefaultAdapter();

        if(!adapter.isEnabled()) {
            adapter.enable();
            while (!adapter.isEnabled())
                ;
        }
        else if(!adapter.isDiscovering()){
            adapter.startDiscovery();
            //adapter.
        }




        /*
        currentState = adapter.getState();
        if(currentState != BluetoothAdapter.STATE_ON || currentState != BluetoothAdapter.STATE_TURNING_ON || currentState != BluetoothAdapter.STATE_ON)
            adapter.;
            */
            //turn on
    }

    public BluetoothAdapter adapter;
    private int currentState;
}
