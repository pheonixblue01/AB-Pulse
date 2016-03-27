package com.example.cameroni.ottobt;

/**
 * Created by Cameroni on 3/26/2016.
 */
public class Otto_Alert extends Otto_Notification implements ID_Owner {
    @Override
    public void RunNotification(){
        //Otto_BluetoothManager.SetupConnection(preferredMac);
        if(pulseEnabled && Otto_PulseHandler.isConnected()){
            Otto_PulseHandler.SetColor(pulseColor);
            Otto_PulseHandler.SetPattern(pulseThemePattern);
            Otto_PulseHandler.SetBrightness(pulseBrightness);
        }


        //Otto_BluetoothManager.DisconnectFromDevice();
    }

}
