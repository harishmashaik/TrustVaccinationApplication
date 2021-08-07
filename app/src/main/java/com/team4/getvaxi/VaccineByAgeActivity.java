package com.team4.getvaxi;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
  private Toolbar toolbar;

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

    RecyclerView listOfVaccines = findViewById(R.id.vaccineByAGe_AC_vaccineList);
    listOfVaccines.setHasFixedSize(false);
    listOfVaccines.setLayoutManager(new LinearLayoutManager(this));
    listOfVaccines.setAdapter(vaccinesAdapter);

    vaccineByAgeImage = findViewById(R.id.vaccineByAGe_AC_vaccineImage);
    vaccineByAgeVaccineName = findViewById(R.id.vaccineByAGe_AC_vaccineName);
    vaccineByAgeVaccineDesc = findViewById(R.id.vaccineByAGe_AC_vaccineDescription);
    getExtras();

    getVaccineDetails(ageParam);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.top_app_bar,menu);
    return true;
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

//    Vaccine v1 = new Vaccine();
//    v1.setVaccineName("Hepatitis B (HepB)");
//    v1.setVaccineDose(2);
//    v1.setVaccineStock(10);
//    v1.setVaccineDescription("Below 2 Months");
//
//    Vaccine v2 = new Vaccine();
//    v2.setVaccineName("Diphtheria, tetanus, and whooping cough (pertussis) (DTaP)");
//    v2.setVaccineDose(1);
//    v2.setVaccineStock(10);
//    v2.setVaccineDescription("Below 2 Months");
//
//
//    Vaccine v3 = new Vaccine();
//    v3.setVaccineName("Haemophilus influenzae type b disease (Hib)");
//    v3.setVaccineDose(1);
//    v3.setVaccineStock(10);
//    v3.setVaccineDescription("Below 2 Months");
//
//
//    Vaccine v4 = new Vaccine();
//    v4.setVaccineName("Polio (IPV)");
//    v4.setVaccineDose(1);
//    v4.setVaccineStock(10);
//    v4.setVaccineDescription("Below 2 Months");
//
//    Vaccine v5 = new Vaccine();
//    v5.setVaccineName("Pneumococcal disease (PCV13)");
//    v5.setVaccineDose(1);
//    v5.setVaccineStock(10);
//    v5.setVaccineDescription("Below 2 Months");
//
//    Vaccine v6 = new Vaccine();
//    v6.setVaccineName("Rotavirus (RV)");
//    v6.setVaccineDose(1);
//    v6.setVaccineStock(10);
//    v6.setVaccineDescription("Below 2 Months");
//
//    ArrayList<Vaccine> vaccinesAtThisAge = new ArrayList<>();
//    vaccinesAtThisAge.add(v1);
//    vaccinesAtThisAge.add(v2);
//    vaccinesAtThisAge.add(v3);
//    vaccinesAtThisAge.add(v4);
//    vaccinesAtThisAge.add(v5);
//    vaccinesAtThisAge.add(v6);
//
//    VaccineByAge vxAgebelow2 = new VaccineByAge();
//    vxAgebelow2.setVaccinesAtThisAge(vaccinesAtThisAge);;
//    vxAgebelow2.setImageLogo("vaccine_age_two_below");
//    vxAgebelow2.setDescription("At 1 to 2 months, your baby should receive vaccines to protect them from the following diseases");
//

