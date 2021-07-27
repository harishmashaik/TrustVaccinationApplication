package com.team4.getvaxi.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Message;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter {

  private static final int VIEW_TYPE_MESSAGE_SENT = 1;
  private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

  private List<Message> messagesList;

  public QuestionsAdapter() {
    messagesList = new ArrayList<>();
  }

  public void setMessagesList(List<Message> messagesList) {
    this.messagesList = messagesList;
    notifyDataSetChanged(); // going to bind new data to Views.
  }

  @NonNull
  @Override
  public VaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View customView =
        LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    VaccineViewHolder userViewHolder = new VaccineViewHolder(customView);
    return userViewHolder;

    if (viewType == VIEW_TYPE_MESSAGE_SENT) {
      view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.item_message_sent, parent, false);
      return new SentMessageHolder(view);
    } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
      view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.item_message_received, parent, false);
      return new ReceivedMessageHolder(view);
    }

    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    Message message = (Message) messagesList.get(position);

    switch (holder.getItemViewType()) {
      case VIEW_TYPE_MESSAGE_SENT:
        ((SentMessageHolder) holder).bind(message);
        break;
      case VIEW_TYPE_MESSAGE_RECEIVED:
        ((ReceivedMessageHolder) holder).bind(message);
    }

  }

//  @Override
//  public void onBindViewHolder(@NonNull VaccineViewHolder holder, int position) {
//    holder.bind(message.get(position), this);
//  }

  @Override
  public int getItemCount() {
    return messagesList.size();
  }

  @Override
  public int getItemViewType(int position) {
  //  return R.layout.custom_vaccinesbyage_vaccinelist_view;
    Message message = (Message) messagesList.get(position);

    if (message.getSender().getUserId().equals(SendBird.getCurrentUser().getUserId())) {
      // If the current user is the sender of the message
      return VIEW_TYPE_MESSAGE_SENT;
    } else {
      // If some other user sent the message
      return VIEW_TYPE_MESSAGE_RECEIVED;
    }
  }
}
