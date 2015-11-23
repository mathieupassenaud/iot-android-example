package com.ovh.iot.ovhiotexample;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mathieu Passenaud on 11/11/15.
 */
public class Metric {

    private String metric;
    private long timestamp;
    private float value;
    private Map<String, String> tags = new HashMap<String, String>();

    public Metric(String metric, long timestamp, float value, Map<String, String> tags) {
        this.metric = metric;
        this.timestamp = timestamp;
        this.value = value;
        this.tags = tags;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