//    Vaccine v7 = new Vaccine();
//    v7.setVaccineName("Diphtheria, tetanus, and whooping cough (pertussis) (DTaP)");
//    v7.setVaccineDose(2);
//    v7.setVaccineStock(10);
//    v7.setVaccineDescription("Below 4 Months");
//
//    Vaccine v8 = new Vaccine();
//    v8.setVaccineName("Haemophilus influenzae type b disease (Hib)");
//    v8.setVaccineDose(2);
//    v8.setVaccineStock(10);
//    v8.setVaccineDescription("Below 4 Months");
//
//    Vaccine v9 = new Vaccine();
//    v9.setVaccineName("Polio (IPV)");
//    v9.setVaccineDose(2);
//    v9.setVaccineStock(10);
//    v9.setVaccineDescription("Below 4 Months");
//
//
//    Vaccine v10 = new Vaccine();
//    v10.setVaccineName("Pneumococcal disease (PCV13)");
//    v10.setVaccineDose(2);
//    v10.setVaccineStock(10);
//    v10.setVaccineDescription("Below 4 Months");
//
//    Vaccine v11 = new Vaccine();
//    v11.setVaccineName("Rotavirus (RV)");
//    v11.setVaccineDose(2);
//    v11.setVaccineStock(10);
//    v11.setVaccineDescription("Below 4 Months");
//
//    ArrayList<Vaccine> vaccinesAtThisAge = new ArrayList<>();
//    vaccinesAtThisAge.add(v7);
//    vaccinesAtThisAge.add(v8);
//    vaccinesAtThisAge.add(v9);
//    vaccinesAtThisAge.add(v10);
//    vaccinesAtThisAge.add(v11);

//
//    Vaccine v12 = new Vaccine();
//    v12.setVaccineName("Diphtheria, tetanus, and whooping cough (pertussis) (DTaP)");
//    v12.setVaccineDose(3);
//    v12.setVaccineStock(10);
//    v12.setVaccineDescription("Below 6 Months");
//
//    Vaccine v13 = new Vaccine();
//    v13.setVaccineName("Haemophilus influenzae type b disease (Hib)");
//    v13.setVaccineDose(3);
//    v13.setVaccineStock(10);
//    v13.setVaccineDescription("Below 6 Months");
//
//    Vaccine v14 = new Vaccine();
//    v14.setVaccineName("Polio (IPV)");
//    v14.setVaccineDose(3);
//    v14.setVaccineStock(10);
//    v14.setVaccineDescription("Below 6 Months");
//
//
//    Vaccine v15 = new Vaccine();
//    v15.setVaccineName("Pneumococcal disease (PCV13)");
//    v15.setVaccineDose(3);
//    v15.setVaccineStock(10);
//    v15.setVaccineDescription("Below 6 Months");
//
//    Vaccine v16 = new Vaccine();
//    v16.setVaccineName("Rotavirus (RV)");
//    v16.setVaccineDose(3);
//    v16.setVaccineStock(10);
//    v16.setVaccineDescription("Below 6 Months");

//    Vaccine v17 = new Vaccine();
//    v17.setVaccineName("Influenza (Flu)");
//    v17.setVaccineDose(3);
//    v17.setVaccineStock(10);
//    v17.setVaccineDescription("Below 11 Months");


//    Vaccine v18 = new Vaccine();
//    v18.setVaccineName("Chickenpox (Varicella) ");
//    v18.setVaccineDose(1);
//    v18.setVaccineStock(10);
//    v18.setVaccineDescription("Below 2 Years");
//
//    Vaccine v19 = new Vaccine();
//    v19.setVaccineName("Diphtheria, tetanus, and whooping cough (pertussis) (DTaP)");
//    v19.setVaccineDose(4);
//    v19.setVaccineStock(10);
//    v19.setVaccineDescription("Below 2 Years");
//
//    Vaccine v20 = new Vaccine();
//    v20.setVaccineName("Haemophilus influenzae type b disease (Hib)");
//    v20.setVaccineDose(4);
//    v20.setVaccineStock(10);
//    v20.setVaccineDescription("Below 2 Years");
//
//    Vaccine v21 = new Vaccine();
//    v21.setVaccineName("Measles, mumps, and rubella (MMR) ");
//    v21.setVaccineDose(1);
//    v21.setVaccineStock(10);
//    v21.setVaccineDescription("Below 2 Years");
//
//    Vaccine v22 = new Vaccine();
//    v22.setVaccineName("Polio (IPV)");
//    v22.setVaccineDose(4);
//    v22.setVaccineStock(10);
//    v22.setVaccineDescription("Below 2 Years");
//
//    Vaccine v23 = new Vaccine();
//    v23.setVaccineName("Pneumococcal disease (PCV13)");
//    v23.setVaccineDose(4);
//    v23.setVaccineStock(10);
//    v23.setVaccineDescription("Below 2 Years");
//
//    Vaccine v24 = new Vaccine();
//    v24.setVaccineName("Hepatitis A (HepA) ");
//    v24.setVaccineDose(1);
//    v24.setVaccineStock(10);
//    v24.setVaccineDescription("Below 2 Years");
//
//
//    Vaccine v25 = new Vaccine();
//    v25.setVaccineName("Hepatitis B (HepB)");
//    v25.setVaccineDose(3);
//    v25.setVaccineStock(10);
//    v25.setVaccineDescription("Below 2 Years");


