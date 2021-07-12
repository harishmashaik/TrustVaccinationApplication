package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Person;

public class MyProfileActivity extends AppCompatActivity {

  public static final String TAG = "MyProfileActivity";

  // Firebase Declarations
  private FirebaseAuth mAuth;
  FirebaseUser user;
  FirebaseFirestore db = FirebaseFirestore.getInstance();
  Person personCurrent = new Person();

  EditText editTextFirstName;
  EditText editTextEmail;
  EditText editTextPhoneNumber;
  EditText editTextDOB;
  EditText editTextInsuranceNum;
  Button buttonUpdate;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_profile);

    mAuth = mAuth = FirebaseAuth.getInstance();
    user = mAuth.getCurrentUser();

    editTextFirstName = findViewById(R.id.account_FirstNameUA);
    editTextEmail = findViewById(R.id.account_EmailUA);
    editTextPhoneNumber = findViewById(R.id.account_phoneNo);
    editTextDOB = findViewById(R.id.account_Dob);
    editTextInsuranceNum = findViewById(R.id.account_InsuranceNo);
    buttonUpdate = findViewById(R.id.but_updateUA);

    getUserData();

     buttonUpdate.setOnClickListener(v -> updateDetails());
  }

  private void updateDetails() {
    Person updatePerson = new Person();
    updatePerson.setPersonName(String.valueOf(editTextFirstName.getText()));
    updatePerson.setPersonPhoneNum(String.valueOf(editTextPhoneNumber.getText()));
  }

  private void getUserData() {
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
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    personCurrent = document.toObject(Person.class);
                    mapUserData(personCurrent);
                  } else {
                    Log.d(TAG, "No such document");
                  }
                } else {
                  Log.d(TAG, "get failed with ", task.getException());
                }
              }
            });
  }

  private void mapUserData(Person p) {

    Log.i(TAG, p.toString());
    editTextFirstName.setText(p.getPersonName());
    editTextEmail.setText(p.getPersonEmail());
    editTextPhoneNumber.setText(p.getPersonPhoneNum());

    db.collection("person")
        .document(user.getUid())
        .set(p)
        .addOnSuccessListener(
            new OnSuccessListener<Void>() {
              @Override
              public void onSuccess(Void aVoid) {
                Log.d(TAG, "Document Snapshot successfully written!");
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
}
