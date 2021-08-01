package com.team4.getvaxi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpasswordActivity extends AppCompatActivity {

    public static final String TAG = "ForgotpasswordActivity";
    private Toolbar toolbar;


    //declaring the firebase auth instance
    private FirebaseAuth mAuth;

    //declaring variables
    Button but_sendmail;
    EditText text_forgotmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle(Commons.getActivityName(getClass().getSimpleName()));
        toolbar.inflateMenu(R.menu.top_app_bar);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()){
                case R.id.appbar_home:
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                    return true;
            }
            return false; });


        //intializing the firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        //binding varibles with the view elements
        but_sendmail = findViewById(R.id.but_forgotpassword_sendmail);
        text_forgotmail = findViewById(R.id.forgot_Password_email_input);

        Toast toast = Toast.makeText(getApplicationContext(), "Type Your Email - We will send you a link for Password reset", Toast.LENGTH_LONG);
        toast.show();

        //send mail handler
        but_sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar,menu);
        return true;
    }

    //Upon providing the user mail, an  e-mail will be sent from firebase to reset the password
    private void sendMail()
    {
       String emailAddress = String.valueOf(text_forgotmail.getText());

        if(emailAddress.length()>8) //checking email validation, with minimum characters length
        {
            Log.i(TAG, emailAddress);
            mAuth.sendPasswordResetEmail(emailAddress)  //from the auth instance invoking setresetpassword method.
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.i("mesg", "Email sent.");
                                Toast toast = Toast.makeText(getApplicationContext(), "Email Send - Please check your Inbox", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent routeToLoginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(routeToLoginActivity);
                            }
                            else
                            {
                             Log.i(TAG,    task.getException() + " "+ task.getResult());
                                Toast toast = Toast.makeText(getApplicationContext(), "Email not send : Error Occurred", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    });

        }

        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Email Not Valid", Toast.LENGTH_SHORT);
            toast.show();

        }


    }
}