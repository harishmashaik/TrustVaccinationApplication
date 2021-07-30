package com.team4.getvaxi.recycle;

import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Message;

import java.time.LocalDateTime;

public class ReceiverMessageViewHolder extends RecyclerView.ViewHolder {

  private final TextView txtreceiverMessage;
  private final TextView txtSenderName;
  private final TextView dateMonth;
  private final TextView timeStamp;

  private Message receiverMessage;
  private VaccineAdapter adapter;

  public ReceiverMessageViewHolder(@NonNull View layoutView) {
    super(layoutView);

    txtreceiverMessage = layoutView.findViewById(R.id.text_gchat_message_other);
    txtSenderName = layoutView.findViewById(R.id.text_gchat_user_other);
    dateMonth = layoutView.findViewById(R.id.text_gchat_date_other);
    timeStamp = layoutView.findViewById(R.id.text_gchat_timestamp_other);
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public void bind(Message message) {
    this.receiverMessage = message;
    txtreceiverMessage.setText(message.getMessage());
    txtSenderName.setText(message.getSenderName());

    LocalDateTime tempDT = LocalDateTime.parse(message.getMessageDateTime());
    dateMonth.setText(tempDT.getMonth().toString() + " " + tempDT.getDayOfMonth());
    timeStamp.setText(tempDT.getHour() + ":" + tempDT.getMinute());
  }
}
