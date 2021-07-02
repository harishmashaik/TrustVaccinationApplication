package com.team4.getvaxi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Person;

public class ProfileCompleteActivity extends AppCompatActivity {


    public static final String TAG = "ProfileCompleteActivity";

    //Firebase Declarations
    private FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    EditText text_fullName;
    EditText text_comple_no_of_kids;
    EditText text_common_law_partner;

    Button but_updateProfile;

    Person per = new Person();
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_complete);

        text_fullName = findViewById(R.id.pro_comple_fullname);
        text_comple_no_of_kids = findViewById(R.id.pro_comple_no_of_kids);
        text_common_law_partner = findViewById(R.id.pro_comple_fullname_law_partner);

        getUserDetails();

        but_updateProfile= findViewById(R.id.but_update_profile);

        but_updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                per.setPersonName(String.valueOf(text_fullName.getText()));
                per.setPersonKids(Integer.parseInt(String.valueOf(text_comple_no_of_kids.getText())));
                per.setPersonCommonLawPartnerName(String.valueOf(text_common_law_partner.getText()));

                db.collection("person")
                        .document("asdasdasdadasdasf")
                        .set(per)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error writing document", e);
                            }
                        });





            }
        });




    }


    public void getUserDetails()
    {
        if (user != null) {
            userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Log.i("the is id ", userID);

        }
    }


}