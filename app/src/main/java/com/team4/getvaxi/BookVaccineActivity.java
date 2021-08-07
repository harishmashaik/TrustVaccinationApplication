package com.team4.getvaxi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.models.Child;
import com.team4.getvaxi.models.Person;
import com.team4.getvaxi.models.Vaccine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookVaccineActivity extends AppCompatActivity {

  public static final String TAG = "BookVaccineActivity";
  final long NEXT_TWO_DAYS = MaterialDatePicker.todayInUtcMilliseconds() + 2 * (86400000);
  private Toolbar toolbar;

  private FirebaseAuth mAuth;
  FirebaseUser user;
  FirebaseFirestore db;

  private Button pickDateOoAppointment;
  private Button bookAppointment;
  private EditText text_appointment_date;
  private EditText text_childAge;

  AutoCompleteTextView dropdownChildList;
  Person personDetails = new Person();
  Booking newBooking = new Booking();

  Commons cmns = new Commons();
  Vaccine currentVaccine = new Vaccine();
  ArrayList<Child> personChildInfo;

  EditText vaccineName;

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_book_vaccine);

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

    pickDateOoAppointment = findViewById(R.id.bookvaccine_AC_pickdate);
    text_appointment_date = findViewById(R.id.bookingCon_date);
    vaccineName = findViewById(R.id.bookingCon_vaccinename);
    dropdownChildList = findViewById(R.id.Bookvaccine_AC_childname_menu);
    bookAppointment = findViewById(R.id.bookVaccineAC_book);
    text_childAge = findViewById(R.id.bookvaccine_childage);

    mAuth = FirebaseAuth.getInstance();
    user = mAuth.getCurrentUser();
    db = FirebaseFirestore.getInstance();

    getUserDetails();

    Intent i = getIntent();

    if (i.hasExtra("vaccineDetails")) {
      Bundle data = i.getExtras();
      currentVaccine = (Vaccine) data.getSerializable("vaccineDetails");
      vaccineName.setText(currentVaccine.getVaccineName());
    }

    MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
    materialDateBuilder.setTitleText("Select an Appointment date");

    final MaterialDatePicker materialDatePicker =
            materialDateBuilder
                    .setCalendarConstraints(
                            new CalendarConstraints.Builder()
                                    .setValidator(DateValidatorPointForward.from(NEXT_TWO_DAYS)).build()).build();

    pickDateOoAppointment.setOnClickListener(
        v -> materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER"));
    materialDatePicker.addOnPositiveButtonClickListener(
        selection -> {
          text_appointment_date.setText(materialDatePicker.getHeaderText());
        });

    dropdownChildList.setOnItemClickListener(
        (parent, view, position, id) -> {
          dropdownChildList.getText().toString();
          personChildInfo.forEach(
              c -> {
                if (dropdownChildList.getText().toString().equals(c.getChildName())) {
                  text_childAge.setText(c.getDateOfBirth());
                }
              });
        });

    bookAppointment.setOnClickListener(
        v -> {
          try {
            bookAppointment();
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.top_app_bar, menu);
    return true;
  }

  private void bookAppointment() throws ClassNotFoundException {

    newBooking.setUserAddress(personDetails.getResidingAddress());
    newBooking.setUserProvince(personDetails.getResidingProvince());
    newBooking.setVaccineName(vaccineName.getText().toString());
    newBooking.setAppointmentDate(text_appointment_date.getText().toString());
    newBooking.setName(dropdownChildList.getText().toString());
    newBooking.setDateOfBooking(new Date());
    newBooking.setUserId(mAuth.getCurrentUser().getUid());
    newBooking.setBoookingStatus(Commons.BOOOKING_STATUS_PENDING);
    newBooking.setAge(text_childAge.getText().toString());
    newBooking.setVaccineDose(currentVaccine.getVaccineDose());

    if (validateBooking(newBooking)) {

      db.collection("bookings")
          .add(newBooking)
          .addOnSuccessListener(
                  documentReference -> {
                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                    try {
                      toastAndNextActivity(
                          "Booking Completed: Wait for Confirmation", "HomeActivity");
                    } catch (ClassNotFoundException e) {
                      e.printStackTrace();
                    }
                  })
          .addOnFailureListener(
                  e -> Log.w(TAG, "Error adding document", e));

    } else {
      toastAndNextActivity("Validation Failed : Try again", "HomeActivity");
    }
  }

  private boolean validateBooking(Booking booking) {

    if (booking.getVaccineName() != null
        && booking.getAppointmentDate() != null && booking.getAppointmentDate().length()>8
        && booking.getAge() != null
        && booking.getName() != null
        && booking.getName().length() >= 3
        && booking.getUserId() != null) {
      return true;
    }
    return false;
  }

  private void toastAndNextActivity(String message, String nextActivity)
      throws ClassNotFoundException {

    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    toast.show();

    Intent nextActivityRequested =
        new Intent(getApplicationContext(), Class.forName("com.team4.getvaxi." + nextActivity));
    startActivity(nextActivityRequested);
  }

  private void getUserDetails() {
    final Person[] personDetails = {new Person()};

    String userId = user.getUid();
    Log.i(TAG, userId);
    DocumentReference docRef = db.collection("person").document(userId);

    docRef
        .get()
        .addOnCompleteListener(
            new OnCompleteListener<DocumentSnapshot>() {
              @Override
              public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                  DocumentSnapshot document = task.getResult();
                  if (document.exists()) {
                    Log.i(TAG, "DocumentSnapshot data: " + document.getData());
                    Log.i(TAG, "DocumentSnapshot data: " + document.get("personUUID").toString());
                    Log.i(TAG, "DocumentSnapshot data: " + document.get("personName").toString());
                    personDetails[0] = document.toObject(Person.class);
                    anotherMeth(personDetails[0]);
                  } else {
                    Log.d(TAG, "No such document");
                  }
                } else {
                  Log.d(TAG, "get failed with ", task.getException());
                }
              }
            });

    System.out.println("the details are " + personDetails[0].toString());

  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private void anotherMeth(Person p) {
    System.out.println("inside the person class");
    System.out.println(p.toString());
    personChildInfo = p.getPersonChildInfo();
    personDetails = p;

    System.out.println("I am here " + personChildInfo.size());

    List<String> field1List =
        personChildInfo.stream().map(Child::getChildName).collect(Collectors.toList());

    ArrayAdapter<String> adapter =
        new ArrayAdapter(
            BookVaccineActivity.this, R.layout.booking_confirm_hoslist_layout, field1List);

    dropdownChildList.setAdapter(adapter);
  }
}
