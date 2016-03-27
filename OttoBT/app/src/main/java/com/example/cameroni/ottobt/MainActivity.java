package com.example.cameroni.ottobt;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import android.content.Intent;
import android.app.PendingIntent;

import com.harman.pulsesdk.DeviceModel;
import com.harman.pulsesdk.ImplementPulseHandler;
import com.harman.pulsesdk.PulseColor;
import com.harman.pulsesdk.PulseHandlerInterface;
import com.harman.pulsesdk.PulseNotifiedListener;
import com.harman.pulsesdk.PulseThemePattern;

import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;

import android.content.ComponentName;

import android.media.Ringtone;
import android.media.MediaPlayer;
import android.media.MediaDataSource;

import java.util.Timer;
import java.util.TimerTask;

import android.provider.Settings;

public class MainActivity extends AppCompatActivity {


    public static Context applicationContext;
    public static Activity mainActivity;
    public static TextView debugOutput;
    public static String testStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applicationContext = getApplicationContext();
        mainActivity = this;
        debugOutput = (TextView)findViewById(R.id.DebugText);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



        /*
        findViewById(R.id.ConnectPulse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Otto_BluetoothManager.pulseHandler.registerPulseNotifiedListener(new PulseNotifiedListener() {
                    @Override
                    public void onConnectMasterDevice() {
                        PulseColor color = new PulseColor();
                        color.red = (byte) 255;
                        color.blue = (byte) 0;
                        color.green = (byte) 0;
                        Otto_BluetoothManager.pulseHandler.SetBackgroundColor(color, false);
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
        });
        */

        //Otto_BluetoothManager.pulseHandler = new ImplementPulseHandler();
        ///Otto_BluetoothManager manager = new Otto_BluetoothManager();
        //manager.StartBTManager(applicationContext);

        //Otto_BluetoothManager.SetupConnection("woo");

        Otto.OttoInit();

        Otto_Activity active = new Otto_Activity();
        CalendarDate date = new CalendarDate(active.scheduler, new Date());
        date.set(date.get(GregorianCalendar.YEAR),
                date.get(GregorianCalendar.MONTH),
                date.get(GregorianCalendar.DAY_OF_MONTH),
                date.get(GregorianCalendar.HOUR_OF_DAY),
                date.get(GregorianCalendar.MINUTE),
                date.get(GregorianCalendar.SECOND));

        Otto_Notification.AddNotification(active);
        active.playAlaram = true;
        active.duration = 5000;
        active.pulseEnabled = true;
        PulseColor color = new PulseColor();
        color.red = (byte)255;
        color.blue = (byte)0;
        color.green = (byte)0;
        active.pulseColor = color;
        active.pulseThemePattern = PulseThemePattern.PulseTheme_Firework;
        active.pulseBrightness = 75;
        active.AddCalendarDate(date);

        //RingtoneManager manager = new RingtoneManager(this);

        //Ringtone ringTone = manager.getRingtone(0);
        //Uri notification = manager.getRingtoneUri(0);
        //notification.
                //RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        //Settings.System.DEFAULT_RINGTONE_URL



        //player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        /*
        try {
            player.setDataSource(applicationContext, notification);
        }
        catch(IOException e){

        }
        */
        //player.setLooping(true);
        //player.start();

        /*
        WakeUpManager.Setup();
        Otto_Activity active = new Otto_Activity();
        CalendarDate date = new CalendarDate(active.scheduler, new Date());
        date.set(date.get(GregorianCalendar.YEAR),
                date.get(GregorianCalendar.MONTH),
                date.get(GregorianCalendar.DAY_OF_MONTH),
                date.get(GregorianCalendar.HOUR_OF_DAY),
                date.get(GregorianCalendar.MINUTE),
                date.get(GregorianCalendar.SECOND));

        Otto_Notification.AddNotification(active);
        active.AddCalendarDate(date);
        */

        /*
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List pkgAppsList = applicationContext.getPackageManager().queryIntentActivities( mainIntent, 0);
        */
        /*
        Intent nextIntent = new Intent(Intent.ACTION_MAIN);
        nextIntent.setComponent(new ComponentName("com.other.package","com.other.package.Activity"));
        startActivity(nextIntent);
        */

        //PackageManager pm = getPackageManager();
        //List<ApplicationInfo> apps = pm.getInstalledApplications(0);

        /*
        for(int i = 0; i < apps.size(); i++){
            if(apps.get(i).packageName == null)
                Log.i("ERROR", "NULL NAME!" );
            else

          Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
        startActivity(launchIntent);Log.i("PACKAGE NAME", apps.get(i).packageName);
            //Log.i("PACKAGE NAME", apps.get(i).packageName);
        }*/

        //Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
        //startActivity(launchIntent);Log.i("PACKAGE NAME", apps.get(i).packageName);
        //
        /*
        RingtoneManager manager = new RingtoneManager(this);
        manager.setType(RingtoneManager.TYPE_RINGTONE);
        Ringtone ringtone = manager.getRingtone(10);

        MediaPlayer player = new MediaPlayer();
        MediaDataSource dataSource = new MediaDataSource() {
            @Override
            public int readAt(long position, byte[] buffer, int offset, int size) throws IOException {
                return 0;
            }

            @Override
            public long getSize() throws IOException {
                return 0;
            }

            @Override
            public void close() throws IOException {

            }
        };
        player.setDataSource(manager.);

        ringtone.play();
        */
        /*
        for(int i = 0; i < 10; i++)
            ringtone.play();
*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
