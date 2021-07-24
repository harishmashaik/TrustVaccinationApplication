package com.team4.getvaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.Inet4Address;

public class CLCHomeActivity extends AppCompatActivity {

    TextView newBookings;
    TextView confirmedBookings;
    TextView vaccineStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_l_c_home);

        newBookings = findViewById(R.id.clc_home_new_bookings);
        confirmedBookings = findViewById(R.id.clc_home_confirmed_bookings);
        vaccineStore = findViewById(R.id.clc_home_vaccine_stock);

        vaccineStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newBookingsActivity = new Intent(getApplicationContext(),CLCVaccineStoreActivity.class);
                startActivity(newBookingsActivity);
            }
        });

        newBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newBookingsActivity = new Intent(getApplicationContext(),CLCNewBookingsActivity.class);
                startActivity(newBookingsActivity);
            }
        });



    }
}