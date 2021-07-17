package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

  // Firebase Declarations
  private FirebaseAuth mAuth;
  FirebaseUser user;
  FirebaseFirestore db = FirebaseFirestore.getInstance();

  EditText text_fullName;
  EditText text_comple_no_of_kids;
  EditText text_common_law_partner;
  EditText text_phone_number;

  Button but_updateProfile;

  Person personDetails = new Person();
  String userID;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile_complete);

    mAuth = mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    personDetails.setPersonUUID(user.getUid());
    personDetails.setPersonEmail(user.getEmail());

    text_fullName = findViewById(R.id.pro_comple_fullname);
    text_comple_no_of_kids = findViewById(R.id.pro_comple_no_of_kids);
    text_common_law_partner = findViewById(R.id.pro_comple_fullname_law_partner);
    text_phone_number = findViewById(R.id.pro_comple_PhoneNumber);

    getUserAndBundleDetails();

    but_updateProfile = findViewById(R.id.but_update_profile);

    but_updateProfile.setOnClickListener(v->updateButtonHandler());
  }

  private void updateButtonHandler() {
    personDetails.setPersonName(String.valueOf(text_fullName.getText()));
    personDetails.setPersonKids(
            Integer.parseInt(String.valueOf(text_comple_no_of_kids.getText())));
    personDetails.setPersonCommonLawPartnerName(
            String.valueOf(text_common_law_partner.getText()));
    if (String.valueOf(text_phone_number.getText()).length() != 10) {
      showToast("Invalid Phone number");
    } else {
      personDetails.setPersonPhoneNum(String.valueOf(text_phone_number.getText()));
    }

    Bundle b = new Bundle();
    b.putSerializable("personDetails", (Serializable) personDetails);
    Intent intent = new Intent(getApplicationContext(), ChildInfoActivity.class);
    intent.putExtras(b);
    startActivity(intent);

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
