package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.VaccineByAge;
import com.team4.getvaxi.recycle.DiseasePreventAdapter;
import com.team4.getvaxi.recycle.VaccineAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DiseasesPreventActivity extends AppCompatActivity {

    public static final String TAG = "DiseasesPreventActivity";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final DiseasePreventAdapter diseasesAdapter = new DiseasePreventAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_prevent);

        RecyclerView listOfVaccines = findViewById(R.id.dieseasesprevent_diseseslist);
        listOfVaccines.setHasFixedSize(false);
        listOfVaccines.setLayoutManager(new LinearLayoutManager(this));
        listOfVaccines.setAdapter(diseasesAdapter);

        loaddiseases();
    }

    private void loaddiseases() {

    DocumentReference docRef = db.collection("diseases").document( "R5NELzbHM1WTB0Wh064C");

    docRef
        .get()
        .addOnCompleteListener(
            new OnCompleteListener<DocumentSnapshot>() {
              @RequiresApi(api = Build.VERSION_CODES.N)
              @Override
              public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                  DocumentSnapshot document = task.getResult();
                  if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    final ArrayList<String> diseasesPrevented =
                        (ArrayList<String>) document.get("diseasesPrevented");
                      diseasesAdapter.setDiseases(diseasesPrevented);
                   // System.out.println(diseasesPrevented);

                    // dies.forEach(System.out::println);
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