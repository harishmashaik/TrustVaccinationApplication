package com.team4.getvaxi.CLC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.team4.getvaxi.R;

import java.util.Arrays;
import java.util.List;

public class BookingConfirmActivity extends AppCompatActivity {

    AutoCompleteTextView asedit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);
        asedit = findViewById(R.id.Booking_AC_hospital_menu);

        List<String> items = Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4");
        ArrayAdapter<String > adapter = new ArrayAdapter(BookingConfirmActivity.this, R.layout.booking_confirm_hoslist_layout, items);

        asedit.setAdapter(adapter);
        //(textField.editText as? AutoCompleteTextView)?.setAdapter(adapter);




    }
}