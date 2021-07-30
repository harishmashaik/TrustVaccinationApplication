package com.team4.getvaxi.recycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.BookVaccineActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Child;
import com.team4.getvaxi.models.Vaccine;

import java.io.Serializable;

;

public class ChildViewHolder extends RecyclerView.ViewHolder {

  private final TextView childAgeandName;

  private Child child;
  private ChildViewAdapter adapter;

  public ChildViewHolder(@NonNull View layoutView) {
    super(layoutView);

      childAgeandName = layoutView.findViewById(R.id.custom_vaccinebyage_vaccinename);

  }

  public void bind(Child child, ChildViewAdapter adapter) {
    this.child = child;
    this.adapter = adapter;
   childAgeandName.setText(child.getChildName() + " | " + child.getDateOfBirth());

  }
}
