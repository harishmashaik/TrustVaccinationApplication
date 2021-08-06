package com.team4.getvaxi;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.team4.getvaxi.models.Vaccine;
import com.team4.getvaxi.recycle.VaccineStoreAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CLCVaccineStoreActivity extends AppCompatActivity {

    public static final String TAG = "CLCVaccineStoreActivity";

    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final VaccineStoreAdapter vaccineStoreAdapter = new VaccineStoreAdapter();
   // ProgressLoader proload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_l_c_vaccine_store);
      //  proload = new ProgressLoader(CLCVaccineStoreActivity.this);


        RecyclerView listOfBookings = findViewById(R.id.clc_vaccine_store_vaccineList);

        listOfBookings.setHasFixedSize(false);
        listOfBookings.setLayoutManager(new LinearLayoutManager(this));

        listOfBookings.setAdapter(vaccineStoreAdapter);

        loadAllVaccines();
        //proload.stopProgresBar();
    }

    private void loadAllVaccines() {

        ArrayList<Vaccine> vaccineList = new ArrayList<>();
        ArrayList<HashMap<String,Vaccine>> vaccineMapList = new ArrayList<>();
        db.collection("vaccinestore")
                .get()
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String tempID = document.getId();
                                    Vaccine v = document.toObject(Vaccine.class);
                                    HashMap<String,Vaccine> curMap = new HashMap<>();
                                    curMap.put(tempID,v);
                                    vaccineMapList.add(curMap);
                                }
                               // vaccineList.sort(Comparator.comparing(Vaccine::getVaccineStock));
                                vaccineStoreAdapter.setVaccinesInStore(vaccineMapList);
                            } else {
                                Log.i(TAG, "Error getting documents: ", task.getException());
                            }
                        });
    }
}