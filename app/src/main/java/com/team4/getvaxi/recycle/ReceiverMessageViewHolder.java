package com.team4.getvaxi.recycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.BookVaccineActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Message;

import java.io.Serializable;


public class ReceiverMessageViewHolder extends RecyclerView.ViewHolder {

  public static final String booking = "BOOKING";
  public static final String docID = "DOCUMENTID";

  private final TextView txtreceiverMessage;
  private final TextView txtSenderName;

  private Message receiverMessage;
  private VaccineAdapter adapter;

  public ReceiverMessageViewHolder(@NonNull View layoutView) {
    super(layoutView);

      txtreceiverMessage= layoutView.findViewById(R.id.text_gchat_message_other);
      txtSenderName = layoutView.findViewById(R.id.text_gchat_user_other);

    //        buttonConfirm.setOnClickListener(v -> {
    ////            Intent i = new Intent(layoutView.getContext(), BookingConfirmActivity.class);
    ////            i.putExtra(booking, (Parcelable) vaccine);
    ////            layoutView.getContext().startActivity(i);
    //        });

    //        buttonDecline.setOnClickListener(v -> {
    //            Intent i = new Intent(layoutView.getContext(), HomeActivity.class);
    //            //i.putExtra(username,user.getUser_name());
    //            layoutView.getContext().startActivity(i);
    //        });


  }

  public void bind(Message message) {
    this.receiverMessage = message;
      txtreceiverMessage.setText(message.getMessage());
      txtSenderName.setText(message.getSenderName());

    //this.adapter = adapter;
    //vaccineByAgeVName.setText(vaccine.getVaccineName());
    //        vaccineName.setText(booking.getVaccineName());
    //        childNameAndAGe.setText(booking.getName() + " " +booking.getAge());
    //        dateofBooking.setText(booking.getAppointmentDate());
    //

  }
}
