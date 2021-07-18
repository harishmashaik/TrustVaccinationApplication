package com.team4.getvaxi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class BookVaccineActivity extends AppCompatActivity {

  private Button pickDateOoAppointment;
  private EditText text_appointment_date;

  EditText vaccineName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_book_vaccine);

    pickDateOoAppointment = findViewById(R.id.bookvaccine_AC_pickdate);
    text_appointment_date = findViewById(R.id.bookingCon_date);
    vaccineName = findViewById(R.id.bookingCon_vaccinename);

    Intent intent = getIntent();
    if (intent.hasExtra("vaccineName")) {
      vaccineName.setText(intent.getStringExtra("vaccineName"));
    }

    MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
    materialDateBuilder.setTitleText("Select an Appointment date");

    final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

    pickDateOoAppointment.setOnClickListener(
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
            System.out.println("here date " + materialDatePicker.getHeaderText().toString());
            text_appointment_date.setText(materialDatePicker.getHeaderText());
          }
        });
  }
}
