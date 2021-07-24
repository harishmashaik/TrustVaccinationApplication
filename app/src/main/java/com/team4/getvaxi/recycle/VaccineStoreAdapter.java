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

public class VaccineStoreAdapter extends RecyclerView.Adapter<VaccineStoreViewHolder> {

  private List<Vaccine> vaccinesInStore;

  public VaccineStoreAdapter() {
    vaccinesInStore = new ArrayList<>();
  }

  public void setVaccinesInStore(List<Vaccine> vaccinesInStore) {
    this.vaccinesInStore = vaccinesInStore;
    notifyDataSetChanged(); // going to bind new data to Views.
  }

  @NonNull
  @Override
  public VaccineStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View customView =
        LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    VaccineStoreViewHolder userViewHolder = new VaccineStoreViewHolder(customView);
    return userViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull VaccineStoreViewHolder holder, int position) {
    holder.bind(vaccinesInStore.get(position), this);
  }

  @Override
  public int getItemCount() {
    return vaccinesInStore.size();
  }

  @Override
  public int getItemViewType(int position) {
    return R.layout.custom_clc_vaccine_store_view;
  }
}
