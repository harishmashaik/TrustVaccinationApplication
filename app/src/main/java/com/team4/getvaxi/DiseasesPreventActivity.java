package com.team4.getvaxi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.recycle.DiseasePreventAdapter;

import java.util.ArrayList;

public class DiseasesPreventActivity extends AppCompatActivity {

    public static final String TAG = "DiseasesPreventActivity";
    private Toolbar toolbar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final DiseasePreventAdapter diseasesAdapter = new DiseasePreventAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_prevent);
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

        RecyclerView listOfVaccines = findViewById(R.id.dieseasesprevent_diseseslist);
        listOfVaccines.setHasFixedSize(false);
        listOfVaccines.setLayoutManager(new LinearLayoutManager(this));
        listOfVaccines.setAdapter(diseasesAdapter);

        loaddiseases();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar,menu);
        return true;
    }

    private void loaddiseases() {

    DocumentReference docRef = db.collection("diseases").document( "R5NELzbHM1WTB0Wh064C");

    docRef
        .get()
        .addOnCompleteListener(
                task -> {
                  if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                      Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                      final ArrayList<String> diseasesPrevented =
                          (ArrayList<String>) document.get("diseasesPrevented");
                        diseasesAdapter.setDiseases(diseasesPrevented);
                    } else {
                      Log.d(TAG, "No such document");
                    }
                  } else {
                    Log.d(TAG, "get failed with ", task.getException());
                  }
                });
    }
}