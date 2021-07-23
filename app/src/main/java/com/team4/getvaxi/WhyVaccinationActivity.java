package com.team4.getvaxi;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

public class WhyVaccinationActivity extends AppCompatActivity {

  private Toolbar toolbar;

  Button butMakingDesicion;
  Button butWellChildVisit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_why_vaccination);

    toolbar = findViewById(R.id.topAppBar);
    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    toolbar.setTitle("Whyvaccination");
    toolbar.inflateMenu(R.menu.top_app_bar);

    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

      @Override
      public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
          case R.id.appbar_home:
            finish();
            return true;
        }

        return false;


      }
    });

    butMakingDesicion = findViewById(R.id.why_vaci_AC_making_vaccine_decison);
    butMakingDesicion.setOnClickListener(v->navigateURL("https://www.cdc.gov/vaccines/parents/why-vaccinate/vaccine-decision.html"));

    butWellChildVisit = findViewById(R.id.why_vaci_AC_well_child_visits);
    butWellChildVisit.setOnClickListener
            (v->navigateURL("https://www.cdc.gov/vaccines/parents/visit/vaccination-during-COVID-19.html?CDC_AA_refVal=https%3A%2F%2Fwww.cdc.gov%2Fvaccines%2Fparents%2Fwhy-vaccinate%2Fwell-child-visits.html"));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.top_app_bar,menu);
    return true;
  }


  public void navigateURL(String getLink) {

      Intent i = new Intent(Intent.ACTION_VIEW);
      i.setData(Uri.parse(getLink));
      startActivity(i);
  }
}
