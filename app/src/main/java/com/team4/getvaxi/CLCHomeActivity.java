package com.team4.getvaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.net.Inet4Address;

public class CLCHomeActivity extends AppCompatActivity {

    TextView newBookings;
    TextView confirmedBookings;
    TextView vaccineStore;
    ProgressDialog proload;


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
                loadProgressDialog();
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

    public void loadProgressDialog(){
        proload = new ProgressDialog(CLCHomeActivity.this);
        proload.setMax(100);
        proload.setMessage("Please wait...");
        proload.setTitle("My Application");
        proload.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        proload.show();
        final Handler handle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                proload.incrementProgressBy(1);
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (proload.getProgress() <= proload
                            .getMax()) {
                        Thread.sleep(30);
                        handle.sendMessage(handle.obtainMessage());
                        if (proload.getProgress() == proload
                                .getMax()) {
                            proload.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}