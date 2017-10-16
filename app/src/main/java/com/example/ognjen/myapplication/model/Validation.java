package com.example.ognjen.myapplication.model;

import android.content.Context;
import android.content.DialogInterface;
import android.renderscript.Double2;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.example.ognjen.myapplication.R;

/**
 * Created by ognjen on 12.10.17..
 */

public class Validation {

    public static boolean validateEmpty(EditText editText, String fieldName){
        String editValue = editText.getText().toString();
        if(editValue.isEmpty()){
            if(editText.getError() == null){
                editText.setError(editText.getResources().getString(R.string.empty_text_error, fieldName));
            }
            return false;
        }
        return true;
    }

    public static boolean validateDegreeRange(EditText editText, String fieldName){
        String editValue = editText.getText().toString();
        if(editValue.isEmpty() || Double.valueOf(editValue) < -90 || Double.valueOf(editValue) > 90){
            if(editText.getError() == null){
                editText.setError(editText.getResources().getString(R.string.degree_range_error, fieldName));
            }
            return false;
        }
        return true;
    }

    public static void displayErrorDialog(Context context){
        new AlertDialog.Builder(context).setMessage(R.string.add_track_error_message).setTitle(R.string.start_track_error_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }
}
