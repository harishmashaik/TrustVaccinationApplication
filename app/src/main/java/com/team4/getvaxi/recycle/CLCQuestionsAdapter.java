package com.team4.getvaxi.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Message;
import com.team4.getvaxi.models.Vaccine;

import java.util.ArrayList;
import java.util.List;

public class CLCQuestionsAdapter extends RecyclerView.Adapter<CLCQuestionsViewHolder> {

  private List<Message> messages;

  public CLCQuestionsAdapter() {
    messages = new ArrayList<>();
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
    notifyDataSetChanged(); // going to bind new data to Views.
  }

  @NonNull
  @Override
  public CLCQuestionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View customView =
        LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    CLCQuestionsViewHolder userViewHolder = new CLCQuestionsViewHolder(customView);
    return userViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull CLCQuestionsViewHolder holder, int position) {
    holder.bind(messages.get(position), this);
  }

  @Override
  public int getItemCount() {
    return messages.size();
  }

  @Override
  public int getItemViewType(int position) {
    return R.layout.custom_clc_questions_view;
  }
}
