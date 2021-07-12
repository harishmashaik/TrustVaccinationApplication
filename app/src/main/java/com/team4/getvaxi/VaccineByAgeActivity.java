package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Person;
import com.team4.getvaxi.models.VaccineByAge;

public class VaccineByAgeActivity extends AppCompatActivity {

    public static final String TAG = "VaccineByAgeActivity";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    VaccineByAge vaccineCurrent = new VaccineByAge();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_by_age);
    }


    public void getVaccineDetails(String byAge){

        StringBuilder docId =new StringBuilder();
        docId.append("get");
        docId.append(byAge);
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

    private  void mapVaccineData(VaccineByAge vaccineCurrent){

    }
}