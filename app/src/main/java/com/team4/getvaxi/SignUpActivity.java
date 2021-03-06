package com.team4.getvaxi;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@RequiresApi(api = Build.VERSION_CODES.R)
public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "SignUpActivity";
    Commons commonsObj = new Commons();

    private FirebaseAuth mAuth;
    Button but_signup;
    Context context = this;

    EditText text_signupPass;
    EditText text_signupEmail;
    EditText text_siggnupConfirmPassword;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        but_signup = findViewById(R.id.but_signup);


        text_signupPass = findViewById(R.id.signup_password);
        text_signupEmail = findViewById(R.id.signup_email);
        text_siggnupConfirmPassword = findViewById(R.id.signup_confirm_password);

        but_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(text_signupEmail.getText());
                String password = String.valueOf(text_signupPass.getText());
                String confirmPassword = String.valueOf(text_siggnupConfirmPassword.getText());

                signUp(email,password,confirmPassword);
            }
        });


    }

    public void signUp(String email,String password,String confirmPassword)
    {

        if(password.contentEquals(confirmPassword))
        {
            if(email.matches(emailPattern))
            {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    user.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Log.i("SignUp Activity", "Email sent.");
                                                    }
                                                    else
                                                    {
                                                        Log.i("SignUp Activity", task.getException().toString());
                                                    }
                                                }
                                            });

                                    Intent intent = new Intent(context,ProfileCompleteActivity.class);
                                    Bundle b = new Bundle();
                                    b.putSerializable("UUID",user.getUid());
                                    intent.putExtras(b);
                                    startActivity(intent);

                                    //  updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.e(TAG, "createUserWithEmail:failure", task.getException());

                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
            }

            else
            {
                Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.signup_invalid_email_toast), Toast.LENGTH_SHORT);
                toast.show();
            }
      }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.signup_password_mismatch_toast), Toast.LENGTH_SHORT);
            toast.show();

        }

    }

    public void sendverificationMail(FirebaseUser user) {

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.i("SignUp Activity", "Email sent.");
                        }
                        else
                        {
                            Log.i("SignUp Activity", task.getException().toString());
                        }
                    }
                });
    }
}