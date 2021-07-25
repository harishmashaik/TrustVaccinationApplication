package com.team4.getvaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.card.MaterialCardView;

public class VaccineScheduleActivity extends AppCompatActivity {

  MaterialCardView matCardBelow11Months;
  MaterialCardView matCardBelow3Years;
  MaterialCardView matCardBelow6Years;
  MaterialCardView matCardBelow10Years;

  MaterialCardView matCardBelow02Months;
  MaterialCardView matCardBelow04Months;
  MaterialCardView matCardBelow06Months;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vaccine_schedule);

    matCardBelow02Months = findViewById(R.id.matCardvaccineSchedulebelow02M);
    matCardBelow04Months = findViewById(R.id.matCardvaccineSchedulebelow04M);
    matCardBelow06Months = findViewById(R.id.matCardvaccineSchedulebelow06M);



    matCardBelow11Months = findViewById(R.id.matCardvaccineSchedulebelow11M);
    matCardBelow3Years = findViewById(R.id.matCardvaccineSchedulebelow3Y);
    matCardBelow6Years = findViewById(R.id.matCardvaccineSchedulebelow6y);
    matCardBelow10Years = findViewById(R.id.matCardvaccineSchedulebelow10y);

    matCardBelow02Months.setOnClickListener(v -> vaccineByAGe("ageB02M"));
    matCardBelow04Months.setOnClickListener(v -> vaccineByAGe("ageB04M"));
    matCardBelow06Months.setOnClickListener(v -> vaccineByAGe("ageB06M"));

    matCardBelow11Months.setOnClickListener(v -> vaccineByAGe("ageB11M"));


    matCardBelow6Years.setOnClickListener(v -> vaccineByAGe("ageB06Y"));
    matCardBelow10Years.setOnClickListener(v -> vaccineByAGe("ageB10Y"));
  }

  private void vaccineByAGe(String ageRange) {

    Intent nxtActiv = new Intent(getApplicationContext(), VaccineByAgeActivity.class);
    nxtActiv.putExtra("AGEPARAM", ageRange);
    startActivity(nxtActiv);
  }
}
