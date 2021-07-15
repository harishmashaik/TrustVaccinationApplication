package com.team4.getvaxi.CLC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.HomeActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.recycle.BookingViewHolder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    appointmentLocked =findViewById(R.id.bookingconfirmAC_button);


    List<String> items = Arrays.asList("Option 1", "Option 2", "Option 3", "Option 4");
    ArrayAdapter<String> adapter =
        new ArrayAdapter(
            BookingConfirmActivity.this, R.layout.booking_confirm_hoslist_layout, items);

    dropdownCenterList.setAdapter(adapter);
    // (textField.editText as? AutoCompleteTextView)?.setAdapter(adapter);

    dropdownCenterList.getText().toString();

    Intent intent = getIntent();

    if (intent.hasExtra(BookingViewHolder.booking)) {
      eachBooking = intent.getParcelableExtra(BookingViewHolder.booking);
      txtVaccineName.setText(eachBooking.getVaccineName());
      txtchildName.setText(eachBooking.getName());
      txtchildAge.setText(eachBooking.getAge());
      txtDateOfAppointment.setText(eachBooking.getAppointmentDate());
    }

    appointmentLocked.setOnClickListener(v->setConfirmAppointment());
  }

  private void setConfirmAppointment() {

    dropdownCenterList.getText();
    HashMap<String, String> tempCenter = new HashMap<>();
    tempCenter.put(dropdownCenterList.getText().toString(), dropdownCenterList.getText().toString());
    eachBooking.setVaccinationCenterDetails(tempCenter);
    eachBooking.setBookingStatus(true);

    Log.i(TAG, eachBooking.toString());

    db.collection("bookings")
            .document(eachBooking.getFbDocID())
            .set(eachBooking)
            .addOnSuccessListener(
                    new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Appointment confirmed "+ eachBooking.getFbDocID());
                        Intent nextActivity =
                                new Intent(getApplicationContext(), HomeActivity.class);
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
}
