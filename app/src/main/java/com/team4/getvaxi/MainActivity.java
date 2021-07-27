package com.team4.getvaxi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final Handler handler = new Handler();
    handler.postDelayed(
        new Runnable() {
          @Override
          public void run() {
            Intent homeIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(homeIntent);
            finish();
          }
        },
        3000);
  }
}
