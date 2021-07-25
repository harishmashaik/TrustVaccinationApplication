package com.team4.getvaxi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

  private FirebaseAuth mAuth;

  private TextView txtWhyVaccination;
  private TextView txtVaccinationSchedule;
  private TextView txtVaccinationByAge;
  private TextView txtDiseaseVaccinePrevent;
  private TextView txtYourChildVaccineVisit;
  private TextView txtTrackAppointment;
  private TextView txtBeforeYouTravel;
  private TextView txtAskQuestions;
  private TextView txtMyprofile;
  private TextView txtLogout;

  Context context = this;
  AlertDialog.Builder builder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    mAuth = FirebaseAuth.getInstance();



    txtWhyVaccination = findViewById(R.id.mainAc_whyVaccination);
    txtVaccinationSchedule = findViewById(R.id.mainAc_VacciantionSchedule);
    txtVaccinationByAge = findViewById(R.id.mainAc_VacciantionByAge);
    txtDiseaseVaccinePrevent = findViewById(R.id.mainAc_DiavaccinePrevent);
    txtYourChildVaccineVisit = findViewById(R.id.mainAc_YourChildvaccineVisit);
    txtTrackAppointment = findViewById(R.id.mainAc_trackAppointment);
    txtBeforeYouTravel = findViewById(R.id.mainAc_BeforeYourTravel);
    txtAskQuestions = findViewById(R.id.mainAc_AskQuestions);
    txtMyprofile = findViewById(R.id.mainAc_MyProfile);
    txtLogout = findViewById(R.id.mainAc_Logout);

    builder = new AlertDialog.Builder(this);

    txtWhyVaccination.setOnClickListener(
        v -> {
          try {
            nextActivity("WhyVaccinationActivity");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });

    txtVaccinationSchedule.setOnClickListener(
        v -> {
          try {
            nextActivity("VaccineScheduleActivity");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });

    txtMyprofile.setOnClickListener(
        v -> {
          try {
            nextActivity("MyProfileActivity");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });

      txtVaccinationByAge.setOnClickListener(v -> {
          try {
              nextActivity("VaccineByAgeActivity");
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
      });

      txtTrackAppointment.setOnClickListener(v -> {
          try {
              nextActivity("TrackBookingActivity");
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
      });


    txtLogout.setOnClickListener(v -> userSignOut());
  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar,menu);
        return true;
    }



    public void nextActivity(String str) throws ClassNotFoundException {

    Intent nextActivityRequested =
        new Intent(getApplicationContext(), Class.forName("com.team4.getvaxi." + str));
    startActivity(nextActivityRequested);
  }

  private void userSignOut() {

    builder
        .setMessage("Do you want to logout from the TrustVaccination ?")
        .setCancelable(false)
        .setPositiveButton(
            "Yes",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                finish();
                // Getting the firebase auth instance and signing out.
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                  mAuth.signOut();
                  Toast toast =
                      Toast.makeText(
                          getApplicationContext(), "User Signed Out", Toast.LENGTH_SHORT);
                  toast.show();
                  Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                  startActivity(intent);

                } else {

                }
              }
            })
        .setNegativeButton(
            "No",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                //  Action for 'NO' Button
                dialog.cancel();
              }
            });
    // Creating dialog box
    AlertDialog alert = builder.create();
    // Setting the title manually
    alert.setTitle("Logout");
    alert.show();
  }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.appbar_home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
