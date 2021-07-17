package com.team4.getvaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.datepicker.MaterialTextInputPicker;
import com.google.android.material.textview.MaterialTextView;

public class SampleTestActivity extends AppCompatActivity {

    public static final String TAG = "SampleTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_test);

        LinearLayout linearLayout = findViewById(R.id.editTextContainer);


        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();

        materialDateBuilder.setTitleText("SELECT A DATE");

        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

        // Create EditText
        final EditText childInfoName = new EditText(this);
        childInfoName.setHint("Enter Yours");
        childInfoName.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        childInfoName.setPadding(20, 20, 20, 20);

        final EditText childInfoAge = new EditText(this);
        childInfoAge.setHint("Enter Yours");
        childInfoAge.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        childInfoAge.setPadding(20, 20, 20, 20);

        final Button datePickButton = new Button(this);
        datePickButton.setText("Pickdate");
        datePickButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        datePickButton.setPadding(20, 20, 20, 20);
        datePickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            }
        });


        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {

                        // if the user clicks on the positive
                        // button that is ok button update the
                        // selected date
                        childInfoAge.setText(materialDatePicker.getHeaderText());
                        // in the above statement, getHeaderText
                        // is the selected date preview from the
                        // dialog
                    }
                });





        // Add EditText to LinearLayout
        if (linearLayout != null) {
            linearLayout.addView(childInfoName);
            linearLayout.addView(childInfoAge);
            linearLayout.addView(datePickButton);

        }

        Button btnShow = findViewById(R.id.btnShow);
        if (btnShow != null) {
            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(MainActivity.this, editText.getText(), Toast.LENGTH_LONG).show();

                    Log.i(TAG,childInfoName.getText().toString() );
                    Log.i(TAG,childInfoAge.getText().toString() );

                }
            });
        }

    }
}