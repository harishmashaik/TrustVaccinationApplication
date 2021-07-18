package com.team4.getvaxi.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Vaccine;

import java.util.ArrayList;
import java.util.List;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineViewHolder> {

  private List<Vaccine> vaccines;

  public VaccineAdapter() {
    vaccines = new ArrayList<>();
  }

  public void setVaccines(List<Vaccine> vaccines) {
    this.vaccines = vaccines;
    notifyDataSetChanged(); // going to bind new data to Views.
  }

  @NonNull
  @Override
  public VaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View customView =
        LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    VaccineViewHolder userViewHolder = new VaccineViewHolder(customView);
    return userViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull VaccineViewHolder holder, int position) {
    holder.bind(vaccines.get(position), this);
  }

  @Override
  public int getItemCount() {
    return vaccines.size();
  }

  @Override
  public int getItemViewType(int position) {
    return R.layout.custom_vaccinesbyage_vaccinelist_view;
  }
}
