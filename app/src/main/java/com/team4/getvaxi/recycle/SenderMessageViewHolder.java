package com.team4.getvaxi.recycle;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.BookVaccineActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Message;

import java.io.Serializable;
import java.time.LocalDateTime;;

public class SenderMessageViewHolder extends RecyclerView.ViewHolder {

  private final TextView mainMessge;
  private final TextView dateMonth;
  private final TextView timeStamp;

  private Message senderMessage;
  private QuestionsAdapter adapter;

  public SenderMessageViewHolder(@NonNull View layoutView) {
    super(layoutView);

    mainMessge = layoutView.findViewById(R.id.text_gchat_message_me);
    dateMonth = layoutView.findViewById(R.id.text_gchat_date_me);
    timeStamp = layoutView.findViewById(R.id.text_gchat_timestamp_me);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public void bind(Message senderMessage) {
    this.senderMessage = senderMessage;
    mainMessge.setText(senderMessage.getMessage());
    LocalDateTime tempDT = LocalDateTime.parse(senderMessage.getMessageDateTime());
    dateMonth.setText(tempDT.getMonth().toString() + " " + tempDT.getDayOfMonth());
    timeStamp.setText(tempDT.getHour() + ":" + tempDT.getMinute());


  }
}
