package com.team4.getvaxi;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
  public static final String TAG = "LoginActivity";

  // declaring the firebase auth variable
  private FirebaseAuth mAuth;
  FirebaseFirestore db = FirebaseFirestore.getInstance();
  Boolean clcSwitch = false;

  // declaring the variables
  Button but_login;
  Button but_forpas;
  Button but_singnUp;
  Switch isCLchecked;

  Context context = this;
  EditText text_loginName;
  EditText text_loginPass;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    // intializing the firebase auth instance.
    mAuth = FirebaseAuth.getInstance();

    // binding the variable with the view elements.
    text_loginName = findViewById(R.id.login_username);
    text_loginPass = findViewById(R.id.login_password);
    isCLchecked = findViewById(R.id.LoginAc_switch);

    but_login = findViewById(R.id.LoginAc_but_login);
    but_forpas = findViewById(R.id.LoginAc_but_forgotPas);
    but_singnUp = findViewById(R.id.LoginAc_but_signup);

    // isCLchecked.isChecked();

    but_login.setOnClickListener(v -> login());
    but_forpas.setOnClickListener(v -> forgotPassword());
    but_singnUp.setOnClickListener(v -> signUp());
    checkUserLogin();
  }

  // login with email and password
  private void login() {
    Log.i(TAG, "the bool is " + clcSwitch);
    Log.i(TAG, "the bool is checked " + isCLchecked.isChecked());
    Log.i(TAG, "the bool is enabled " + isCLchecked.isEnabled());
    Log.i(TAG, "the bool is selected" + isCLchecked.isSelected());

    if (isCLchecked.isChecked()) {

      clcLogin();
    } else {
      String email =
          String.valueOf(
              text_loginName.getText()); // getting details of email & password from the edittext
      String password = String.valueOf(text_loginPass.getText());
      Log.i("The mail is", email);
      Log.i("the pass is ", password);

      if (email.length() > 5 && password.length() > 5) {
        mAuth
            .signInWithEmailAndPassword(
                email, password) // involking signin method with firebase auth instance.
            .addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                      FirebaseUser user =
                          mAuth.getCurrentUser(); // if logged in navigate to the home activity
                      Intent intent = new Intent(context, HomeActivity.class);
                      startActivity(intent);

                    } else {
                      Toast toast =
                          Toast.makeText(
                              getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT);
                      toast.show();
                    }
                  }
                });
      } else {
        Toast toast =
            Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT);
        toast.show();
      }
    }
  }

  // this method will navigate the user to forgot password activity.

  private void forgotPassword() {
    Intent intent = new Intent(context, ForgotpasswordActivity.class);
    startActivity(intent);
  }

  private void clcLogin() {
    System.out.println("clc login");

    db.collection("clcusers")
        .get()
        .addOnCompleteListener(
            new OnCompleteListener<QuerySnapshot>() {
              @RequiresApi(api = Build.VERSION_CODES.N)
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.i(TAG, document.getId() + " => " + document.getData());
                    List<Map<String, String>> usersList =
                        (List<Map<String, String>>) document.getData().get("clcu");
                    System.out.println(usersList);
                    usersList.forEach(
                        li -> {
                          System.out.println(li.get("email"));

                          if (text_loginName.getText().toString().equals(li.get("email"))) {
                            System.out.println("the mail is " + li.get("email"));
                            if (text_loginPass.getText().toString().equals(li.get("password"))) {
                              //
                              Intent intent = new Intent(context, CLCHomeActivity.class);
                              startActivity(intent);
                              finish();
                            }
                            // checkfor pass
                          } else {
                            // no clc user
                          }
                        });
                  }
                } else {
                  Log.i(TAG, "Error getting documents: ", task.getException());
                }
              }
            });
  }

  private void signUp() {
    Intent intent = new Intent(context, SignUpActivity.class);
    startActivity(intent);
  }

  private void checkUserLogin() {
    FirebaseUser user = mAuth.getCurrentUser();
    if (!(user == null)) {
      Intent intent = new Intent(context, HomeActivity.class);
      startActivity(intent);
    }
  }
}
