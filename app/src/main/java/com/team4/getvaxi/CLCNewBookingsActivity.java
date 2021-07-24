package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.models.Vaccine;
import com.team4.getvaxi.recycle.BookingsAdapter;

import java.util.ArrayList;

public class CLCNewBookingsActivity extends AppCompatActivity {

  public static final String TAG = "CLCHomeActivity";

  ProgressDialog proload;
  private FirebaseAuth mAuth;
  FirebaseUser user;
  FirebaseFirestore db = FirebaseFirestore.getInstance();
  final BookingsAdapter bookingsAdapter = new BookingsAdapter();

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_c_l_c_new_bookings);
    RecyclerView listOfBookings = findViewById(R.id.user_bookingList);

    listOfBookings.setHasFixedSize(false);
    listOfBookings.setLayoutManager(new LinearLayoutManager(this));

    listOfBookings.setAdapter(bookingsAdapter);
    proload = new ProgressDialog(CLCNewBookingsActivity.this);

    //updateVaccineStore();

    loadBookings();
  }

  private void loadBookings() {
    ArrayList<Booking> bookingList = new ArrayList<>();
    db.collection("bookings")
        .whereEqualTo("bookingReviewed", false)
        .get()
        .addOnCompleteListener(
            new OnCompleteListener<QuerySnapshot>() {
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  for (QueryDocumentSnapshot document : task.getResult()) {
                    String tempID = document.getId();
                    Booking b = document.toObject(Booking.class);
                    b.setFbDocID(tempID);
                    Log.i(TAG, document.getId() + " => " + b.toString());
                    bookingList.add(b);
                  }
                  bookingsAdapter.setBookings(bookingList);
                } else {
                  Log.i(TAG, "Error getting documents: ", task.getException());
                }
              }
            });
  }


  @RequiresApi(api = Build.VERSION_CODES.N)
  private void updateVaccineStore(){

    Vaccine v0 = new Vaccine();
    v0.setVaccineName("Hepatitis B (HepB)");
    v0.setVaccineDose(1);
    v0.setVaccineStock(10);
    v0.setVaccineDescription("Below 2 Months");


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

    Vaccine v7 = new Vaccine();
    v7.setVaccineName("Diphtheria, tetanus, and whooping cough (pertussis) (DTaP)");
    v7.setVaccineDose(2);
    v7.setVaccineStock(10);
    v7.setVaccineDescription("Below 4 Months");

    Vaccine v8 = new Vaccine();
    v8.setVaccineName("Haemophilus influenzae type b disease (Hib)");
    v8.setVaccineDose(2);
    v8.setVaccineStock(10);
    v8.setVaccineDescription("Below 4 Months");

    Vaccine v9 = new Vaccine();
    v9.setVaccineName("Polio (IPV)");
    v9.setVaccineDose(2);
    v9.setVaccineStock(10);
    v9.setVaccineDescription("Below 4 Months");


    Vaccine v10 = new Vaccine();
    v10.setVaccineName("Pneumococcal disease (PCV13)");
    v10.setVaccineDose(2);
    v10.setVaccineStock(10);
    v10.setVaccineDescription("Below 4 Months");

    Vaccine v11 = new Vaccine();
    v11.setVaccineName("Rotavirus (RV)");
    v11.setVaccineDose(2);
    v11.setVaccineStock(10);
    v11.setVaccineDescription("Below 4 Months");

    Vaccine v12 = new Vaccine();
    v12.setVaccineName("Diphtheria, tetanus, and whooping cough (pertussis) (DTaP)");
    v12.setVaccineDose(3);
    v12.setVaccineStock(10);
    v12.setVaccineDescription("Below 6 Months");

    Vaccine v13 = new Vaccine();
    v13.setVaccineName("Haemophilus influenzae type b disease (Hib)");
    v13.setVaccineDose(3);
    v13.setVaccineStock(10);
    v13.setVaccineDescription("Below 6 Months");

    Vaccine v14 = new Vaccine();
    v14.setVaccineName("Polio (IPV)");
    v14.setVaccineDose(3);
    v14.setVaccineStock(10);
    v14.setVaccineDescription("Below 6 Months");


    Vaccine v15 = new Vaccine();
    v15.setVaccineName("Pneumococcal disease (PCV13)");
    v15.setVaccineDose(3);
    v15.setVaccineStock(10);
    v15.setVaccineDescription("Below 6 Months");

    Vaccine v16 = new Vaccine();
    v16.setVaccineName("Rotavirus (RV)");
    v16.setVaccineDose(3);
    v16.setVaccineStock(10);
    v16.setVaccineDescription("Below 6 Months");

    Vaccine v17 = new Vaccine();
    v17.setVaccineName("Influenza (Flu)");
    v17.setVaccineDose(3);
    v17.setVaccineStock(10);
    v17.setVaccineDescription("Below 11 Months");

    Vaccine v18 = new Vaccine();
    v18.setVaccineName("Chickenpox (Varicella) ");
    v18.setVaccineDose(1);
    v18.setVaccineStock(10);
    v18.setVaccineDescription("Below 2 Years");

    Vaccine v19 = new Vaccine();
    v19.setVaccineName("Diphtheria, tetanus, and whooping cough (pertussis) (DTaP)");
    v19.setVaccineDose(4);
    v19.setVaccineStock(10);
    v19.setVaccineDescription("Below 2 Years");

    Vaccine v20 = new Vaccine();
    v20.setVaccineName("Haemophilus influenzae type b disease (Hib)");
    v20.setVaccineDose(4);
    v20.setVaccineStock(10);
    v20.setVaccineDescription("Below 2 Years");

    Vaccine v21 = new Vaccine();
    v21.setVaccineName("Measles, mumps, and rubella (MMR) ");
    v21.setVaccineDose(1);
    v21.setVaccineStock(10);
    v21.setVaccineDescription("Below 2 Years");

    Vaccine v22 = new Vaccine();
    v22.setVaccineName("Polio (IPV)");
    v22.setVaccineDose(4);
    v22.setVaccineStock(10);
    v22.setVaccineDescription("Below 2 Years");

    Vaccine v23 = new Vaccine();
    v23.setVaccineName("Pneumococcal disease (PCV13)");
    v23.setVaccineDose(4);
    v23.setVaccineStock(10);
    v23.setVaccineDescription("Below 2 Years");

    Vaccine v24 = new Vaccine();
    v24.setVaccineName("Hepatitis A (HepA) ");
    v24.setVaccineDose(1);
    v24.setVaccineStock(10);
    v24.setVaccineDescription("Below 2 Years");


    Vaccine v25 = new Vaccine();
    v25.setVaccineName("Hepatitis B (HepB)");
    v25.setVaccineDose(3);
    v25.setVaccineStock(10);
    v25.setVaccineDescription("Below 2 Years");

    Vaccine v26 = new Vaccine();
    v26.setVaccineName("Diphtheria, tetanus, and whooping cough (pertussis) (DTaP)");
    v26.setVaccineDose(5);
    v26.setVaccineStock(10);
    v26.setVaccineDescription("Below 6 Years");

    Vaccine v27 = new Vaccine();
    v27.setVaccineName("Polio (IPV)");
    v27.setVaccineDose(4);
    v27.setVaccineStock(10);
    v27.setVaccineDescription("Below 6 Years");

    Vaccine v28 = new Vaccine();
    v28.setVaccineName("Measles, mumps, and rubella (MMR) ");
    v28.setVaccineDose(2);
    v28.setVaccineStock(10);
    v28.setVaccineDescription("Below 6 Years");

    Vaccine v29 = new Vaccine();
    v29.setVaccineName("Chickenpox (Varicella)");
    v29.setVaccineDose(2);
    v29.setVaccineStock(10);
    v29.setVaccineDescription("Below 6 Years");

    Vaccine v30 = new Vaccine();
    v30.setVaccineName("HPV");
    v30.setVaccineDose(1);
    v30.setVaccineStock(10);
    v30.setVaccineDescription("Below 10 Years");

    Vaccine v31 = new Vaccine();
    v31.setVaccineName("Meningococcal disease (MenACWY)");
    v31.setVaccineDose(1);
    v31.setVaccineStock(10);
    v31.setVaccineDescription("Below 12 Years");

    Vaccine v32 = new Vaccine();
    v32.setVaccineName("Meningococcal disease (MenACWY)");
    v32.setVaccineDose(2);
    v32.setVaccineStock(10);
    v32.setVaccineDescription("Below 18 Years");


    Vaccine v33 = new Vaccine();
    v33.setVaccineName("Tetanus, diphtheria, and whooping cough (pertussis)");
    v33.setVaccineDose(1);
    v33.setVaccineStock(10);
    v32.setVaccineDescription("Below 12 Years");

    Vaccine v34 = new Vaccine();
    v34.setVaccineName("Serogroup B meningococcal (MenB)");
    v34.setVaccineDose(1);
    v34.setVaccineStock(10);
    v34.setVaccineDescription("Below 18 Years");


    ArrayList<Vaccine> vaccinesAtThisAge = new ArrayList<>();
    vaccinesAtThisAge.add(v0);
    vaccinesAtThisAge.add(v1);
    vaccinesAtThisAge.add(v2);
    vaccinesAtThisAge.add(v3);
    vaccinesAtThisAge.add(v4);
    vaccinesAtThisAge.add(v5);
    vaccinesAtThisAge.add(v6);
    vaccinesAtThisAge.add(v7);
    vaccinesAtThisAge.add(v8);
    vaccinesAtThisAge.add(v9);
    vaccinesAtThisAge.add(v10);
    vaccinesAtThisAge.add(v11);
    vaccinesAtThisAge.add(v12);
    vaccinesAtThisAge.add(v13);
    vaccinesAtThisAge.add(v14);
    vaccinesAtThisAge.add(v15);
    vaccinesAtThisAge.add(v16);
    vaccinesAtThisAge.add(v17);
    vaccinesAtThisAge.add(v18);
    vaccinesAtThisAge.add(v19);
    vaccinesAtThisAge.add(v20);
    vaccinesAtThisAge.add(v21);
    vaccinesAtThisAge.add(v22);
    vaccinesAtThisAge.add(v23);
    vaccinesAtThisAge.add(v24);
    vaccinesAtThisAge.add(v25);
    vaccinesAtThisAge.add(v26);
    vaccinesAtThisAge.add(v27);
    vaccinesAtThisAge.add(v28);
    vaccinesAtThisAge.add(v29);
    vaccinesAtThisAge.add(v30);
    vaccinesAtThisAge.add(v31);
    vaccinesAtThisAge.add(v32);
    vaccinesAtThisAge.add(v33);
    vaccinesAtThisAge.add(v34);

    vaccinesAtThisAge.forEach(v->{

      db.collection("vaccinestore")
              .add(v)
              .addOnSuccessListener(
                      new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                          Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        }
                      })
              .addOnFailureListener(
                      new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                          Log.w(TAG, "Error adding document", e);
                        }
                      });

    });


  }
}
