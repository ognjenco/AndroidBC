package com.example.ognjen.myapplication.model;

import android.location.Location;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ognjen on 12.10.17..
 */

public class SettingsManager {
    public static Map<String, Location> cityLocationMap;
    static {
        cityLocationMap = new HashMap<>();
        Location belgradeLocation = new Location("homeCity");
        belgradeLocation.setLongitude(50);
        belgradeLocation.setLatitude(40.543);
        belgradeLocation.setAltitude(200);

        cityLocationMap.put("Belgrade", belgradeLocation);

        Location londonLocation = new Location("homeCity");
        londonLocation.setLongitude(60);
        londonLocation.setLatitude(35.323);
        londonLocation.setAltitude(43.543);
        cityLocationMap.put("London", londonLocation);

    }


    private int sampleRate;
    private float speed;
    private String homeCity;

    public int getSampleRate() {
        return sampleRate;
    }

    public float getSpeed() {
        return speed;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public Location getLocation(String city){
        return cityLocationMap.get(city);
    }

    public void setSettings(int sampleRate, float speed, String chosenCity){
        this.sampleRate = sampleRate;
        this.speed = speed;
        this.homeCity = chosenCity;
    }

    private static SettingsManager instance;

    private SettingsManager() {
        this.speed = 100;
        this.sampleRate = 10;
        this.homeCity = "Belgrade";
    }

    public static SettingsManager getInstance(){
        if(instance == null){
            instance = new SettingsManager();
        }
        return instance;
    }
}
