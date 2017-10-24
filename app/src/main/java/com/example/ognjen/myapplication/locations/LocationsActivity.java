package com.example.ognjen.myapplication.locations;

import android.content.Intent;
import android.location.Location;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ognjen.myapplication.AddLocationActivity;
import com.example.ognjen.myapplication.mytrack.MyTrackActivity;
import com.example.ognjen.myapplication.R;
import com.example.ognjen.myapplication.model.Track;
import com.example.ognjen.myapplication.model.TracksMemory;

import static com.example.ognjen.myapplication.AddLocationActivity.RESULT_LOCATION;
import static com.example.ognjen.myapplication.mytrack.MyTrackActivity.LOG_TAG;
import static com.example.ognjen.myapplication.mytrack.MyTrackActivity.RESULT_LOCATIONS;
import static com.example.ognjen.myapplication.NewTrack.RESULT_DESCRIPTION;
import static com.example.ognjen.myapplication.NewTrack.RESULT_NAME;

public class LocationsActivity extends AppCompatActivity {
    public static final int ADD_LOCATION_REQUEST_CODE = 1;
    private ArrayAdapter<Location> locationArrayAdapter = null;
    private Track track;
    private boolean canAddLocations = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        Intent intent = getIntent();
        track = new Track(intent.getStringExtra(RESULT_NAME), intent.getStringExtra(RESULT_DESCRIPTION));

        ((TextView)findViewById(R.id.titleText)).setText(track.getName());
        ((TextView)findViewById(R.id.subTitleText)).setText(track.getDescription());

        if(intent.getParcelableArrayExtra(RESULT_LOCATIONS) != null){
            for(Parcelable locationParcelable : intent.getParcelableArrayExtra(RESULT_LOCATIONS)){
                track.getLocations().add((Location) locationParcelable);
            }
            findViewById(R.id.addLocation_button).setVisibility(View.GONE);
            canAddLocations = false;
        }
        locationArrayAdapter = new LocationsListAdapter(this, R.layout.locations_list_item, track.getLocations());
        ListView locationsList = (ListView) findViewById(R.id.locations_list);

        locationsList.setAdapter(locationArrayAdapter);
        locationsList.setEmptyView(findViewById(android.R.id.empty));

    }

    public void addLocation(View view) {
        Intent intent = new Intent(this, AddLocationActivity.class);
        this.startActivityForResult(intent, ADD_LOCATION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_LOCATION_REQUEST_CODE && resultCode == RESULT_OK){
            Location location  = data.getParcelableExtra(RESULT_LOCATION);
            Log.i(LOG_TAG, "Got location: " + location.getLatitude() + " " + location.getLongitude() + " " + location.getAltitude());
            locationArrayAdapter.add(location);
        }
    }

    public void done(View view) {
        if(canAddLocations){
            TracksMemory.getInstance().addTrack(track);
        }

        this.startActivity(new Intent(this, MyTrackActivity.class));
    }
}
