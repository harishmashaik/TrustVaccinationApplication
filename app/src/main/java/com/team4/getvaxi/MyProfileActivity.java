package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.team4.getvaxi.recycle.ChildViewAdapter;
import com.team4.getvaxi.recycle.VaccineAdapter;

import java.io.Serializable;

public class MyProfileActivity extends AppCompatActivity {

  public static final String TAG = "MyProfileActivity";
  final ChildViewAdapter childAdapter = new ChildViewAdapter();

  private Toolbar toolbar;


  // Firebase Declarations
  private FirebaseAuth mAuth;
  FirebaseUser user;
  FirebaseFirestore db = FirebaseFirestore.getInstance();
  Person personCurrent = new Person();

  EditText editTextFirstName;
  EditText editTextEmail;
  EditText editTextPhoneNumber;
  EditText editTextspousename;
  EditText editTextInsuranceNum;

  Button buttonUpdate;
  Button buttonAddChild;

  RecyclerView listOfChildren;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_profile);

    toolbar = findViewById(R.id.topAppBar);
    setSupportActionBar(toolbar);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    toolbar.setTitle("Update My Profile");
    toolbar.inflateMenu(R.menu.top_app_bar);

    mAuth = mAuth = FirebaseAuth.getInstance();
    user = mAuth.getCurrentUser();

     listOfChildren = findViewById(R.id.myprofile_childList);

    editTextFirstName = findViewById(R.id.account_FirstNameUA);
    editTextEmail = findViewById(R.id.account_EmailUA);
    editTextPhoneNumber = findViewById(R.id.account_phoneNo);
    editTextspousename = findViewById(R.id.account_spousename);
   // editTextInsuranceNum = findViewById(R.id.account_InsuranceNo);
    buttonUpdate = findViewById(R.id.but_updateUA);
    buttonAddChild = findViewById(R.id.but_addChild);

    getUserData();

     buttonUpdate.setOnClickListener(v -> updateDetails());
    buttonAddChild.setOnClickListener(v -> updateChildDetails());
  }

  private void updateChildDetails() {

    Bundle b = new Bundle();
    b.putSerializable("CURPER", (Serializable) personCurrent);
    Intent intent = new Intent(getApplicationContext(), AddNewChildActivity.class);
    intent.putExtras(b);
    startActivity(intent);

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
    editTextspousename.setText(p.getPersonCommonLawPartnerName());

    listOfChildren.setHasFixedSize(false);
    listOfChildren.setLayoutManager(new LinearLayoutManager(this));
    listOfChildren.setAdapter(childAdapter);
    childAdapter.setChildren(p.getPersonChildInfo());

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
