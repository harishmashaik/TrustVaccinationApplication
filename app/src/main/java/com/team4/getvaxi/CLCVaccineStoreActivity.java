package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.models.Vaccine;
import com.team4.getvaxi.recycle.BookingsAdapter;
import com.team4.getvaxi.recycle.VaccineStoreAdapter;

import java.util.ArrayList;
import java.util.Comparator;

public class CLCVaccineStoreActivity extends AppCompatActivity {

    public static final String TAG = "CLCVaccineStoreActivity";

    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final VaccineStoreAdapter vaccineStoreAdapter = new VaccineStoreAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_l_c_vaccine_store);

        RecyclerView listOfBookings = findViewById(R.id.clc_vaccine_store_vaccineList);

        listOfBookings.setHasFixedSize(false);
        listOfBookings.setLayoutManager(new LinearLayoutManager(this));

        listOfBookings.setAdapter(vaccineStoreAdapter);

        loadAllVaccines();
    }

    private void loadAllVaccines() {
        ArrayList<Vaccine> vaccineList = new ArrayList<>();
        db.collection("vaccinestore")
                .get()
                .addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        String tempID = document.getId();
                                        Vaccine v = document.toObject(Vaccine.class);
                                        //b.setFbDocID(tempID);
                                        //Log.i(TAG, document.getId() + " => " + v.toString());
                                        vaccineList.add(v);
                                    }
                                    vaccineList.sort(Comparator.comparing(Vaccine::getVaccineStock));
                                    vaccineStoreAdapter.setVaccinesInStore(vaccineList);
                                } else {
                                    Log.i(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
    }
}