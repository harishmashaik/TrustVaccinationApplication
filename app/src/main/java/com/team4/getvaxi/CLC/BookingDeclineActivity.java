package com.team4.getvaxi.CLC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.CLCHomeActivity;
import com.team4.getvaxi.Commons;
import com.team4.getvaxi.HomeActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.recycle.BookingViewHolder;

import java.util.HashMap;

public class BookingDeclineActivity extends AppCompatActivity {

  public static final String TAG = "BookingConfirmActivity";

  FirebaseFirestore db = FirebaseFirestore.getInstance();

  EditText txtVaccineName;
  EditText txtchildName;
  EditText txtchildAge;
  EditText txtDateOfAppointment;
  EditText txtRemarks;
  Button appointmentDeclined;

  Booking eachBooking = new Booking();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_booking_decline);

    txtVaccineName = findViewById(R.id.bookingDel_vaccinename);
    txtchildAge = findViewById(R.id.bookingDel_childage);
    txtchildName = findViewById(R.id.bookingDel_childname);
    txtDateOfAppointment = findViewById(R.id.bookingDel_appoDate);
    appointmentDeclined = findViewById(R.id.bookingDecline_AC_confirm);
    txtRemarks = findViewById(R.id.bookingDel_remarks);

    Intent intent = getIntent();
    Bundle data = intent.getExtras();

    if (intent.hasExtra(BookingViewHolder.booking)) {
      eachBooking = (Booking) data.getSerializable(BookingViewHolder.booking);
      txtVaccineName.setText(eachBooking.getVaccineName());
      txtchildName.setText(eachBooking.getName());
      txtchildAge.setText(eachBooking.getAge());
      txtDateOfAppointment.setText(eachBooking.getAppointmentDate());
    }

    appointmentDeclined.setOnClickListener(v -> setConfirmAppointment());
  }

  private void setConfirmAppointment() {

    eachBooking.setBookingReviewed(true);
    eachBooking.setRemarks(txtRemarks.getText().toString());
    eachBooking.setBoookingStatus(Commons.BOOOKING_STATUS_DECLINE);

    Log.i(TAG, eachBooking.toString());

    db.collection("bookings")
        .document(eachBooking.getFbDocID())
        .set(eachBooking)
        .addOnSuccessListener(
                aVoid -> {
                  Log.d(TAG, "Appointment Cancelled" + eachBooking.getFbDocID());
                  Intent nextActivity = new Intent(getApplicationContext(), CLCHomeActivity.class);
                  startActivity(nextActivity);
                })
        .addOnFailureListener(
                e -> Log.w(TAG, "Error writing document", e));
  }
}
