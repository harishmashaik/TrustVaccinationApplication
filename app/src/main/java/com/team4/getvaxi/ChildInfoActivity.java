package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointBackward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Child;
import com.team4.getvaxi.models.Person;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static java.time.Period.between;

public class ChildInfoActivity extends AppCompatActivity {

  public static final String TAG = "SampleTestActivity";
  FirebaseFirestore db = FirebaseFirestore.getInstance();

  private Button but_next;
  private EditText text_child_dob;
  Person person = new Person();

  ArrayList<Child> childArrayList = new ArrayList<>();
  LinearLayout linearLayout;

  EditText childInfoName;
  EditText childInfoAge;

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_child_info);

    but_next = findViewById(R.id.childInfoAC_nextbutton);

    linearLayout = findViewById(R.id.editTextContainer);

    getBundleData();
    prepareChildViewsandGetData(1);

    but_next.setOnClickListener(v -> buttonNextHandler());
  }

  private void getBundleData() {

    Intent i = getIntent();
    Bundle data = i.getExtras();

    person = (Person) data.getSerializable("personDetails");
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private void buttonNextHandler() {
    if (childInfoName.getText().toString().length() > 1) {
      Child c1 = new Child();
      c1.setChildName(childInfoName.getText().toString());
      // c1.setChildAge(getAge(childInfoAge.getText().toString()));
      c1.setDateOfBirth(childInfoAge.getText().toString());

      childArrayList.add(c1);

      if (childArrayList.size() != person.getPersonKids()) {
        prepareChildViewsandGetData(childArrayList.size() + 1);

      } else {
        updateUsersProfile();
      }

    } else {
      showToast("Fields should not be empty");
    }
  }

  private void updateUsersProfile() {

    person.setPersonChildInfo(childArrayList);
    person.setProfileCompletionStatus(true);
    db.collection("person")
        .document(person.getPersonUUID())
        .set(person)
        .addOnSuccessListener(
            aVoid -> {
              Log.i(TAG, "Profile Updated successfully");
              Intent intentHomeActivity = new Intent(getApplicationContext(), HomeActivity.class);
              startActivity(intentHomeActivity);
            })
        .addOnFailureListener(e -> Log.w(TAG, "Error Updating profile", e));
  }

  private void prepareChildViewsandGetData(int a) {

    MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();

    materialDateBuilder.setTitleText("SELECT A DATE");

    final MaterialDatePicker materialDatePicker =
        materialDateBuilder
            .setCalendarConstraints(
                new CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointBackward.now())
                    .build())
            .build();

    childInfoName = new EditText(this);
    childInfoName.setHint("Enter Child " + a + " Name");
    childInfoName.setLayoutParams(
        new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    childInfoName.setPadding(20, 80, 20, 40);

    childInfoAge = new EditText(this);
    childInfoAge.setHint("Date of Birth");
    childInfoAge.setEnabled(false);
    childInfoAge.setTypeface(null, Typeface.BOLD);
    childInfoAge.setLayoutParams(
        new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    childInfoAge.setPadding(20, 80, 20, 40);

    final Button datePickButton = new Button(this);
    datePickButton.setText("Pickdate");
    datePickButton.setLayoutParams(
        new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    datePickButton.setPadding(20, 80, 20, 20);
    datePickButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
          }
        });

    materialDatePicker.addOnPositiveButtonClickListener(
        new MaterialPickerOnPositiveButtonClickListener() {
          @SuppressLint("SetTextI18n")
          @Override
          public void onPositiveButtonClick(Object selection) {

            childInfoAge.setText(materialDatePicker.getHeaderText());
          }
        });

    if (linearLayout != null) {
      linearLayout.addView(childInfoName);
      linearLayout.addView(childInfoAge);
      linearLayout.addView(datePickButton);
    }

    Button btnShow = findViewById(R.id.btnShow);
    if (btnShow != null) {
      btnShow.setOnClickListener(
          v -> {
            Log.i(TAG, childInfoName.getText().toString());
            Log.i(TAG, childInfoAge.getText().toString());
          });
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private int getAge(String dob) {
    String dobDay = dob.substring(4, 6);
    String dobMonth = dob.substring(0, 3);
    String dobYear = dob.substring(8, 12);
    return (LocalDate.now().getYear() - Integer.parseInt(dobYear));
  }

  private void showToast(String messageToast) {
    Toast toast = Toast.makeText(getApplicationContext(), messageToast, Toast.LENGTH_SHORT);
    toast.show();
  }
}