//    Vaccine v26 = new Vaccine();
//    v26.setVaccineName("Diphtheria, tetanus, and whooping cough (pertussis) (DTaP)");
//    v26.setVaccineDose(5);
//    v26.setVaccineStock(10);
//    v26.setVaccineDescription("Below 6 Years");
//
//    Vaccine v27 = new Vaccine();
//    v27.setVaccineName("Polio (IPV)");
//    v27.setVaccineDose(4);
//    v27.setVaccineStock(10);
//    v27.setVaccineDescription("Below 6 Years");
//
//    Vaccine v28 = new Vaccine();
//    v28.setVaccineName("Measles, mumps, and rubella (MMR) ");
//    v28.setVaccineDose(2);
//    v28.setVaccineStock(10);
//    v28.setVaccineDescription("Below 6 Years");
//
//    Vaccine v29 = new Vaccine();
//    v29.setVaccineName("Chickenpox (Varicella)");
//    v29.setVaccineDose(2);
//    v29.setVaccineStock(10);
//    v29.setVaccineDescription("Below 6 Years");

//    Vaccine v30 = new Vaccine();
//    v30.setVaccineName("HPV");
//    v30.setVaccineDose(1);
//    v30.setVaccineStock(10);
//    v30.setVaccineDescription("Below 10 Years");


    Vaccine v32 = new Vaccine();
    v32.setVaccineName("Meningococcal disease (MenACWY)");
    v32.setVaccineDose(2);
    v32.setVaccineStock(10);
    v32.setVaccineDescription("Below 18 Years");

    Vaccine v34 = new Vaccine();
    v34.setVaccineName("Serogroup B meningococcal (MenB)");
    v34.setVaccineDose(1);
    v34.setVaccineStock(10);
    v34.setVaccineDescription("Below 18 Years");


    ArrayList<Vaccine> vaccinesAtThisAge = new ArrayList<>();
    vaccinesAtThisAge.add(v32);
    vaccinesAtThisAge.add(v34);
//    vaccinesAtThisAge.add(v28);
//    vaccinesAtThisAge.add(v29);


    VaccineByAge vxAgebelow6years = new VaccineByAge();
    vxAgebelow6years.setVaccinesAtThisAge(vaccinesAtThisAge);
    vxAgebelow6years.setImageLogo("vaccine_age_eighteen_years_below");
    vxAgebelow6years.setDescription("Below Eighteen years, your baby should receive vaccines to protect them from the following diseases");

    db.collection("vaccines")
            .document("vaccineB18Y")
            .set(vxAgebelow6years)
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
    vaccineByAgeVaccineName.setText("Vaccines at this age");
    vaccineByAgeVaccineDesc.setText(vaccineCurrent.getDescription());



    String uri = "@mipmap/" +vaccineCurrent.getImageLogo();  // where myresource (without the extension) is the file

    int imageResource = getResources().getIdentifier(uri, null, getPackageName());

    Drawable res = getResources().getDrawable(imageResource);
    vaccineByAgeImage.setImageDrawable(res);

    final ArrayList<Vaccine> vaccinesList = vaccineCurrent.getVaccinesAtThisAge();

    vaccinesAdapter.setVaccines(vaccinesList);
  }
}
