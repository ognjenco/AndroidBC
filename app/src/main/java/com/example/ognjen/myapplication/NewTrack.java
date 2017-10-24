package com.example.ognjen.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ognjen.myapplication.locations.LocationsActivity;
import com.example.ognjen.myapplication.model.Validation;

import static com.example.ognjen.myapplication.mytrack.MyTrackActivity.LOG_TAG;

public class NewTrack extends AppCompatActivity {

    public static final String RESULT_NAME = "resultName";
    public static final String RESULT_DESCRIPTION = "resultDescription";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_track);
    }

    public void startTrack(View view) {
        Log.i(LOG_TAG, "in startTrack handler");
        EditText nameEdit = (EditText) findViewById(R.id.name_edit);
        EditText descriptionEdit = (EditText) findViewById(R.id.description_edit);
        boolean validName = Validation.validateEmpty(nameEdit, "Name");
        boolean validDescription = Validation.validateEmpty(descriptionEdit, "Description");
        if(!(validName && validDescription)){
            Validation.displayErrorDialog(this);
        } else {
            Intent intent = new Intent(this, LocationsActivity.class);
            intent.putExtra(RESULT_NAME, nameEdit.getText().toString());
            intent.putExtra(RESULT_DESCRIPTION, descriptionEdit.getText().toString());
            Log.i(LOG_TAG, "putting result back to my tracks");
            this.startActivity(intent);
        }

    }
}
