package com.team4.getvaxi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.recycle.YourChildVaccineVisitAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class YourChildVaccineActivity extends AppCompatActivity {
  private Toolbar toolbar;
  public static final String TAG = "YourChildVaccineActivity";

  private FirebaseAuth mAuth;
  FirebaseUser user;
  FirebaseFirestore db = FirebaseFirestore.getInstance();
  final YourChildVaccineVisitAdapter trackbookingsAdapter = new YourChildVaccineVisitAdapter();

  @RequiresApi(api = Build.VERSION_CODES.R)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_your_child_vaccine);

    toolbar = findViewById(R.id.topAppBar);
    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    toolbar.setTitle(Commons.getActivityName(getClass().getSimpleName()));
    toolbar.inflateMenu(R.menu.top_app_bar);
    toolbar.setOnMenuItemClickListener(
        item -> {
          switch (item.getItemId()) {
            case R.id.appbar_home:
              startActivity(new Intent(getApplicationContext(), HomeActivity.class));
              finish();
              return true;
          }
          return false;
        });

    mAuth = FirebaseAuth.getInstance();

    RecyclerView listOfuserBookings = findViewById(R.id.user_child_vaccine_visit);

    listOfuserBookings.setHasFixedSize(false);
    listOfuserBookings.setLayoutManager(new LinearLayoutManager(this));
    listOfuserBookings.setAdapter(trackbookingsAdapter);

    try {
      loadUserBookings();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.top_app_bar, menu);
    return true;
  }

  @RequiresApi(api = Build.VERSION_CODES.R)
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
                final List<Booking> collectConfirmed =
                    userBookingList.stream()
                        .filter(
                            booking ->
                                booking.getBoookingStatus().equals(Commons.BOOOKING_STATUS_CONFIRM))
                        .filter(booking -> booking.getBookingReviewed().equals(true))
                        .collect(Collectors.toList());
                vaccineCompleted(collectConfirmed);

                //trackbookingsAdapter.setBookings(userBookingList);
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

  @RequiresApi(api = Build.VERSION_CODES.O)
  private void vaccineCompleted(List<Booking> collectConfirmed) {
    List<Booking> completedBookings = new ArrayList<>();

    final List<String> stringsMonths =
        Arrays.asList(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

    collectConfirmed.forEach(
        booking -> {
          final String appointmentDate = booking.getAppointmentDate();
          String dobDay = appointmentDate.substring(5, 7);
          String dobMonth = appointmentDate.substring(0, 3);
          String dobYear = appointmentDate.substring(8);

          int indexMonth = stringsMonths.indexOf(dobMonth) + 1;
          String paddedMonth =
              indexMonth > 9 ? String.valueOf(indexMonth) : String.format("%02d", indexMonth);

          String appointmentDateInt = dobYear + paddedMonth + dobDay;

          System.out.println(LocalDate.now());
          String currentDate = LocalDate.now().toString();
          String curYr = currentDate.substring(0, 4);
          String curMOn = currentDate.substring(5, 7);
          String curDay = currentDate.substring(8);
          int currentToday =
              Integer.parseInt(
                  currentDate.substring(0, 4).trim()
                      + currentDate.substring(5, 7).trim()
                      + currentDate.substring(8).trim());

          System.out.println(Integer.parseInt(appointmentDateInt.trim()) > currentToday);

          if (currentToday > Integer.parseInt(appointmentDateInt.trim())) {
            completedBookings.add(booking);
          }
        });

      trackbookingsAdapter.setBookings(completedBookings);
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
