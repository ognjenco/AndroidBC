package com.example.ognjen.myapplication.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ognjen on 12.10.17..
 */

public class TracksMemory {
    private List<Track> tracksList;
    private TracksMemory(){
        tracksList = new ArrayList<>();
    }

    public List<Track> getTracks(){
        return Collections.unmodifiableList(this.tracksList);
    }

    public void addTrack(Track newTrack){
        tracksList.add(newTrack);
    }

    private static TracksMemory instance;

    public static TracksMemory getInstance() {
        if(instance == null){
            instance = new TracksMemory();
        }
        return instance;
    }
}
