package com.example.ognjen.myapplication;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ognjen.myapplication.model.SettingsManager;
import com.example.ognjen.myapplication.model.Validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        ArrayList<String> cities = new ArrayList<>(SettingsManager.cityLocationMap.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner citiesSpinner = (Spinner) findViewById(R.id.citySpinner);
        citiesSpinner.setAdapter(adapter);
        String homeCity = SettingsManager.getInstance().getHomeCity();
        int sampleRate = SettingsManager.getInstance().getSampleRate();
        ((EditText) findViewById(R.id.sampleRateEdit)).setText(Integer.toString(sampleRate));
        float speed = SettingsManager.getInstance().getSpeed();
        ((EditText) findViewById(R.id.speedEdit)).setText(Float.toString(speed));
        citiesSpinner.setSelection(cities.indexOf(homeCity));
        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String chosenCity = (String)parent.getItemAtPosition(position);
                setLocation(chosenCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setLocation(String chosenCity) {
        Location chosenLocation = SettingsManager.getInstance().getLocation(chosenCity);
        ((TextView)findViewById(R.id.locationSettingsText)).setText(getResources().getString(R.string.city_location_info_format, chosenLocation.getLatitude(), chosenLocation.getLongitude(), chosenLocation.getAltitude()));
    }

    public void setSettings(View view){

        String selectedCity = (String) ((Spinner) findViewById(R.id.citySpinner)).getSelectedItem();
        EditText speedEdit = (EditText) findViewById(R.id.speedEdit);
        EditText sampleRateEdit = (EditText) findViewById(R.id.sampleRateEdit);


        if(!(Validation.validateEmpty(speedEdit, "Speed") && Validation.validateEmpty(sampleRateEdit, "Sample Rate"))){
            Validation.displayErrorDialog(this);
        } else {
            SettingsManager.getInstance().setSettings(Integer.parseInt(sampleRateEdit.getText().toString()), Float.parseFloat(speedEdit.getText().toString()), selectedCity);
            this.startActivity(new Intent(this, MyTrackActivity.class));
        }
    }

}
