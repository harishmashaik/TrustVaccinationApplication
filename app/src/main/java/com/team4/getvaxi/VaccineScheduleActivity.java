package com.team4.getvaxi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.material.card.MaterialCardView;

public class VaccineScheduleActivity extends AppCompatActivity {
  private Toolbar toolbar;

  MaterialCardView matCardBelow11Months;
  MaterialCardView matCardBelow3Years;
  MaterialCardView matCardBelow6Years;
  MaterialCardView matCardBelow10Years;

  MaterialCardView matCardBelow18Years;
  MaterialCardView matCardBelow02Months;
  MaterialCardView matCardBelow04Months;
  MaterialCardView matCardBelow06Months;

  @RequiresApi(api = Build.VERSION_CODES.R)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vaccine_schedule);
    toolbar = findViewById(R.id.topAppBar);
    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    toolbar.setTitle(Commons.getActivityName(getClass().getSimpleName()));
    toolbar.inflateMenu(R.menu.top_app_bar);
    toolbar.setOnMenuItemClickListener(item -> {
      switch (item.getItemId()){
        case R.id.appbar_home:
          startActivity(new Intent(getApplicationContext(),HomeActivity.class));
          finish();
          return true;
      }
      return false; });

    matCardBelow02Months = findViewById(R.id.matCardvaccineSchedulebelow02M);
    matCardBelow04Months = findViewById(R.id.matCardvaccineSchedulebelow04M);
    matCardBelow06Months = findViewById(R.id.matCardvaccineSchedulebelow06M);
    matCardBelow11Months = findViewById(R.id.matCardvaccineSchedulebelow11M);
    matCardBelow3Years = findViewById(R.id.matCardvaccineSchedulebelow3Y);
    matCardBelow6Years = findViewById(R.id.matCardvaccineSchedulebelow6y);
    matCardBelow10Years = findViewById(R.id.matCardvaccineSchedulebelow10y);
    matCardBelow18Years= findViewById(R.id.matCardvaccineSchedulebelow18y);

    matCardBelow02Months.setOnClickListener(v -> vaccineByAGe("ageB02M"));
    matCardBelow04Months.setOnClickListener(v -> vaccineByAGe("ageB04M"));
    matCardBelow06Months.setOnClickListener(v -> vaccineByAGe("ageB06M"));

    matCardBelow11Months.setOnClickListener(v -> vaccineByAGe("ageB11M"));
    matCardBelow3Years.setOnClickListener(v -> vaccineByAGe("ageB02Y"));
    matCardBelow18Years.setOnClickListener(v -> vaccineByAGe("ageB18Y"));


    matCardBelow6Years.setOnClickListener(v -> vaccineByAGe("ageB06Y"));
    matCardBelow10Years.setOnClickListener(v -> vaccineByAGe("ageB10Y"));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.top_app_bar,menu);
    return true;
  }

  private void vaccineByAGe(String ageRange) {

    Intent nxtActiv = new Intent(getApplicationContext(), VaccineByAgeActivity.class);
    nxtActiv.putExtra("AGEPARAM", ageRange);
    startActivity(nxtActiv);
  }
}
