package com.ovh.iot.ovhiotexample;

import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import java.io.*;
import java.net.*;

/**
 * Created by Mathieu Passenaud on 11/11/15.
 */
public class Pusher implements Runnable{
    private String tokenId;
    private String tokenSecret;
    private boolean loop = true;

    public Pusher(){
    }

    public void setTokenId(String tokenId){
        this.tokenId=tokenId;
    }

    public void setTokenSecret(String tokenSecret){
        this.tokenSecret=tokenSecret;
    }

    @Override
    public void run() {
        while(loop) {

            if(Thread.interrupted()){
                loop = false;
            }
            try {
                // get all data
                if (SensorsValues.getInstance().getMetricsSize() < 30) {
                    Thread.sleep(500);
                    Log.v("PUSHER", "Wait Data");
                }
                List<Metric> metricList = SensorsValues.getInstance().getMetricsAndPurge();

                // TODO push data
                Log.v("PUSHER", "push data");
                pushData(metricList);
                Thread.sleep(500);
            }catch(InterruptedException inex){
                loop = false;
            }
        }
    }

    public void pushData(List<Metric> metrics){
        HttpURLConnection conn = null;

        String input = new Gson().toJson(metrics).toString();

        Log.v("PUSHER", input);
        try {
            // Initialize connection parameters
            URL url = new URL("https://opentsdb.iot.runabove.io/api/put");
            conn = (HttpURLConnection) url.openConnection();

            // Set HTTP Basic Auth
            conn.setRequestProperty("Authorization", "Basic " + Base64.encodeToString((tokenId + ":" + tokenSecret).getBytes(), Base64.DEFAULT));

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // Send the payload as outputstream
            OutputStream requestBody = conn.getOutputStream();
            requestBody.write(input.getBytes());
            requestBody.flush();

            // Parse the response from the server
            if (conn.getResponseCode() >= 400) {
                // Error from the server
                BufferedReader responseBody = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
                // Print the response body in stdout
                String output;
                while ((output = responseBody.readLine()) != null) {
                    Log.v("PUSHER", output);
                }
                Log.v("PUSHER", "Send failed, HTTP error code : " + conn.getResponseCode() + "\n Output server : " + output);
            } else {
                // No error from the server
                BufferedReader responseBody = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                // Print the response body in stdout
                Log.v("PUSHER", "Send successful\n" + metrics.size() + " datas.");
            }

        } catch (IOException e) {
            Log.v("PUSHER", "IOException " + e.getMessage());
        } finally {
            conn.disconnect();
        }
    }
}
