package com.team4.getvaxi.CLC;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.CLCHomeActivity;
import com.team4.getvaxi.Commons;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Hospital;
import com.team4.getvaxi.models.HospitalByProvince;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CLCAddHospitalActivity extends AppCompatActivity {
    public static final String TAG = "BookVaccineActivity";

    FirebaseFirestore db;

    EditText hospitalName;
    AutoCompleteTextView provinceList;
    EditText hospitalAddress;
    Button addHospitalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_l_c_add_hospital);

        hospitalName = findViewById(R.id.clc_add_hospital_new_name);
        provinceList= findViewById(R.id.clc_add_hospital_provnin_list_menu);
        hospitalAddress = findViewById(R.id.clc_add_hospital_new_address);
        addHospitalButton = findViewById(R.id.clc_add_hospital_new_button);
        db = FirebaseFirestore.getInstance();

        ArrayAdapter<String> adapter =
                new ArrayAdapter(
                        CLCAddHospitalActivity.this, R.layout.booking_confirm_hoslist_layout, Commons.listOfProvinces);
        provinceList.setAdapter(adapter);

        provinceList.setOnItemClickListener(
                (parent, view, position, id) -> {
                    provinceList.getText().toString();
                });

        addHospitalButton.setOnClickListener(v->addHospital());


    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void addHospital() {

        DocumentReference docRef = db.collection("hospitals").document("Aj59QU6ygI9V6MItVnVu");
//        Hospital h1 = new Hospital(hospitalName.getText().toString(),hospitalAddress.getText().toString());
//        HashMap<String, List<Hospital>>  hosMap = new HashMap<>();
//        hosMap.put(provinceList.getText().toString(),List.of(h1));

        HashMap<String, List<Hospital>>  hosMap = new HashMap<>();
        Hospital h2 = new Hospital("Montreal general hospital","1650 Cedar Ave, Montreal, Quebec H3G 1A4");
        hosMap.put("Quebec",List.of(h2));

        Hospital h3 = new Hospital("Travel Clinic","790 Bay St. Suite 426, Toronto, ON M5G 1N8");
        hosMap.put("Ontario",List.of(h3));

        Hospital h4 = new Hospital("BC women's Hospital & Health centre","4500 Oak St, Vancouver, BC V6H 3N1");
        hosMap.put("British Columbia",List.of(h4));

        Hospital h5 = new Hospital("Queens General hospital","175 School St, Liverpool, NS B0T 1K0");
        hosMap.put("Nova Scotia",List.of(h5));

        Hospital h6 = new Hospital("Northeast Community Health Centre","14007 50 St NW, Edmonton, AB T5A 5E4");
        hosMap.put("Alberta",List.of(h6));

        Hospital h7 = new Hospital("Seven oaks general hospital","2300 McPhillips St, Winnipeg, MB R2V 3M3");
        hosMap.put("Monitoba",List.of(h7));

        Hospital h8 = new Hospital("Qikiqtani General Hospital","Iqaluit,NU");
        hosMap.put("Nunavut",List.of(h8));

        Hospital h9 = new Hospital("Quinte Health Care","265 Dundas St E, Belleville, ON K8N 5A9");
        hosMap.put("Prince Edward Island",List.of(h9));

        Hospital h10 = new Hospital("Fort Saskatchewan Community Hospital","9401 86 Ave, Fort Saskatchewan, AB T8L 0C6");
        hosMap.put("Saskatchewan",List.of(h10));


        Hospital h11 = new Hospital("Whitehorse general hospital","5 Hospital Rd, Whitehorse, YT Y1A 3H7");
        hosMap.put("Yukon",List.of(h11));



        db.collection("hospitals")
                .document("Aj59QU6ygI9V6MItVnVu")
                .set(hosMap)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
//                                Log.d(TAG, "Appointment confirmed " + eachBooking.getFbDocID());
//                                updateVaccineStore();
                                Intent nextActivity = new Intent(getApplicationContext(), CLCHomeActivity.class);
                                startActivity(nextActivity);
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });

    }

    private void toastAndNextActivity(String message, String nextActivity)
            throws ClassNotFoundException {

        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.show();

        Intent nextActivityRequested =
                new Intent(getApplicationContext(), Class.forName("com.team4.getvaxi." + nextActivity));
        startActivity(nextActivityRequested);
    }
}