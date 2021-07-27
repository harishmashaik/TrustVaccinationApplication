package com.team4.getvaxi.CLC;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team4.getvaxi.CLCHomeActivity;
import com.team4.getvaxi.HomeActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.SampleTestActivity;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.models.Vaccine;
import com.team4.getvaxi.recycle.BookingViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class BookingConfirmActivity extends AppCompatActivity {

  public static final String TAG = "BookingConfirmActivity";
  Booking eachBooking = new Booking();

  FirebaseFirestore db = FirebaseFirestore.getInstance();

  AutoCompleteTextView dropdownCenterList;

  EditText txtVaccineName;
  EditText txtchildName;
  EditText txtchildAge;
  EditText txtDateOfAppointment;

  Button appointmentLocked;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_booking_confirm);
    dropdownCenterList = findViewById(R.id.Booking_AC_hospital_menu);

    txtVaccineName = findViewById(R.id.bookingCon_vaccinename);
    txtchildAge = findViewById(R.id.bookingCon_childage);
    txtchildName = findViewById(R.id.bookingCon_childname);
    txtDateOfAppointment = findViewById(R.id.bookingCon_appoDate);
    appointmentLocked = findViewById(R.id.bookingconfirmAC_button);

    List<String> items = Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4");
    ArrayAdapter<String> adapter =
        new ArrayAdapter(
            BookingConfirmActivity.this, R.layout.booking_confirm_hoslist_layout, items);

    dropdownCenterList.setAdapter(adapter);

    dropdownCenterList.getText().toString();

    Intent intent = getIntent();

    if (intent.hasExtra(BookingViewHolder.booking)) {
      eachBooking = intent.getParcelableExtra(BookingViewHolder.booking);
      System.out.println("insid ethe booking confirm");
      System.out.println(eachBooking.toString());
      System.out.println("booking confirm");
      System.out.println(eachBooking.toString());
      txtVaccineName.setText(eachBooking.getVaccineName());
      txtchildName.setText(eachBooking.getName());
      txtchildAge.setText(eachBooking.getAge());
      txtDateOfAppointment.setText(eachBooking.getAppointmentDate());
    }

    appointmentLocked.setOnClickListener(v -> setConfirmAppointment());
  }

  private void setConfirmAppointment() {

    dropdownCenterList.getText();
    HashMap<String, String> tempCenter = new HashMap<>();
    tempCenter.put(
        dropdownCenterList.getText().toString(), dropdownCenterList.getText().toString());
    eachBooking.setVaccinationCenterDetails(tempCenter);
    eachBooking.setBookingReviewed(true);
    eachBooking.setBoookingStatus("CONFM");

    Log.i(TAG, eachBooking.toString());

    db.collection("bookings")
        .document(eachBooking.getFbDocID())
        .set(eachBooking)
        .addOnSuccessListener(
            new OnSuccessListener<Void>() {
              @Override
              public void onSuccess(Void aVoid) {
                Log.d(TAG, "Appointment confirmed " + eachBooking.getFbDocID());
                updateVaccineStore();
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

  private void updateVaccineStore() {
    ArrayList<Vaccine> vaccineList = new ArrayList<>();
    db.collection("vaccinestore")
        .get()
        .addOnCompleteListener(
            new OnCompleteListener<QuerySnapshot>() {
              @RequiresApi(api = Build.VERSION_CODES.N)
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  System.out.println("vaccine store " + 1);
                  for (QueryDocumentSnapshot document : task.getResult()) {
                    String tempID = document.getId();
                    Vaccine v = document.toObject(Vaccine.class);
                    System.out.println("vaccine store " + v.getVaccineName() + " and " + txtVaccineName.getText().toString());
                    if (v.getVaccineName().trim().equals(txtVaccineName.getText().toString().trim())
                        && v.getVaccineDose() == eachBooking.getVaccineDose()) {
                      System.out.println("the match su " + tempID);
                      v.setVaccineStock(v.getVaccineStock() - 1);
                      db.collection("vaccinestore").document(tempID).set(v);
                    }
                  }
                } else {
                  Log.i(TAG, "Error getting documents: ", task.getException());
                }
              }
            });
  }

  private void upadte() {}
}
