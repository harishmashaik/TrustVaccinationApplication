package com.team4.getvaxi;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Person;

public class Commons {

  private static FirebaseAuth mAuth;
  private static FirebaseUser currentUser;
  private static FirebaseFirestore db;

  String userUUID;
  Person currentPersonDetails = new Person();

  public Commons() {
    mAuth = FirebaseAuth.getInstance();
    db = FirebaseFirestore.getInstance();
  }

  public String getCurrentUserId() {
    userUUID = mAuth.getCurrentUser().getUid();
    return  userUUID;
  }

  public void getCurrentUserDetails() {

      DocumentReference docRef = db.collection("person").document(mAuth.getCurrentUser().getUid());

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
}
