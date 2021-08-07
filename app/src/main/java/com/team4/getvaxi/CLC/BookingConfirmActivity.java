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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.team4.getvaxi.CLCHomeActivity;
import com.team4.getvaxi.Commons;
import com.team4.getvaxi.HomeActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.SampleTestActivity;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.models.Hospital;
import com.team4.getvaxi.models.Person;
import com.team4.getvaxi.models.Vaccine;
import com.team4.getvaxi.recycle.BookingViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingConfirmActivity extends AppCompatActivity {

  public static final String TAG = "BookingConfirmActivity";

  FirebaseFirestore db = FirebaseFirestore.getInstance();

  AutoCompleteTextView dropdownCenterList;
  EditText txtVaccineName;
  EditText txtchildName;
  EditText txtchildAge;
  EditText txtDateOfAppointment;
  Button appointmentLocked;
  Map<String, Object> hospitalMap;
  List<Hospital> provincelistofHospitals = new ArrayList<>();

  Booking eachBooking = new Booking();

  @RequiresApi(api = Build.VERSION_CODES.N)
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

    dropdownCenterList.getText().toString();

    Intent intent = getIntent();
    Bundle data = intent.getExtras();

    if (intent.hasExtra(BookingViewHolder.booking)) {
      eachBooking = (Booking) data.getSerializable(BookingViewHolder.booking);
      txtVaccineName.setText(eachBooking.getVaccineName());
      txtchildName.setText(eachBooking.getName());
      txtchildAge.setText(eachBooking.getAge());
      txtDateOfAppointment.setText(eachBooking.getAppointmentDate());
    }
    getHospitalDetails();

    appointmentLocked.setOnClickListener(v -> setConfirmAppointment());
  }

  private void setConfirmAppointment() {

    dropdownCenterList.getText();
    HashMap<String, String> tempCenter = new HashMap<>();
    tempCenter.put(
        "Name&Addr", dropdownCenterList.getText().toString());
    eachBooking.setVaccinationCenterDetails(tempCenter);
    eachBooking.setBookingReviewed(true);
    eachBooking.setBoookingStatus(Commons.BOOOKING_STATUS_CONFIRM);

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

  @RequiresApi(api = Build.VERSION_CODES.N)
  private void getHospitalDetails(){
    final List<HashMap<String,String>>[] temProvList = new List[]{new ArrayList<>()};
    System.out.println("inside ht eget hos dwtaisl");

    DocumentReference docRef = db.collection("hospitals").document("Aj59QU6ygI9V6MItVnVu");

    docRef
        .get()
        .addOnCompleteListener(
            task -> {
              if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                  Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                  hospitalMap = document.getData();
//                  Log.d(
//                      TAG,
//                      "DocumentSnapshot data: " + hospitalMap.get(eachBooking.getUserProvince()));
//                  Log.d(TAG, "DocumentSnapshot data: " + eachBooking.getUserProvince());

                  //hospitalMap = document.toObject(HashMap.class);
                  temProvList[0] = (List<HashMap<String, String>>) hospitalMap.get(eachBooking.getUserProvince());

                  mappingMethod(temProvList[0]);
//                  ArrayAdapter<String> adapter =
//                      new ArrayAdapter(
//                          BookingConfirmActivity.this,
//                          R.layout.booking_confirm_hoslist_layout,
//                          temProvList[0]);
//
//                  dropdownCenterList.setAdapter(adapter);
                } else {
                  Log.d(TAG, "No such document");
                }
              } else {
                Log.d(TAG, "get failed with ", task.getException());
              }
            });

    System.out.println(provincelistofHospitals);


  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private void mappingMethod(List<HashMap<String,String>> lis) {
    List<String> combines = new ArrayList<>();
    for(HashMap<String,String > h1 : lis){
      System.out.println(h1.keySet());
      combines.add(h1.get("hospitalName") +", "+ h1.get("hospitalAddress"));
    }



//    this.provincelistofHospitals.addAll(lis);
//    System.out.println("The prov is " + this.provincelistofHospitals);

//    System.out.println("the tyepe is"+ lis.getClass());
//
//    final Hospital hospital = lis.get(0);
//
//
////    for(HashMap<String,Hospital> h1 : lis)
//    {
//      combines.add(h1.getHospitalName() + h1.getHospitalAddress());
//    }

//    this.provincelistofHospitals.
//            forEach(hospital -> {
//              combines.add(hospital.getHospitalName() + " "+ hospital.getHospitalAddress());
//            });


    ArrayAdapter<String> adapter =
            new ArrayAdapter(
                    BookingConfirmActivity.this, R.layout.booking_confirm_hoslist_layout, combines);

    dropdownCenterList.setAdapter(adapter);

  }

  private void updateVaccineStore() {
    ArrayList<Vaccine> vaccineList = new ArrayList<>();
    db.collection("vaccinestore")
        .get()
        .addOnCompleteListener(
                task -> {
                  if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                      String tempID = document.getId();
                      Vaccine v = document.toObject(Vaccine.class);
                      if (v.getVaccineName().trim().equals(txtVaccineName.getText().toString().trim())
                          && v.getVaccineDose() == eachBooking.getVaccineDose()) {
                        v.setVaccineStock(v.getVaccineStock() - 1);
                        db.collection("vaccinestore").document(tempID).set(v);
                      }
                    }
                  } else {
                    Log.i(TAG, "Error getting documents: ", task.getException());
                  }
                });
  }


}
