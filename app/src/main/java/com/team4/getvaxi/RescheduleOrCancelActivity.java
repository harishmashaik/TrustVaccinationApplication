package com.team4.getvaxi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.models.Message;
import com.team4.getvaxi.models.Person;

import java.util.ArrayList;

public class RescheduleOrCancelActivity extends AppCompatActivity {
  public static final String TAG = "RescheduleOrCancelActivity";
  private Toolbar toolbar;

  private Button changeDate;
  private Button reschduleAppointment;
  private Button cancelButton;
  private EditText newdate;
  private EditText childDob;
  private EditText vaccineName;
  FirebaseFirestore db = FirebaseFirestore.getInstance();

  AutoCompleteTextView dropdownChildList;
  Person personDetails = new Person();
  Booking currentBooking = new Booking();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reschedule_or_cancel);

    toolbar = findViewById(R.id.topAppBar);
    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    toolbar.setTitle(Commons.getActivityName(getClass().getSimpleName()));
    toolbar.inflateMenu(R.menu.top_app_bar);
    toolbar.setOnMenuItemClickListener(item -> {
      switch (item.getItemId()){
        case R.id.appbar_home:
          startActivity(new Intent(getApplicationContext(),HomeActivity.class));
          finish();
          return true;
      }
      return false; });

    reschduleAppointment = findViewById(R.id.bookVaccinechange_reschedule);
    childDob = findViewById(R.id.bookvaccinechange_childage);
    changeDate = findViewById(R.id.bookvaccinechange_AC_pickdate);
    newdate = findViewById(R.id.bookingChange_date);
    dropdownChildList = findViewById(R.id.Changevaccine_AC_childname_menu);
    vaccineName = findViewById(R.id.bookingChang_vaccinename);
    cancelButton = findViewById(R.id.bookVaccinechange_cancel);

    MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
    materialDateBuilder.setTitleText("Select New date for appointment");

    final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

    changeDate.setOnClickListener(
        v -> materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER"));
    materialDatePicker.addOnPositiveButtonClickListener(
        selection -> {
          newdate.setText(materialDatePicker.getHeaderText());
        });

    getBundleExtras();
    cancelButton.setOnClickListener(v -> cancelAppointment());
    reschduleAppointment.setOnClickListener(v -> RescheduleAppointment());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.top_app_bar,menu);
    return true;
  }

  private void cancelAppointment() {
    currentBooking.setBoookingStatus("CANCELBYUS");
    currentBooking.setBookingReviewed(true);

    db.collection("bookings").document(currentBooking.getFbDocID()).set(currentBooking);
    Toast toast = Toast.makeText(getApplicationContext(), "Appointment Cancelled", Toast.LENGTH_LONG);
    toast.show();
    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
    startActivity(i);
  }

  private void RescheduleAppointment() {
    System.out.println("the new date si "+ newdate.getText().toString());

      if(newdate.getText().toString()!=null && newdate.getText().toString().length()>8){
          currentBooking.setBookingReviewed(false);
          currentBooking.setBoookingStatus("PEND");
          currentBooking.setAppointmentDate(newdate.getText().toString());
          db.collection("bookings").document(currentBooking.getFbDocID()).set(currentBooking);

          Toast toast = Toast.makeText(getApplicationContext(), "Appointment has been rescheduled", Toast.LENGTH_LONG);
          toast.show();
          Intent i = new Intent(getApplicationContext(), HomeActivity.class);
          startActivity(i);
      }
      else{
        Toast.makeText(getApplicationContext(), "Invalid Date Selected", Toast.LENGTH_LONG).show();
      }

  }

  private void getBundleExtras() {
    Intent i = getIntent();
    if (i.hasExtra("FROMTRACKHOLDER")) {
      Bundle data = i.getExtras();
      currentBooking = (Booking) data.getSerializable("FROMTRACKHOLDER");
      childDob.setText(currentBooking.getAge());
      dropdownChildList.setText(currentBooking.getName());
      vaccineName.setText(currentBooking.getVaccineName());
    }
  }
}
