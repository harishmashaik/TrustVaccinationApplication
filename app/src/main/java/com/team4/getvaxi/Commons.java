package com.team4.getvaxi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.models.Person;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiresApi(api = Build.VERSION_CODES.R)
public class Commons extends Activity {


    public static final String TAG = "CommonsClass";
    public static final String BOOOKING_STATUS_PENDING="PEND";
    public static final String BOOOKING_STATUS_CONFIRM="CONFM";
    public static final String BOOOKING_STATUS_DECLINE="DECL";

    public static final BigDecimal BOOOKING_FROM_NEXT_48HRS= BigDecimal.valueOf(2 * (86400000));


    AlertDialog.Builder builder;

    public static List<String> listOfProvinces = List.of("Quebec","Ontario","British Columbia","Nova Scotia","Alberta","Monitoba","Nunavut","Prince Edward Island","Saskatchewan","Yukon");

    private static FirebaseAuth mAuth;
  private static FirebaseUser currentUser;
  private static FirebaseFirestore db;

  String userUUID;
  Person currentPersonDetails = new Person();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        builder = new AlertDialog.Builder(getApplicationContext());
    }


    public String getCurrentUserId() {
    userUUID = mAuth.getCurrentUser().getUid();
    return  userUUID;
  }

  public static boolean isValidPhoneNumber(String number){
      Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
      Matcher match = ptrn.matcher(number);
      return (match.find() && match.group().equals(number));

  }

  public static String getActivityName(String simpleName){
        int index = simpleName.indexOf("Activity");
        return simpleName.substring(0,index);
  }

  public Person getCurrentUserDetails() {
    System.out.println("the user is " +mAuth.getCurrentUser().getUid());

      DocumentReference docRef = db.collection("person").document(mAuth.getCurrentUser().getUid());

      docRef.get()
              .addOnCompleteListener(
                      new OnCompleteListener<DocumentSnapshot>() {
                          @Override
                          public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                              if (task.isSuccessful()) {
                                  DocumentSnapshot document = task.getResult();
                                  if (document.exists()) {
                                      Log.i(TAG, "DocumentSnapshot data: " + document.getData());

                                      currentPersonDetails = document.toObject(Person.class);
                                      Log.i(TAG, currentPersonDetails.toString());

                                      //mapUserData(personCurrent);
                                  } else {
                                      Log.d(TAG, "No such document");
                                  }
                              } else {
                                  Log.d(TAG, "get failed with ", task.getException());
                              }
                          }
                      });

    System.out.println(currentPersonDetails.toString());

      return currentPersonDetails;

  }

    public void userSignOut() {
        builder = new AlertDialog.Builder(getApplicationContext());

        // Setting message manually and performing action on button click
        builder
                .setMessage("Do you want to logout from the TrustVaccination ?")
                .setCancelable(false)
                .setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                // Getting the firebase auth instance and signing out.
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    mAuth.signOut();
                                    Toast toast =
                                            Toast.makeText(
                                                    getApplicationContext(), "User Signed Out", Toast.LENGTH_SHORT);
                                    toast.show();
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    startActivity(intent);

                                } else {

                                }
                            }
                        })
                .setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
        // Creating dialog box
        AlertDialog alert = builder.create();
        // Setting the title manually
        alert.setTitle("Logout");
        alert.show();
    }

    public boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((mobile != null && mobile.isConnectedOrConnecting())
                    || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");

        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");
        builder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                });

        return builder;
    }
}
