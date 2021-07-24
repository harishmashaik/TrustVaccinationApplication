package com.team4.getvaxi.recycle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.team4.getvaxi.BookVaccineActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Vaccine;

;import java.io.BufferedReader;

import static android.graphics.Color.rgb;

public class VaccineStoreViewHolder extends RecyclerView.ViewHolder {

  public static final String booking = "BOOKING";
  public static final String docID = "DOCUMENTID";

  private final TextView vaccineName;
  private final TextView vaccineDose;
  private final TextView vaccineStock;
  private final Button updateButton;

  private Vaccine vaccine;
  private VaccineStoreAdapter adapter;
  private final MaterialCardView materialCardView;

  public VaccineStoreViewHolder(@NonNull View layoutView) {
    super(layoutView);

      vaccineName = layoutView.findViewById(R.id.custom_clc_vaccine_store_vaccinename);
      vaccineDose = layoutView.findViewById(R.id.custom_clc_vaccine_store_vaccinedose);
      vaccineStock = layoutView.findViewById(R.id.custom_clc_vaccine_store_vaccinestock);
      updateButton = layoutView.findViewById(R.id.custom_clc_vaccine_store_button);
      materialCardView = layoutView.findViewById(R.id.custom_clc_vaccine_store_matcard);

    //        buttonConfirm.setOnClickListener(v -> {
    ////            Intent i = new Intent(layoutView.getContext(), BookingConfirmActivity.class);
    ////            i.putExtra(booking, (Parcelable) vaccine);
    ////            layoutView.getContext().startActivity(i);
    //        });

    //        buttonDecline.setOnClickListener(v -> {
    //            Intent i = new Intent(layoutView.getContext(), HomeActivity.class);
    //            //i.putExtra(username,user.getUser_name());
    //            layoutView.getContext().startActivity(i);
    //        });

    layoutView.setOnClickListener(
        v -> {
          Intent i = new Intent(layoutView.getContext(), BookVaccineActivity.class);
          i.putExtra("vaccineName", vaccine.getVaccineName());
          layoutView.getContext().startActivity(i);
        });
  }

  public void bind(Vaccine vaccine, VaccineStoreAdapter adapter) {
    this.vaccine = vaccine;
    this.adapter = adapter;
    if(vaccine.getVaccineStock() < 5){
      System.out.println(vaccine.toString());
      materialCardView.setCardBackgroundColor(rgb(255,170,153));
    }
    else {
      materialCardView.setCardBackgroundColor(rgb(255,255,255));
    }
      vaccineName.setText(vaccine.getVaccineName());
      vaccineDose.setText(Integer.toString(vaccine.getVaccineDose()));
     vaccineStock.setText(Integer.toString(vaccine.getVaccineStock()));



  }
}
