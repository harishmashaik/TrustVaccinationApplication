package com.team4.getvaxi.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Child;
import com.team4.getvaxi.models.Vaccine;

import java.util.ArrayList;
import java.util.List;

public class ChildViewAdapter extends RecyclerView.Adapter<ChildViewHolder> {

  private List<Child> children;

  public ChildViewAdapter() {
    children = new ArrayList<>();
  }

  public void setChildren(List<Child> children) {
    this.children = children;
    notifyDataSetChanged(); // going to bind new data to Views.
  }

  @NonNull
  @Override
  public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View customView =
        LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    ChildViewHolder childViewHolder = new ChildViewHolder(customView);
    return childViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
    holder.bind(children.get(position), this);
  }

  @Override
  public int getItemCount() {
    return children.size();
  }

  @Override
  public int getItemViewType(int position) {
    return R.layout.custom_vaccinesbyage_vaccinelist_view;
  }
}
