package com.team4.getvaxi;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Child;
import com.team4.getvaxi.models.Person;

import java.util.ArrayList;

public class AddNewChildActivity extends AppCompatActivity {

  Person currentPerson = new Person();

  FirebaseFirestore db;

  Button addNewChild;
  Button pickDobNewChild;
  EditText childName;
  EditText childDob;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_new_child);

    addNewChild = findViewById(R.id.newchild_add_button);
    childName = findViewById(R.id.newchld_childName);
    childDob = findViewById(R.id.newchild_dob);
    pickDobNewChild = findViewById(R.id.pickdob_newchld);

    db = FirebaseFirestore.getInstance();

    getBundleData();
    MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
    materialDateBuilder.setTitleText("Select date Of Birth of Child");

    // new CalendarConstraints.Builder().setEnd(MaterialDatePicker.todayInUtcMilliseconds());

    // MaterialDatePicker.todayInUtcMilliseconds();

    final MaterialDatePicker materialDatePicker =
        materialDateBuilder
            .setCalendarConstraints(
                new CalendarConstraints.Builder()
                    .setEnd(MaterialDatePicker.todayInUtcMilliseconds())
                    .build())
            .build();
    // final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

    pickDobNewChild.setOnClickListener(
        v -> materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER"));
    materialDatePicker.addOnPositiveButtonClickListener(
        selection -> {
          childDob.setText(materialDatePicker.getHeaderText());
        });

    addNewChild.setOnClickListener(
        v -> {
          try {
            addChildDeatils();
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }
        });
  }

  private void addChildDeatils() throws ClassNotFoundException {

    final ArrayList<Child> personChildInfo = currentPerson.getPersonChildInfo();
    Child c1 = new Child();
    c1.setDateOfBirth(childDob.getText().toString());
    c1.setChildName(childName.getText().toString());
    personChildInfo.add(c1);
    currentPerson.setPersonChildInfo(personChildInfo);

    final Task<Void> person =
        db.collection("person").document(currentPerson.getPersonUUID()).set(currentPerson);
    if (person != null) {
      toastAndNextActivity(c1.getChildName() + " has beed added as your children", "HomeActivity");
    }
  }

  private void toastAndNextActivity(String message, String nextActivity)
      throws ClassNotFoundException {

    Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    toast.show();

    Intent nextActivityRequested =
        new Intent(getApplicationContext(), Class.forName("com.team4.getvaxi." + nextActivity));
    startActivity(nextActivityRequested);
  }

  private void getBundleData() {

    Intent i = getIntent();
    if (i.hasExtra("CURPER")) {
      Bundle data = i.getExtras();
      currentPerson = (Person) data.getSerializable("CURPER");
    }
    System.out.println("in the add child activity");
    System.out.println(currentPerson.toString());
  }
}
