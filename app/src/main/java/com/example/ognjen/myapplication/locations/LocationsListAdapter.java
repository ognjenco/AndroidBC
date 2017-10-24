package com.example.ognjen.myapplication.locations;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ognjen.myapplication.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by ognjen on 10.10.17..
 */

public class LocationsListAdapter extends ArrayAdapter<Location> {

    private int resourceId;
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
        this.resourceId = resource;
    }

    public LocationsListAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<Location> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Location item = this.getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){
            LayoutInflater inflater = ((Activity) this.getContext()).getLayoutInflater();
            convertView = inflater.inflate(this.resourceId, parent, false);
            viewHolder.longitudeText = convertView.findViewById(R.id.longitudeText);
            viewHolder.latitudeText = convertView.findViewById(R.id.latitudeText);
            viewHolder.altitudeText = convertView.findViewById(R.id.altitudeText);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.longitudeText.setText(getContext().getResources().getString(R.string.longitude_list_label, item.getLongitude()));
        viewHolder.latitudeText.setText(getContext().getResources().getString(R.string.latitude_list_label, item.getLatitude()));
        viewHolder.altitudeText.setText(getContext().getResources().getString(R.string.altitude_list_label, item.getAltitude()));
        return convertView;
    }

    static class ViewHolder {
        TextView longitudeText;
        TextView latitudeText;
        TextView altitudeText;
    }
}
