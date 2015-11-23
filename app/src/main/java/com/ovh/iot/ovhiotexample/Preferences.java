package com.ovh.iot.ovhiotexample;

import android.content.SharedPreferences;

/**
 * Created by Mathieu Passenaud on 12/11/15.
 */
public class Preferences {

    private String sourceName;
    private String tokenId;
    private String tokenSecret;

    private static Preferences instance = new Preferences();

    private SharedPreferences prefs;

    private Preferences(){

    }

    public static Preferences getInstance(){
        return instance;
    }

    public void setPrefs(SharedPreferences pref){
        prefs = pref;
    }

    public String getTokenId(){
        String tokenidKey = "com.ovh.iot.example.app.tokenid";
        return prefs.getString(tokenidKey, "tokenID");

    }
    public String getTokenSecret(){
        String tokensecretKey = "com.ovh.iot.example.app.tokensecret";
        return prefs.getString(tokensecretKey, "tokenSecret");
    }

    public void setTokenId(String tokenid){
        String tokenidKey = "com.ovh.iot.example.app.tokenid";
        prefs.edit().putString(tokenidKey, tokenid).apply();
    }
    public void setTokenSecret(String tokenSecret){
        String tokensecretKey = "com.ovh.iot.example.app.tokensecret";
        prefs.edit().putString(tokensecretKey, tokenSecret).apply();
    }

    public void setSourceName(String sourceName){
        String deviceIdKey = "com.ovh.iot.example.app.deviceid";
        prefs.edit().putString(deviceIdKey, sourceName).apply();
    }

    public String getSourceName(){
        String deviceIdKey = "com.ovh.iot.example.app.deviceid";
        return prefs.getString(deviceIdKey, android.os.Build.MODEL);
    }

}
