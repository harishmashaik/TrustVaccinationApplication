package com.team4.getvaxi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseUser;
import com.team4.getvaxi.models.ProgressLoader;

public class CLCHomeActivity extends AppCompatActivity {

  MaterialCardView newBookings;
  MaterialCardView confirmedBookings;
  MaterialCardView vaccineStore;
  MaterialCardView clcLogout;
  MaterialCardView newQuestions;
  ProgressLoader proload;

  AlertDialog.Builder builder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_c_l_c_home);


    newBookings = findViewById(R.id.clc_card_new_bookings);
    confirmedBookings = findViewById(R.id.clc_card_confirmed_bookings);
    vaccineStore = findViewById(R.id.clc_card_vaccine_stock);
    newQuestions = findViewById(R.id.clc_card_questions);
    clcLogout = findViewById(R.id.clc_card_logout);

    proload = new ProgressLoader(CLCHomeActivity.this);
    builder = new AlertDialog.Builder(this);

    vaccineStore.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            proload.StartProgressLoader();
            Intent newBookingsActivity =
                new Intent(getApplicationContext(), CLCVaccineStoreActivity.class);
            // startActivity(newBookingsActivity);
            final  Handler handle =
                new Handler() {
                  @Override
                  public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    // proload.incrementProgressBy(1);
                  }
                };
            new Thread(
                    new Runnable() {
                      @Override
                      public void run() {
                        try {
                          Thread.sleep(300);
                          handle.sendMessage(handle.obtainMessage());
                          proload.stopProgresBar();
                          startActivity(newBookingsActivity);

                        } catch (Exception e) {
                          e.printStackTrace();
                        }
                      }
                    })
                .start();
          }
        });

    newBookings.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent newBookingsActivity =
                new Intent(getApplicationContext(), CLCNewBookingsActivity.class);
            newBookingsActivity.putExtra("TYPE", "new");
            startActivity(newBookingsActivity);
          }
        });

    confirmedBookings.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            Intent newBookingsActivity =
                new Intent(getApplicationContext(), CLCNewBookingsActivity.class);
            newBookingsActivity.putExtra("TYPE", "confirmed");
            startActivity(newBookingsActivity);
          }
        });

      newQuestions.setOnClickListener(
              new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent newBookingsActivity =
                              new Intent(getApplicationContext(), CLCQuestionsActivity.class);
                      newBookingsActivity.putExtra("TYPE", "confirmed");
                      startActivity(newBookingsActivity);
                  }
              });

    clcLogout.setOnClickListener(v -> logoutCLC());
  }

  private void logoutCLC() {
    builder
        .setMessage("Do you want to logout from the TrustVaccination ?")
        .setCancelable(false)
        .setPositiveButton(
            "Yes",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                finish();
                Toast toast =
                    Toast.makeText(getApplicationContext(), "Signing Out..", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
              }
            })
        .setNegativeButton(
            "No",
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
              }
            });

    AlertDialog alert = builder.create();
    alert.setTitle("Logout");
    alert.show();
  }

  //    public void loadProgressDialog(){
  //        //LayoutInflater inflater =
  //        proload = new ProgressDialog(CLCHomeActivity.this);
  //        //proload.setMax(100);
  //        //proload.setMessage("Loading...");
  //        proload.setContentView(R.layout.custom_progress_loader);
  //        //proload.setTitle("My Application");
  //        proload.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
  //        proload.show();

  //        final Handler handle = new Handler() {
  //            @Override
  //            public void handleMessage(Message msg) {
  //                super.handleMessage(msg);
  //                //proload.incrementProgressBy(1);
  //            }
  //        };
  //        new Thread(new Runnable() {
  //            @Override
  //            public void run() {
  //                try {
  //                    while (proload.getProgress() <= proload
  //                            .getMax()) {
  //                        Thread.sleep(30);
  //                        handle.sendMessage(handle.obtainMessage());
  //                        if (proload.getProgress() == proload
  //                                .getMax()) {
  //                            proload.dismiss();
  //                        }
  //                    }
  //                } catch (Exception e) {
  //                    e.printStackTrace();
  //                }
  //            }
  //        }).start();

  // }
}
