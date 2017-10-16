package com.example.ognjen.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ognjen.myapplication.model.SettingsManager;

public class AddLocationActivity extends AppCompatActivity {

    public static final String RESULT_LOCATION = "ADD_LOCATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        Location defaultLocation = SettingsManager.getInstance().getLocation(SettingsManager.getInstance().getHomeCity());
        ((EditText) findViewById(R.id.latitudeEdit)).setText(Double.toString(defaultLocation.getLatitude()));
        ((EditText) findViewById(R.id.longitudeEdit)).setText(Double.toString(defaultLocation.getLongitude()));
        ((EditText) findViewById(R.id.altitudeEdit)).setText(Double.toString(defaultLocation.getAltitude()));
        ((EditText) findViewById(R.id.speedEdit)).setText(Float.toString(SettingsManager.getInstance().getSpeed()));
    }

    public void add(View view) {
        boolean hasErrors = false;
        EditText latitudeEdit = (EditText) findViewById(R.id.latitudeEdit);

        EditText longitudeEdit = (EditText) findViewById(R.id.longitudeEdit);
        String latitudeValue = latitudeEdit.getText().toString();
        if(latitudeValue.isEmpty()){
            latitudeEdit.setError("Latitude can not be empty");
            hasErrors = true;
        } else if(Double.parseDouble(latitudeValue) > 90 || Double.parseDouble(latitudeValue) < -90){
            latitudeEdit.setError("Latitude needs to be between 90 and -90 degrees");
            hasErrors = true;
        }



        String longitudeValue = longitudeEdit.getText().toString();
        if(longitudeValue.isEmpty()){
            longitudeEdit.setError("Longitude can not be empty");
            hasErrors = true;
        } else if(Double.parseDouble(longitudeValue) > 90 || Double.parseDouble(longitudeValue) < -90){
            longitudeEdit.setError("Longitude needs to be between 90 and -90 degrees");
            hasErrors = true;
        }


        EditText altitudeEdit = (EditText) findViewById(R.id.altitudeEdit);
        String altitudeValue = altitudeEdit.getText().toString();

        if(altitudeValue.isEmpty()){
            altitudeEdit.setError("Altitude can not be empty");
            hasErrors = true;
        }

        if(hasErrors){
            new AlertDialog.Builder(this).setMessage(R.string.add_track_error_message).setTitle(R.string.start_track_error_title)
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
        } else {
            Location location = new Location("myLocation");
            location.setLatitude(Double.parseDouble(latitudeValue));
            location.setLongitude(Double.parseDouble(longitudeValue));
            location.setAltitude(Double.parseDouble(altitudeValue));
            Intent result = new Intent();
            result.putExtra(RESULT_LOCATION, location);
            this.setResult(RESULT_OK, result);
            this.finish();
        }

    }
}
