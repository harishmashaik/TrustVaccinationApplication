package com.team4.getvaxi;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.recycle.TrackBookingAdapter;

import java.util.ArrayList;

public class TrackBookingActivity extends AppCompatActivity {

    public static final String TAG = "TrackBookingActivity";

    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final TrackBookingAdapter trackbookingsAdapter = new TrackBookingAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        RecyclerView listOfBookings = findViewById(R.id.user_trackBookingList);

        listOfBookings.setHasFixedSize(false);
        listOfBookings.setLayoutManager(new LinearLayoutManager(this));

        listOfBookings.setAdapter(trackbookingsAdapter);

        loadUserBookings();





    }

    private void loadUserBookings() {
        ArrayList<Booking> userBookingList = new ArrayList<>();
        db.collection("bookings")
                .whereEqualTo("bookingStatus", false)
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
                                        userBookingList.add(b);
                                    }
                                    trackbookingsAdapter.setBookings(userBookingList);
                                } else {
                                    Log.i(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });
    }
}