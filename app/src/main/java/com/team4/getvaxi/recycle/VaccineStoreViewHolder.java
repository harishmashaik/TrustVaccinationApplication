package com.team4.getvaxi.recycle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.team4.getvaxi.CLCVaccineStoreUpdateActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Vaccine;

import java.util.HashMap;
import java.util.Map;

import static android.graphics.Color.rgb;;

public class VaccineStoreViewHolder extends RecyclerView.ViewHolder {

  public static final String booking = "BOOKING";
  private  static final String VACCINE_DETAILS = "VACCINEDETAILS";

  private final TextView vaccineName;
  private final TextView vaccineDose;
  private final TextView vaccineStock;
  private final Button updateButton;

  private HashMap<String, Vaccine> vaccineMap;
  Vaccine currVaccine = new Vaccine();
  String currentKey = "";
  private VaccineStoreAdapter adapter;
  private final MaterialCardView materialCardView;

  public VaccineStoreViewHolder(@NonNull View layoutView) {
    super(layoutView);

    vaccineName = layoutView.findViewById(R.id.custom_clc_vaccine_store_vaccinename);
    vaccineDose = layoutView.findViewById(R.id.custom_clc_vaccine_store_vaccinedose);
    vaccineStock = layoutView.findViewById(R.id.custom_clc_vaccine_store_vaccinestock);
    updateButton = layoutView.findViewById(R.id.custom_clc_vaccine_store_button);
    materialCardView = layoutView.findViewById(R.id.custom_clc_vaccine_store_matcard);

    updateButton.setOnClickListener(
        v -> {
          Intent i = new Intent(layoutView.getContext(), CLCVaccineStoreUpdateActivity.class);
          i.putExtra(VACCINE_DETAILS, vaccineMap);
          layoutView.getContext().startActivity(i);
        });
  }

  public void bind(HashMap<String, Vaccine> vaccine, VaccineStoreAdapter adapter) {
    this.vaccineMap = vaccine;
    this.adapter = adapter;

    for (Map.Entry<String, Vaccine> entry : vaccine.entrySet()) {
      this.currentKey = entry.getKey();
      this.currVaccine = entry.getValue();
    }

    if (currVaccine.getVaccineStock() < 5) {
      System.out.println(vaccine.toString());
      materialCardView.setCardBackgroundColor(rgb(255, 170, 153));
    } else {
      materialCardView.setCardBackgroundColor(rgb(255, 255, 255));
    }
    vaccineName.setText(currVaccine.getVaccineName());
    vaccineDose.setText(Integer.toString(currVaccine.getVaccineDose()));
    vaccineStock.setText(Integer.toString(currVaccine.getVaccineStock()));
  }
}
