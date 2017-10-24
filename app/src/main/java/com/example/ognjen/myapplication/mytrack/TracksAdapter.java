package com.example.ognjen.myapplication.mytrack;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ognjen.myapplication.model.Track;

import java.util.List;

/**
 * Created by ognjen on 23.10.17..
 */

public class TracksAdapter extends ArrayAdapter<Track> {
    private int resoucreId;

    public TracksAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public TracksAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public TracksAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Track[] objects) {
        super(context, resource, objects);
    }

    public TracksAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull Track[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public TracksAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Track> objects) {
        super(context, resource, objects);
        this.resoucreId = resource;
    }

    public TracksAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<Track> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = ((Activity) this.getContext()).getLayoutInflater();
            convertView = layoutInflater.inflate(this.resoucreId, parent);
            // TODO set layout tag and view holder fields
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Track item = this.getItem(position);
        holder.nameText.setText(item.getName());
        holder.descriptionText.setText(item.getDescription());
        holder.locationCountText.setText(item.getLocations().size());

        return convertView;
    }

    static class ViewHolder {
        TextView nameText;
        TextView descriptionText;
        TextView locationCountText;
    }

}
