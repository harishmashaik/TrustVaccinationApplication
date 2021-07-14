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
import com.team4.getvaxi.recycle.BookingsAdapter;

import java.util.ArrayList;

public class CLCHomeActivity extends AppCompatActivity {

  public static final String TAG = "CLCHomeActivity";

  private FirebaseAuth mAuth;
  FirebaseUser user;
  FirebaseFirestore db = FirebaseFirestore.getInstance();
  final BookingsAdapter bookingsAdapter = new BookingsAdapter();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_c_l_c_home);
    RecyclerView listOfBookings = findViewById(R.id.user_bookingList);

    listOfBookings.setHasFixedSize(false);
    listOfBookings.setLayoutManager(new LinearLayoutManager(this));

    listOfBookings.setAdapter(bookingsAdapter);

    loadBookings();
  }

  private void loadBookings() {
    ArrayList<Booking> bookingList = new ArrayList<>();
    db.collection("bookings")
        .get()
        .addOnCompleteListener(
            new OnCompleteListener<QuerySnapshot>() {
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  for (QueryDocumentSnapshot document : task.getResult()) {

                    Booking b = document.toObject(Booking.class);
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
}
