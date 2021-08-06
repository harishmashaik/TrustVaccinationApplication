package com.team4.getvaxi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Vaccine;

import java.util.HashMap;
import java.util.Map;

public class CLCVaccineStoreUpdateActivity extends AppCompatActivity {

  private Toolbar toolbar;
  Vaccine currentVaccine = new Vaccine();
  String currentKey="";
  HashMap<String,Vaccine> currentVaccineMap = new HashMap<>();
  FirebaseFirestore db = FirebaseFirestore.getInstance();

  TextView clcStoreVaccineName;
  TextView clcStoreVaccineDose;
  EditText clcStoreVaccineStock;
  Button clcStoreVaccineUpdate;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_c_l_c_vaccine_store_update);

    toolbar = findViewById(R.id.topAppBar);
    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    toolbar.setTitle(Commons.getActivityName(getClass().getSimpleName()));
    toolbar.inflateMenu(R.menu.top_app_bar);

    toolbar.setOnMenuItemClickListener(
        new Toolbar.OnMenuItemClickListener() {

          @Override
          public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
              case R.id.appbar_home:
                  startActivity(new Intent(getApplicationContext(),CLCHomeActivity.class));
                finish();
                return true;
            }

            return false;
          }
        });

    clcStoreVaccineName = findViewById(R.id.clc_vaccine_store_update_vaccinename);
    clcStoreVaccineDose = findViewById(R.id.clc_vaccine_store_update_vaccindose);
    clcStoreVaccineStock = findViewById(R.id.clc_vaccine_store_update_vaccinestock);
    clcStoreVaccineUpdate = findViewById(R.id.clc_update_vaccine_store_button);

    getBundleData();

      clcStoreVaccineUpdate.setOnClickListener(v-> {
        try {
          updateStore();
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }
      });
  }

    private void updateStore() throws ClassNotFoundException {
      currentVaccine.setVaccineStock(Integer.parseInt(clcStoreVaccineStock.getText().toString()));
      final Task<Void> vaccinestore = db.collection("vaccinestore").document(currentKey).set(currentVaccine);

      if(vaccinestore != null)
      {
        toastAndNextActivity("Stock is updated for " + currentVaccine.getVaccineName(), "CLCHomeActivity" );
      }
      else
      {
        toastAndNextActivity("Stock update failed for  " + currentVaccine.getVaccineName(), "CLCHomeActivity" );
      }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar,menu);
        return true;
    }

  private void getBundleData() {

    Intent i = getIntent();
    if (i.hasExtra("VACCINEDETAILS")) {
      Bundle data = i.getExtras();
      this.currentVaccineMap = (HashMap<String, Vaccine>) data.getSerializable("VACCINEDETAILS");
      System.out.println("the vaccine is " + currentVaccine.toString());

      for(Map.Entry<String,Vaccine> entry:currentVaccineMap.entrySet()){
        this.currentKey = entry.getKey();
        this.currentVaccine = entry.getValue();
      }

      clcStoreVaccineName.setText(currentVaccine.getVaccineName());
      clcStoreVaccineDose.setText(String.valueOf(currentVaccine.getVaccineDose()));
      clcStoreVaccineStock.setText(String.valueOf(currentVaccine.getVaccineStock()));
    }
  }

  private void toastAndNextActivity(String message, String nextActivity)
          throws ClassNotFoundException {

    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    toast.show();

    Intent nextActivityRequested =
            new Intent(getApplicationContext(), Class.forName("com.team4.getvaxi." + nextActivity));
    startActivity(nextActivityRequested);
  }
}
