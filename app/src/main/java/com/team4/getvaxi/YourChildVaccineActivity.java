package com.team4.getvaxi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.recycle.TrackBookingAdapter;

import java.util.ArrayList;

public class YourChildVaccineActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static final String TAG = "YourChildVaccineActivity";

    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    final TrackBookingAdapter trackbookingsAdapter = new TrackBookingAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_child_vaccine);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar, menu);
        return true;
    }

    private void loadUserBookings() throws ClassNotFoundException {
        ArrayList<Booking> userBookingList = new ArrayList<>();
        db.collection("bookings")
                .whereEqualTo("userId", mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String tempID = document.getId();
                                    Booking b = document.toObject(Booking.class);
                                    b.setFbDocID(tempID);
                                    Log.i(TAG, document.getId() + " => " + b.toString());
                                    userBookingList.add(b);
                                }
                                trackbookingsAdapter.setBookings(userBookingList);
                                if (userBookingList.size() == 0) {
                                    try {
                                        toastAndNextActivity(getString(R.string.toast_no_boorkings), "HomeActivity");
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                Log.i(TAG, "Error getting documents: ", task.getException());
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