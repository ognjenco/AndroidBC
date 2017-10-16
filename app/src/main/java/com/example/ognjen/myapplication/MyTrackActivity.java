package com.example.ognjen.myapplication;

import android.content.Intent;
import android.location.Location;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ognjen.myapplication.locations.LocationsActivity;
import com.example.ognjen.myapplication.model.Track;
import com.example.ognjen.myapplication.model.TracksMemory;

import java.util.ArrayList;
import java.util.List;

import static com.example.ognjen.myapplication.NewTrack.RESULT_DESCRIPTION;
import static com.example.ognjen.myapplication.NewTrack.RESULT_NAME;

public class MyTrackActivity extends AppCompatActivity {
    public static final int NEW_TRACK_CODE = 1;
    public static final String LOG_TAG = "myTrack_log";
    public static final String RESULT_LOCATIONS = "LOCATIONS";
    public static final String SAVED_TRACKS = "SAVED_TRACKS";
    private static List<Track> trackList = new ArrayList<>();
    ArrayAdapter<Track> tracksAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_track);
        Intent intent = this.getIntent();

        tracksAdapter = new ArrayAdapter<Track>(this, android.R.layout.simple_list_item_1 , TracksMemory.getInstance().getTracks());
        ListView listView = (ListView) findViewById(R.id.tracksListView);
        listView.setAdapter(tracksAdapter);
        listView.setEmptyView(findViewById(android.R.id.empty));
//        trackList.add(new Track("My name", "My description"));
//        tracksAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track item = tracksAdapter.getItem(position);
                Log.i(LOG_TAG, item.getName() + " : " + item.getDescription() + " was clicked");
                Intent locationsIntent = new Intent(getApplicationContext(), LocationsActivity.class);
                locationsIntent.putExtra(RESULT_NAME, item.getName());
                locationsIntent.putExtra(RESULT_DESCRIPTION, item.getDescription());
                locationsIntent.putExtra(RESULT_LOCATIONS, item.getLocations().toArray(new Location[]{}));
                startActivity(locationsIntent);
            }
        });
    }



    public void handleSettings(View view) {
        this.startActivity(new Intent(this, SettingsActivity.class));
    }



    public void newTrack(View view) {
        Intent intent = new Intent(getApplicationContext(), NewTrack.class);
        this.startActivity(intent);

    }
}
