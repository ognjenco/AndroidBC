package com.example.ognjen.myapplication.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ognjen on 9.10.17..
 */

public class Track implements Parcelable{
    private String name;
    private String description;

    private List<Location> locations = new ArrayList<>();

    public Track(String name, String description) {
        this.name = name;
        this.description = description;

    }

    protected Track(Parcel in) {
        name = in.readString();
        description = in.readString();
        locations = in.createTypedArrayList(Location.CREATOR);
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return this.name + " : " + this.description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeParcelableArray(this.locations.toArray(new Location[]{}), 0);
    }
}
