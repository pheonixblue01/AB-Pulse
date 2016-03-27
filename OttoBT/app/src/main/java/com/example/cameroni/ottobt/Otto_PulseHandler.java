package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/27/2016.
 */

import android.util.Log;

import com.harman.pulsesdk.DeviceModel;
import com.harman.pulsesdk.ImplementPulseHandler;
import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseHandlerInterface;
import com.harman.pulsesdk.PulseNotifiedListener;
import com.harman.pulsesdk.PulseThemePattern;

public class Otto_PulseHandler {
    public static void StartPulseConnection(){

        boolean connectionCompleted = false;
        Log.i("ConnectedDevice", Otto_BluetoothManager.GetConnectedDevice().getName());
        if(Otto_BluetoothManager.GetConnectedDevice().getName().compareTo("JBL Pulse 2") == 0){
            if(pulseHandler != null){
                if(pulseHandler.isConnectMasterDevice())
                    return;
            }

            pulseHandler = new ImplementPulseHandler();
            pulseHandler.registerPulseNotifiedListener(new PulseNotifiedListener() {
                @Override
                public void onConnectMasterDevice() {
                    Log.i("CONNECTED", "Connected");
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
        }

    }

    private static PulseHandlerInterface pulseHandler;

    public static boolean isConnected(){
        if(pulseHandler != null)
            return pulseHandler.isConnectMasterDevice();
        return false;
    }

    public static void SetColor(PulseColor color){
        if(pulseHandler.isConnectMasterDevice() && color != null)
            pulseHandler.SetBackgroundColor(color, true);
    }

    public static void SetPattern(PulseThemePattern pattern){
        if(pulseHandler.isConnectMasterDevice() && pattern != null)
            pulseHandler.SetLEDPattern(pattern);
    }

    public static void SetBrightness(int brightness){
        if(pulseHandler.isConnectMasterDevice() && brightness > 0)
            pulseHandler.SetBrightness(brightness);
    }
}
