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

public class DiseasePreventAdapter extends RecyclerView.Adapter<DiseasePreventViewHolder> {

  private List<String> diseases;

  public DiseasePreventAdapter() {
    diseases = new ArrayList<>();
  }

  public void setDiseases(List<String> diseases) {
    this.diseases = diseases;
    notifyDataSetChanged(); // going to bind new data to Views.
  }

  @NonNull
  @Override
  public DiseasePreventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View customView =
        LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    DiseasePreventViewHolder userViewHolder = new DiseasePreventViewHolder(customView);
    return userViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull DiseasePreventViewHolder holder, int position) {
    holder.bind(diseases.get(position), this);
  }

  @Override
  public int getItemCount() {
    return diseases.size();
  }

  @Override
  public int getItemViewType(int position) {
    return R.layout.custom_vaccinesbyage_vaccinelist_view;
  }
}
