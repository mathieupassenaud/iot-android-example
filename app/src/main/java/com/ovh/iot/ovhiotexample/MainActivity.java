package com.ovh.iot.ovhiotexample;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathieu Passenaud on 11/11/15.
 */
public class MainActivity extends ActionBarActivity {

    private SensorManager mSensorManager;


    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Preferences.getInstance().setPrefs(this.getSharedPreferences("com.ovh.iot.example.app", Context.MODE_PRIVATE));
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        setContentView(R.layout.activity_main);
        getAvailableSensors();

        final Button fab = (Button) findViewById(R.id.button);
        final Button stopbutton = (Button) findViewById(R.id.stop);
        final EditText tokenId = (EditText) findViewById(R.id.tokenID);
        final EditText tokenSecret = (EditText) findViewById(R.id.TockenSecret);
        final EditText sourceName = (EditText) findViewById(R.id.deviceId);
        final TextView statusText = (TextView) findViewById(R.id.status);

        tokenId.setText(Preferences.getInstance().getTokenId());
        tokenSecret.setText(Preferences.getInstance().getTokenSecret());
        sourceName.setText(Preferences.getInstance().getSourceName());

        final Pusher pusher = new Pusher();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pusher.setTokenId(tokenId.getText().toString());
                pusher.setTokenSecret(tokenSecret.getText().toString());
                fab.setEnabled(false);
                stopbutton.setEnabled(true);
                Preferences.getInstance().setTokenId(tokenId.getText().toString());
                Preferences.getInstance().setTokenSecret(tokenSecret.getText().toString());
                Preferences.getInstance().setSourceName(sourceName.getText().toString());
                final Thread threadPusher = new Thread(pusher);
                Log.v("MAIN", "launch pusher");
                // launch capture !
                List<Integer> checkedSensors = getCheckedSensors();
                for (int sensorType : checkedSensors) {
                    // all data stored in SensorsValues object
                    launchGetSensorValue(sensorType);
                }
                threadPusher.start();
                // This gets executed in a non-UI thread:


                stopbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        threadPusher.interrupt();
                        stopSensors();
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        /*fab.setEnabled(true);
                        stopbutton.setEnabled(false);
                        */
                    }
                });
            }
        });


    }
    public void getAvailableSensors(){

        CheckBox accelerometer = (CheckBox) findViewById(R.id.accelerometer);
        CheckBox ambiantTemperature = (CheckBox) findViewById(R.id.ambiantTemperature);
        CheckBox gravtity = (CheckBox) findViewById(R.id.Gravity);
        CheckBox gyroscope = (CheckBox) findViewById(R.id.Gyroscope);
        CheckBox light = (CheckBox) findViewById(R.id.Light);
        CheckBox linearAcceleration = (CheckBox) findViewById(R.id.LinearAcceleration);
        CheckBox magneticField = (CheckBox) findViewById(R.id.MagneticField);
        CheckBox pressure = (CheckBox) findViewById(R.id.Pressure);
        CheckBox proximity = (CheckBox) findViewById(R.id.Proximity);
        CheckBox relativeHumidity = (CheckBox) findViewById(R.id.RelativeHumidity);
        CheckBox rotation = (CheckBox) findViewById(R.id.Rotation);
        CheckBox temperature = (CheckBox) findViewById(R.id.Temperature);

        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s : deviceSensors){
            if(s.getType() == Sensor.TYPE_ACCELEROMETER){
                accelerometer.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
                ambiantTemperature.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_GRAVITY){
                gravtity.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_GYROSCOPE){
                gyroscope.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_LIGHT){
                light.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
                linearAcceleration.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                magneticField.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_PRESSURE){
                pressure.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_PROXIMITY){
                proximity.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_RELATIVE_HUMIDITY){
                relativeHumidity.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_ROTATION_VECTOR){
                rotation.setEnabled(true);
            }
            if(s.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
                temperature.setEnabled(true);
            }
        }
    }

    public List<Integer> getCheckedSensors(){
        CheckBox accelerometer = (CheckBox) findViewById(R.id.accelerometer);
        CheckBox ambiantTemperature = (CheckBox) findViewById(R.id.ambiantTemperature);
        CheckBox gravity = (CheckBox) findViewById(R.id.Gravity);
        CheckBox gyroscope = (CheckBox) findViewById(R.id.Gyroscope);
        CheckBox light = (CheckBox) findViewById(R.id.Light);
        CheckBox linearAcceleration = (CheckBox) findViewById(R.id.LinearAcceleration);
        CheckBox magneticField = (CheckBox) findViewById(R.id.MagneticField);
        CheckBox pressure = (CheckBox) findViewById(R.id.Pressure);
        CheckBox proximity = (CheckBox) findViewById(R.id.Proximity);
        CheckBox relativeHumidity = (CheckBox) findViewById(R.id.RelativeHumidity);
        CheckBox rotation = (CheckBox) findViewById(R.id.Rotation);
        CheckBox temperature = (CheckBox) findViewById(R.id.Temperature);

        List<Integer> checkedSensors = new ArrayList<Integer>();
        if(accelerometer.isChecked()){
            checkedSensors.add(Sensor.TYPE_ACCELEROMETER);
        }
        if(ambiantTemperature.isChecked()){
            checkedSensors.add(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }
        if(gravity.isChecked()){
            checkedSensors.add(Sensor.TYPE_GRAVITY);
        }
        if(gyroscope.isChecked()){
            checkedSensors.add(Sensor.TYPE_GYROSCOPE);
        }
        if(light.isChecked()){
            checkedSensors.add(Sensor.TYPE_LIGHT);
        }
        if(linearAcceleration.isChecked()){
            checkedSensors.add(Sensor.TYPE_LINEAR_ACCELERATION);
        }
        if(magneticField.isChecked()){
            checkedSensors.add(Sensor.TYPE_MAGNETIC_FIELD);
        }
        if(pressure.isChecked()){
            checkedSensors.add(Sensor.TYPE_PRESSURE);
        }
        if(proximity.isChecked()){
            checkedSensors.add(Sensor.TYPE_PROXIMITY);
        }
        if(relativeHumidity.isChecked()){
            checkedSensors.add(Sensor.TYPE_RELATIVE_HUMIDITY);
        }
        if(rotation.isChecked()){
            checkedSensors.add(Sensor.TYPE_ROTATION_VECTOR);
        }
        if(temperature.isChecked()){
            checkedSensors.add(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }
        return checkedSensors;
    }

    private List<SensorListener> listeners = new ArrayList<SensorListener>();
    public void launchGetSensorValue(int sensorType){
        SensorListener listener = new SensorListener();
        listeners.add(listener);
        mSensorManager.registerListener(listener, mSensorManager.getDefaultSensor(sensorType), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopSensors() {
        for (SensorListener listener : listeners) {
            mSensorManager.unregisterListener(listener);
        }
    }
}
