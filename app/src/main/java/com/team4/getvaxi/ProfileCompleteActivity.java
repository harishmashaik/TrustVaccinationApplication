package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Person;

import java.io.Serializable;

public class ProfileCompleteActivity extends AppCompatActivity {

  public static final String TAG = "ProfileCompleteActivity";


  private FirebaseAuth mAuth;
  FirebaseUser user;
  FirebaseFirestore db = FirebaseFirestore.getInstance();

  EditText text_fullName;
  EditText text_comple_no_of_kids;
  EditText text_common_law_partner;
  EditText text_phone_number;
  EditText text_address;
  AutoCompleteTextView provincesList;

  Button but_updateProfile;

  Person personDetails = new Person();
  String userID;

  @RequiresApi(api = Build.VERSION_CODES.R)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile_complete);

    mAuth = mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    personDetails.setPersonUUID(user.getUid());
    personDetails.setPersonEmail(user.getEmail());

    text_fullName = findViewById(R.id.pro_comple_fullname);
    //text_comple_no_of_kids = findViewById(R.id.pro_comple_no_of_kids);
    text_common_law_partner = findViewById(R.id.pro_comple_fullname_law_partner);
    text_phone_number = findViewById(R.id.pro_comple_PhoneNumber);
    provincesList = findViewById(R.id.profile_complete_province_list);
    text_address = findViewById(R.id.pro_comple_address);

    ArrayAdapter<String> adapter =
            new ArrayAdapter(
                    ProfileCompleteActivity.this, R.layout.booking_confirm_hoslist_layout, Commons.listOfProvinces);

    provincesList.setAdapter(adapter);

    getUserAndBundleDetails();

    but_updateProfile = findViewById(R.id.but_update_profile);
    but_updateProfile.setOnClickListener(v->updateButtonHandler());
  }

  @RequiresApi(api = Build.VERSION_CODES.R)
  private void updateButtonHandler() {
    String address = text_address.getText().toString();
    String province = provincesList.getText().toString();
    String name = String.valueOf(text_fullName.getText().toString());
//    String kids = text_comple_no_of_kids.getText().toString();
    String lawPartner = text_common_law_partner.getText().toString();
    String phomeNum = text_phone_number.getText().toString();
    if (checkEmpty(address)
          && checkEmpty(province)
          && checkEmpty(name)
          &&checkEmpty(lawPartner)
          &&checkEmpty(phomeNum)) {
      showToast(getString(R.string.booking_validation_failed));
    } else {
      Commons.isValidPhoneNumber(text_phone_number.getText().toString());
      personDetails.setResidingAddress(address);
      personDetails.setResidingProvince(province);
      personDetails.setPersonName(name);
      //personDetails.setPersonKids(Integer.parseInt(kids));
      personDetails.setPersonCommonLawPartnerName(String.valueOf(lawPartner));
      personDetails.setPersonPhoneNum(String.valueOf(phomeNum));
      Bundle b = new Bundle();
      b.putSerializable("personDetails", (Serializable) personDetails);
      Intent intent = new Intent(getApplicationContext(), ChildInfoActivity.class);
      intent.putExtras(b);
      startActivity(intent);
    }

  }

  private boolean checkEmpty(String input){
    if(input.matches("") || input == null) return true;
    return false;
  }

  public void getUserAndBundleDetails(){
    if (user != null) {
      userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
      personDetails.setPersonUUID(userID);
      Log.i("the is id ", userID);
    }
  }

  private void showToast(String invalid_phone_number) {
    Toast toast = Toast.makeText(getApplicationContext(), invalid_phone_number, Toast.LENGTH_SHORT);
    toast.show();
  }
}
