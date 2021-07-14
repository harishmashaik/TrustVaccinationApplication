package com.team4.getvaxi.CLC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.recycle.BookingViewHolder;

import java.util.Arrays;
import java.util.List;

public class BookingConfirmActivity extends AppCompatActivity {

    public static final String TAG = "BookingConfirmActivity";

    AutoCompleteTextView asedit;

    EditText txtVaccineName;
    EditText txtchildName;
    EditText txtchildAge;
    EditText txtDateOfAppointment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);
        asedit = findViewById(R.id.Booking_AC_hospital_menu);

        txtVaccineName = findViewById(R.id.bookingCon_vaccinename);
        txtchildAge=findViewById(R.id.bookingCon_childage);
        txtchildName=findViewById(R.id.bookingCon_childname);
        txtDateOfAppointment=findViewById(R.id.bookingCon_appoDate);

        List<String> items = Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4");
        ArrayAdapter<String > adapter = new ArrayAdapter(BookingConfirmActivity.this, R.layout.booking_confirm_hoslist_layout, items);

        asedit.setAdapter(adapter);
        //(textField.editText as? AutoCompleteTextView)?.setAdapter(adapter);

        asedit.getText().toString();

        Intent intent = getIntent();

    if (intent.hasExtra(BookingViewHolder.booking)) {
       Booking eachBooking = intent.getParcelableExtra(BookingViewHolder.booking);
        txtVaccineName.setText(eachBooking.getVaccineName());
        txtchildName.setText(eachBooking.getName());
        txtchildAge.setText(eachBooking.getAge());
        txtDateOfAppointment.setText(eachBooking.getAppointmentDate());



    }







    }
}