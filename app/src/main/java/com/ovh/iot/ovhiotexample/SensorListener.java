package com.ovh.iot.ovhiotexample;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

/**
 * Created by Mathieu Passenaud on 11/11/15.
 */
public class SensorListener implements SensorEventListener {

    public SensorListener(){
    }

    public void onSensorChanged(SensorEvent event){

        // sensors can return 3 datas (3 axis)
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".x", event.values[0]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".y", event.values[1]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".z", event.values[2]);
            return;
        }

        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".x", event.values[0]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".y", event.values[1]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".z", event.values[2]);
            return;
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".x", event.values[0]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".y", event.values[1]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".z", event.values[2]);
            return;
        }

        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".x", event.values[0]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".y", event.values[1]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".z", event.values[2]);
            return;
        }
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".x", event.values[0]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".y", event.values[1]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".z", event.values[2]);
            return;
        }

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".x", event.values[0]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".y", event.values[1]);
            SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType())+".z", event.values[2]);
            return;
        }
        SensorsValues.getInstance().pushValue(getSensorTypeAsString(event.sensor.getType()), event.values[0]);
        Log.v("SENSORS", "new data");
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    private String getSensorTypeAsString(int sensorType){
        String sensorName="UNKNOWN";
        switch (sensorType) {
            case 1:  sensorName = "ACCELEROMETER";
                break;
            case 2:  sensorName = "MAGNETIC";
                break;
            case 3:  sensorName = "ORIENTATION";
                break;
            case 4:  sensorName = "GYROSCOPE";
                break;
            case 5:  sensorName = "LIGHT";
                break;
            case 6:  sensorName = "PRESSURE";
                break;
            case 7:  sensorName = "TEMPERATURE";
                break;
            case 8:  sensorName = "PROXIMITY";
                break;
            case 9:  sensorName = "GRAVITY";
                break;
            case 10: sensorName = "ACCELERATION";
                break;
            case 11: sensorName = "ROTATION";
                break;
            case 12: sensorName = "HUMIDITY";
                break;
            case 13: sensorName = "AMBIENT_TEMPERATURE";
                break;
            case 14: sensorName = "MAGNETIC_FIELD_UNCALIBRATED";
                break;
            case 15: sensorName = "GAME_ROTATION_VECTOR";
                break;
            case 16: sensorName = "GYROSCOPE_UNCALIBRATED";
                break;
            case 17: sensorName = "SIGNIFICANT_MOTION";
                break;
            case 18: sensorName = "STEP_DETECTOR";
                break;
            case 19: sensorName = "STEP_COUNTER";
                break;
            case 20: sensorName = "GEOMAGNETIC_ROTATION_VECTOR";
                break;
            default: sensorName = "UNKNOWN";
                break;
        }
        return sensorName;
    }
}

