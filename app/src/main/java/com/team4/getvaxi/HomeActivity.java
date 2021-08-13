package com.team4.getvaxi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
    //  if(!isConnected(HomeActivity.this)) buildDialog(HomeActivity.this).show();
    //  else {
    //   Toast.makeText(HomeActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
    setContentView(R.layout.activity_home);
    // }

    mAuth = FirebaseAuth.getInstance();

    txtWhyVaccination = findViewById(R.id.mainAc_whyVaccination);
    txtVaccinationSchedule = findViewById(R.id.mainAc_VacciantionSchedule);
    txtDiseaseVaccinePrevent = findViewById(R.id.mainAc_DiavaccinePrevent);
    txtYourChildVaccineVisit = findViewById(R.id.mainAc_YourChildvaccineVisit);
    txtTrackAppointment = findViewById(R.id.mainAc_trackAppointment);
    txtBeforeYouTravel = findViewById(R.id.mainAc_BeforeYourTravel);
    txtAskQuestions = findViewById(R.id.mainAc_AskQuestions);
    txtMyprofile = findViewById(R.id.mainAc_MyProfile);
    txtLogout = findViewById(R.id.mainAc_Logout);
    txtYourChildVaccineVisit = findViewById(R.id.mainAc_YourChildvaccineVisit);

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

    txtTrackAppointment.setOnClickListener(
        v -> {
          try {
            nextActivity("TrackBookingActivity");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });

    txtAskQuestions.setOnClickListener(
        v -> {
          try {
            nextActivity("AskQuestionsActivity");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });

    txtBeforeYouTravel.setOnClickListener(
        v -> {
          try {
            nextActivity("BeforeYourTravelActivity");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });

    txtDiseaseVaccinePrevent.setOnClickListener(
        v -> {
          try {
            nextActivity("DiseasesPreventActivity");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });

    txtYourChildVaccineVisit.setOnClickListener(
        v -> {
          try {
            nextActivity("YourChildVaccineActivity");
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });

    txtLogout.setOnClickListener(v -> userSignOut());
  }

  public boolean isConnected(Context context) {
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netinfo = cm.getActiveNetworkInfo();
    if (netinfo != null && netinfo.isConnectedOrConnecting()) {
      android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
      android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
      if ((mobile != null && mobile.isConnectedOrConnecting())
          || (wifi != null && wifi.isConnectedOrConnecting())) return true;
      else return false;
    } else return false;
  }

  public AlertDialog.Builder buildDialog(Context c) {
    AlertDialog.Builder builder = new AlertDialog.Builder(c);
    builder.setTitle("No Internet Connection");

    builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");
    builder.setPositiveButton(
        "Ok",
        new DialogInterface.OnClickListener() {

          @Override
          public void onClick(DialogInterface dialog, int which) {

            finish();
          }
        });

    return builder;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.top_app_bar, menu);
    return true;
  }

  public void nextActivity(String str) throws ClassNotFoundException {
    if (isConnected(HomeActivity.this)) {
      Intent nextActivityRequested =
          new Intent(getApplicationContext(), Class.forName("com.team4.getvaxi." + str));
      startActivity(nextActivityRequested);
    } else {
      buildDialog(HomeActivity.this).show();
    }
  }

  private void userSignOut() {

    builder
        .setMessage(R.string.log_out_caution)
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
                          getApplicationContext(),
                          getString(R.string.log_out_messgae_signing_out),
                          Toast.LENGTH_SHORT);
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
    switch (item.getItemId()) {
      case R.id.appbar_home:
        finish();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
