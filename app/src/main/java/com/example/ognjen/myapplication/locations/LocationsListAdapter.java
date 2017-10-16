package com.example.ognjen.myapplication.locations;

import android.content.Context;
import android.location.Location;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ognjen on 10.10.17..
 */

public class LocationsListAdapter extends ArrayAdapter<Location> {

    public LocationsListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public LocationsListAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public LocationsListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Location[] objects) {
        super(context, resource, objects);
    }

    public LocationsListAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull Location[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public LocationsListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Location> objects) {
        super(context, resource, objects);
    }

    public LocationsListAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<Location> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Location item = this.getItem(position);
        TextView locationText;
        if(convertView == null){
            locationText = new TextView(parent.getContext());
        } else {
            locationText = (TextView) convertView;
        }
        locationText.setText(String.format("Longitude: %f\nLatitude: %f\nAltitude: %f", item.getLongitude(), item.getLatitude(), item.getAltitude()));
        return locationText;
    }
}
