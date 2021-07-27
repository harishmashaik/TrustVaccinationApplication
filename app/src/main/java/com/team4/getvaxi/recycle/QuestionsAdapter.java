package com.team4.getvaxi.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Message;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter {

  private static final int VIEW_TYPE_MESSAGE_SENT = 1;
  private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

  private List<Message> messagesList;
  private FirebaseAuth mAuth;

  public QuestionsAdapter() {
    messagesList = new ArrayList<>();
  }

  public void setMessagesList(List<Message> messagesList) {
    this.messagesList = messagesList;
    mAuth = FirebaseAuth.getInstance();

    notifyDataSetChanged(); // going to bind new data to Views.
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//    final View customView =
//        LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
//    VaccineViewHolder userViewHolder = new VaccineViewHolder(customView);
//    return userViewHolder;

    View view;

    if (viewType == VIEW_TYPE_MESSAGE_SENT) {
      view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.custom_question_sender_view, parent, false);
      return new SenderMessageViewHolder(view);
    } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
      view = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.custom_question_receiver_view, parent, false);
      return new ReceiverMessageViewHolder(view);
    }

    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    Message message = (Message) messagesList.get(position);

    switch (holder.getItemViewType()) {
      case VIEW_TYPE_MESSAGE_SENT:
        ((SenderMessageViewHolder) holder).bind(message);
        break;
      case VIEW_TYPE_MESSAGE_RECEIVED:
        ((ReceiverMessageViewHolder) holder).bind(message);
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

    if(mAuth.getCurrentUser()!=null)
    {
      if (message.getUserId().equals(mAuth.getCurrentUser().getUid()) && message.getUserType().equals("USER")) {
        // If the current user is the sender of the message
        return VIEW_TYPE_MESSAGE_SENT;
      } else {
        // If some other user sent the message
        return VIEW_TYPE_MESSAGE_RECEIVED;
      }
    }

    return VIEW_TYPE_MESSAGE_RECEIVED;

  }
}
