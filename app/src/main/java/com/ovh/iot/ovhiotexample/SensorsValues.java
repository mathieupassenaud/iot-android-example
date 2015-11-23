package com.ovh.iot.ovhiotexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mathieu Passenaud on 11/11/15.
 *
 * This class is used as a buffer for sensors values
 */
public class SensorsValues {
    private static SensorsValues _instance = new SensorsValues();

  private List<Metric> metrics = new ArrayList<Metric>();
    private SensorsValues(){}

    public static SensorsValues getInstance(){
        return _instance;
    }

    public void pushValue(String sensorName, float value){
        Map<String, String> tags = new HashMap<String, String>();
        tags.put("source", Preferences.getInstance().getSourceName());
        metrics.add(new Metric(sensorName.replace(' ', '_'), System.currentTimeMillis(), value, tags));
    }

    public int getMetricsSize(){
        return metrics.size();
    }

    public List<Metric> getMetricsAndPurge(){
        List<Metric> toReturn = metrics;
        metrics = new ArrayList<Metric>();
        return toReturn;
    }
}
