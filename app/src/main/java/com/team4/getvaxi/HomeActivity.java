package com.team4.getvaxi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtWhyVaccination= findViewById(R.id.mainAc_whyVaccination);
        txtVaccinationSchedule = findViewById(R.id.mainAc_VacciantionSchedule);
        txtVaccinationByAge = findViewById(R.id.mainAc_VacciantionByAge);
        txtDiseaseVaccinePrevent = findViewById(R.id.mainAc_DiavaccinePrevent);
        txtYourChildVaccineVisit = findViewById(R.id.mainAc_YourChildvaccineVisit);
        txtTrackAppointment = findViewById(R.id.mainAc_trackAppointment);
        txtBeforeYouTravel = findViewById(R.id.mainAc_BeforeYourTravel);
        txtAskQuestions = findViewById(R.id.mainAc_AskQuestions);
        txtMyprofile = findViewById(R.id.mainAc_MyProfile);
        txtLogout = findViewById(R.id.mainAc_Logout);



        txtWhyVaccination.setOnClickListener(v-> {
            try {
                nextActivity("WhyVaccinationActivity");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    public void nextActivity(String str) throws ClassNotFoundException {

        Intent nextActivityRequested = new Intent(getApplicationContext(), Class.forName("com.team4.getvaxi." + str));
        startActivity(nextActivityRequested);
    }


}