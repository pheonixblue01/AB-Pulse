package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/26/2016.
 */
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import java.util.ArrayList;
import java.lang.reflect.Array;




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


            BroadcastReceiver reciever = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if(BluetoothDevice.ACTION_FOUND.equals(action));{
                        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    }
                }
            }
        }




        /*
        currentState = adapter.getState();
        if(currentState != BluetoothAdapter.STATE_ON || currentState != BluetoothAdapter.STATE_TURNING_ON || currentState != BluetoothAdapter.STATE_ON)
            adapter.;
            */
        //turn on
    }

    private BluetoothAdapter adapter;
    private ArrayList<BluetoothDevice> trustedDevices = new ArrayList<BluetoothDevice>();
}
