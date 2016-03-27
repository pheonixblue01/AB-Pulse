package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/26/2016.
 */

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.camera2.CameraManager;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;


import com.harman.pulsesdk.DeviceModel;
import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseThemePattern;
import com.harman.pulsesdk.PulseHandlerInterface;
import com.harman.pulsesdk.PulseNotifiedListener;
import com.harman.pulsesdk.ImplementPulseHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


public class Otto_BluetoothManager {

    public static void Init(){

    }

    public static void SetupConnection(String prefferedConnection, boolean pulseEnabled){

        if(pulseEnabled){
            pulseHandler = new ImplementPulseHandler();

            pulseHandler.registerPulseNotifiedListener(new PulseNotifiedListener() {
                @Override
                public void onConnectMasterDevice() {
                    /*
                    PulseColor color = new PulseColor();
                    color.red = (byte) 255;
                    color.blue = (byte) 0;
                    color.green = (byte) 255;
                    Otto_BluetoothManager.pulseHandler.SetBackgroundColor(color, false);
                    */
                }

                @Override
                public void onDisconnectMasterDevice() {

                }

                @Override
                public void onRetBrightness(int i) {

                }

                @Override
                public void onLEDPatternChanged(PulseThemePattern pulseThemePattern) {

                }

                @Override
                public void onSoundEvent(int i) {

                }

                @Override
                public void onRetCaptureColor(PulseColor pulseColor) {

                }

                @Override
                public void onRetCaptureColor(byte b, byte b1, byte b2) {

                }

                @Override
                public void onRetSetDeviceInfo(boolean b) {

                }

                @Override
                public void onRetGetLEDPattern(PulseThemePattern pulseThemePattern) {

                }

                @Override
                public void onRetRequestDeviceInfo(DeviceModel[] deviceModels) {

                }

                @Override
                public void onRetSetLEDPattern(boolean b) {

                }
            });


            pulseHandler.ConnectMasterDevice(MainActivity.mainActivity);

            if(pulseHandler.isConnectMasterDevice())
                return;
            else{
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Otto_BluetoothManager.pulseHandler.registerPulseNotifiedListener(new PulseNotifiedListener() {
                            @Override
                            public void onConnectMasterDevice() {
                                /*
                                PulseColor color = new PulseColor();
                                color.red = (byte) 255;
                                color.blue = (byte) 255;
                                color.green = (byte) 255;
                                Otto_BluetoothManager.pulseHandler.SetBackgroundColor(color, false);
                                */
                            }

                            @Override
                            public void onDisconnectMasterDevice() {

                            }

                            @Override
                            public void onRetBrightness(int i) {

                            }

                            @Override
                            public void onLEDPatternChanged(PulseThemePattern pulseThemePattern) {

                            }

                            @Override
                            public void onSoundEvent(int i) {

                            }

                            @Override
                            public void onRetCaptureColor(PulseColor pulseColor) {

                            }

                            @Override
                            public void onRetCaptureColor(byte b, byte b1, byte b2) {

                            }

                            @Override
                            public void onRetSetDeviceInfo(boolean b) {

                            }

                            @Override
                            public void onRetGetLEDPattern(PulseThemePattern pulseThemePattern) {

                            }

                            @Override
                            public void onRetRequestDeviceInfo(DeviceModel[] deviceModels) {

                            }

                            @Override
                            public void onRetSetLEDPattern(boolean b) {

                            }
                        });
                        Otto_BluetoothManager.pulseHandler.ConnectMasterDevice(MainActivity.mainActivity);
                    }

                }, 25000);
            }
        }


        adapter = BluetoothAdapter.getDefaultAdapter();

        devicePriority.addAll(adapter.getBondedDevices());

        GetAvailableDevices();


    }


    public static void GetAvailableDevices(){
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
        MainActivity.applicationContext.registerReceiver(reciever, filter);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.applicationContext.unregisterReceiver(reciever);
                ConnectToDevice();
            }
        }, 5000);
    }


    private static boolean ConnectToDevice(){
        for(int i = 0; i < devicePriority.size(); i++){
            if(devicePriority.get(i).getName().compareTo("JBL Pulse 2") == 0)
                try {
                    socket = devicePriority.get(i).createRfcommSocketToServiceRecord(devicePriority.get(i).getUuids()[1].getUuid());//devicePriority.get(i).getUuids()[0].getUuid());
                    socket.connect();




                }
                catch(IOException e){}
        }

        return false;
    }


    public static BluetoothDevice GetConnectedDevice(){
        return socket.getRemoteDevice();
    }

    public static BluetoothAdapter adapter;
    private static ArrayList<BluetoothDevice> trustedDevices = new ArrayList<BluetoothDevice>();
    private static ArrayList<BluetoothDevice> availableDevices = new ArrayList<BluetoothDevice>();
    private static ArrayList<BluetoothDevice> devicePriority = new ArrayList<BluetoothDevice>();
    private static Timer timer = new Timer();
    private static BroadcastReceiver reciever;
    private static BluetoothSocket socket;

    public static PulseHandlerInterface pulseHandler;

    /*
    public static void Init(){

        //Load in mac addresses

        adapter = BluetoothAdapter.getDefaultAdapter();
        //GetAvailableDevices();
        trustedDevices = new ArrayList<BluetoothDevice>();
        trustedDevices.addAll(adapter.getBondedDevices());

        int i = 0;
        for(i = 0; i < devicePriorityMacs.size(); i++){
            if(devicePriorityMacs.get(i) != trustedDevices.get(i).getAddress()){
                boolean isStillTrusted = false;
                for(int j = 0; j <  trustedDevices.size(); j++){
                    if(devicePriorityMacs.get(i) == trustedDevices.get(j).getAddress()){
                        BluetoothDevice tempDevice = trustedDevices.get(i);
                        trustedDevices.set(i,trustedDevices.get(j));
                        trustedDevices.set(j, tempDevice);
                        isStillTrusted = true;
                        break;
                    }
                }
                if(!isStillTrusted)
                    devicePriorityMacs.remove(i);
            }
        }
        if(devicePriorityMacs.size() < trustedDevices.size()){
            devicePriorityMacs.add(trustedDevices.get(i).getAddress());
        }
    }

    public static void SetupConnection(String preferredeMac){
        if(preferredeMac != null && preferredMac != "")
            Otto_BluetoothManager.preferredMac = preferredeMac;
        GetAvailableDevices();
    }


    private static void GetAvailableDevices(){
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
        MainActivity.applicationContext.registerReceiver(reciever, filter);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.applicationContext.unregisterReceiver(reciever);
                FindTopPriorityDevice();
            }
        }, 5000);
    }

    private static void FindTopPriorityDevice(){
        for(int i = 0; i < availableDevices.size(); i++){
            if(availableDevices.get(i).getAddress().compareTo(preferredMac) == 0)
                ConnectToDevice(availableDevices.get(i));
        }
        for(int i = 0; i < trustedDevices.size(); i++){
            if(availableDevices.contains(trustedDevices.get(i))){
                for(int j = 0; j < availableDevices.size(); j++){
                    if(availableDevices.get(j) == trustedDevices.get(i))
                        ConnectToDevice(availableDevices.get(j));
                }
            }
                //ConnectToDevice(availableDevices..get(i));
        }
    }

    private static void ConnectToDevice(BluetoothDevice device){
        try {
            socket = device.createRfcommSocketToServiceRecord(device.getUuids()[0].getUuid());
            socket.connect();

            Otto_PulseHandler.StartPulseConnection();
        }
        catch(IOException e){
            Log.i("ERROR", "Unable to connect to bluetooth device.");
        }
    }

    public static void DisconnectFromDevice(){
        try {
            if (socket.isConnected())
                socket.close();
        }
        catch(IOException e){
            Log.i("ERROR", "Unable to disconnect from bluetooth device.");
        }
    }

    public static BluetoothDevice GetConnectedDevice(){
        if(socket.isConnected())
            return socket.getRemoteDevice();
        else
            return null;
    }


    public static BluetoothAdapter adapter;
    private static ArrayList<String> devicePriorityMacs = new ArrayList<String>();
    private static ArrayList<BluetoothDevice> trustedDevices;
    private static ArrayList<BluetoothDevice> availableDevices = new ArrayList<BluetoothDevice>();
    private static Timer timer = new Timer();
    private static BroadcastReceiver reciever;
    public static BluetoothSocket socket;
    private static String preferredMac = "";
    */
}
