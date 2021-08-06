package com.team4.getvaxi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Child;
import com.team4.getvaxi.models.Person;

import java.util.ArrayList;

public class AddNewChildActivity extends AppCompatActivity {

  Person currentPerson = new Person();
  private Toolbar toolbar;

  FirebaseFirestore db;

  Button addNewChild;
  Button pickDobNewChild;
  EditText childName;
  EditText childDob;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_new_child);

    toolbar = findViewById(R.id.topAppBar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    toolbar.setTitle("Add New Child");
    toolbar.inflateMenu(R.menu.top_app_bar);
    toolbar.setOnMenuItemClickListener(item -> {
      switch (item.getItemId()){
        case R.id.appbar_home:
          startActivity(new Intent(getApplicationContext(),HomeActivity.class));
          finish();
          return true;
      }
      return false; });

    addNewChild = findViewById(R.id.newchild_add_button);
    childName = findViewById(R.id.newchld_childName);
    childDob = findViewById(R.id.newchild_dob);
    pickDobNewChild = findViewById(R.id.pickdob_newchld);

    db = FirebaseFirestore.getInstance();

    getBundleData();
    MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
    materialDateBuilder.setTitleText("Select date Of Birth of Child");

    final MaterialDatePicker materialDatePicker =
        materialDateBuilder
            .setCalendarConstraints(
                new CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointBackward.now())
                    .build())
            .build();

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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.top_app_bar,menu);
    return true;
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
  }
}
