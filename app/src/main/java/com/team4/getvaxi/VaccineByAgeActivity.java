package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Vaccine;
import com.team4.getvaxi.models.VaccineByAge;
import com.team4.getvaxi.recycle.VaccineAdapter;

import java.util.ArrayList;

public class VaccineByAgeActivity extends AppCompatActivity {

  public static final String TAG = "VaccineByAgeActivity";

  FirebaseFirestore db = FirebaseFirestore.getInstance();
  final VaccineAdapter vaccinesAdapter = new VaccineAdapter();

  ImageView vaccineByAgeImage;
  TextView vaccineByAgeVaccineName;
  TextView vaccineByAgeVaccineDesc;
  VaccineByAge vaccineCurrent = new VaccineByAge();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vaccine_by_age);

    RecyclerView listOfVaccines = findViewById(R.id.vaccineByAGe_AC_vaccineList);
    listOfVaccines.setHasFixedSize(false);
    listOfVaccines.setLayoutManager(new LinearLayoutManager(this));
    listOfVaccines.setAdapter(vaccinesAdapter);

    vaccineByAgeImage = findViewById(R.id.vaccineByAGe_AC_vaccineImage);
    vaccineByAgeVaccineName = findViewById(R.id.vaccineByAGe_AC_vaccineName);
    vaccineByAgeVaccineDesc = findViewById(R.id.vaccineByAGe_AC_vaccineDescription);

    getVaccineDetails("asa");
    //mapVaccineData();

  }

  public void getVaccineDetails(String byAge) {

    StringBuilder docId = new StringBuilder();
    docId.append("get");
    docId.append(byAge);
    DocumentReference docRef = db.collection("vaccines").document("vaccineatage6");

    docRef
        .get()
        .addOnCompleteListener(
            new OnCompleteListener<DocumentSnapshot>() {
              @Override
              public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                  DocumentSnapshot document = task.getResult();
                  if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    vaccineCurrent = document.toObject(VaccineByAge.class);
                    mapVaccineData(vaccineCurrent);
                  } else {
                    Log.d(TAG, "No such document");
                  }
                } else {
                  Log.d(TAG, "get failed with ", task.getException());
                }
              }
            });
  }

  private void mapVaccineData(VaccineByAge vaccineCurrent) {
    vaccineByAgeVaccineName.setText("for age below 6");
    vaccineByAgeVaccineDesc.setText(vaccineCurrent.getDescription());
    final ArrayList<Vaccine> vaccinesList = vaccineCurrent.getVaccines();

    vaccinesAdapter.setVaccines(vaccinesList);

  }
}
