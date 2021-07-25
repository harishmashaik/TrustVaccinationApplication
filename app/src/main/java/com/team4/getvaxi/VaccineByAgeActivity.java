package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

  String ageParam;

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
    //updateVVVV();
    getExtras();

    getVaccineDetails(ageParam);
    // mapVaccineData();

  }

  private void getExtras() {
    Intent intent = getIntent();
    if (intent.hasExtra("AGEPARAM")) {
      ageParam = intent.getStringExtra("AGEPARAM");
    }
  }

  public void getVaccineDetails(String byAge) {

  if(byAge.length()>4){
    StringBuilder docId = new StringBuilder();
    docId.append("vaccine");
    docId.append(byAge.substring(3));
    DocumentReference docRef = db.collection("vaccines").document(docId.toString());

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
  }


  private void updateVVVV(){

    Vaccine v1 = new Vaccine();
    v1.setVaccineName("Hepatitis B (HepB)");
    v1.setVaccineDose(2);
    v1.setVaccineStock(10);
    v1.setVaccineDescription("Below 2 Months");

    Vaccine v2 = new Vaccine();
    v2.setVaccineName("Diphtheria, tetanus, and whooping cough (pertussis) (DTaP)");
    v2.setVaccineDose(1);
    v2.setVaccineStock(10);
    v2.setVaccineDescription("Below 2 Months");


    Vaccine v3 = new Vaccine();
    v3.setVaccineName("Haemophilus influenzae type b disease (Hib)");
    v3.setVaccineDose(1);
    v3.setVaccineStock(10);
    v3.setVaccineDescription("Below 2 Months");


    Vaccine v4 = new Vaccine();
    v4.setVaccineName("Polio (IPV)");
    v4.setVaccineDose(1);
    v4.setVaccineStock(10);
    v4.setVaccineDescription("Below 2 Months");

    Vaccine v5 = new Vaccine();
    v5.setVaccineName("Pneumococcal disease (PCV13)");
    v5.setVaccineDose(1);
    v5.setVaccineStock(10);
    v5.setVaccineDescription("Below 2 Months");

    Vaccine v6 = new Vaccine();
    v6.setVaccineName("Rotavirus (RV)");
    v6.setVaccineDose(1);
    v6.setVaccineStock(10);
    v6.setVaccineDescription("Below 2 Months");

    ArrayList<Vaccine> vaccinesAtThisAge = new ArrayList<>();
    vaccinesAtThisAge.add(v1);
    vaccinesAtThisAge.add(v2);
    vaccinesAtThisAge.add(v3);
    vaccinesAtThisAge.add(v4);
    vaccinesAtThisAge.add(v5);
    vaccinesAtThisAge.add(v6);

    VaccineByAge vxAgebelow2 = new VaccineByAge();
    vxAgebelow2.setVaccinesAtThisAge(vaccinesAtThisAge);;
    vxAgebelow2.setImageLogo("vaccine_age_two_below");
    vxAgebelow2.setDescription("At 1 to 2 months, your baby should receive vaccines to protect them from the following diseases");


    db.collection("vaccines")
            .document("vaccineB02M")
            .set(vxAgebelow2)
            .addOnSuccessListener(
                    new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                        Log.i(TAG, "Profile Updated successfully");
                        Intent intentHomeActivity = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intentHomeActivity);
                      }
                    })
            .addOnFailureListener(
                    new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error Updating profile", e);
                      }
                    });

  }

  private void mapVaccineData(VaccineByAge vaccineCurrent) {
    vaccineByAgeVaccineName.setText("for age below 6");
    vaccineByAgeVaccineDesc.setText(vaccineCurrent.getDescription());



    String uri = "@mipmap/" +vaccineCurrent.getImageLogo();  // where myresource (without the extension) is the file

    int imageResource = getResources().getIdentifier(uri, null, getPackageName());

    Drawable res = getResources().getDrawable(imageResource);
    vaccineByAgeImage.setImageDrawable(res);

    final ArrayList<Vaccine> vaccinesList = vaccineCurrent.getVaccinesAtThisAge();

    vaccinesAdapter.setVaccines(vaccinesList);
  }
}
