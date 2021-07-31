package com.team4.getvaxi.recycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.team4.getvaxi.R;
import com.team4.getvaxi.RescheduleOrCancelActivity;
import com.team4.getvaxi.models.Booking;

import java.io.Serializable;

import static android.graphics.Color.rgb;

;

public class TrackBookingViewHolder extends RecyclerView.ViewHolder {

  public static final String booking = "BOOKING";
  public static final String docID = "DOCUMENTID";
  FirebaseFirestore db = FirebaseFirestore.getInstance();

  private final TextView vaccineName;
  private final TextView childNameAndAGe;
  private final TextView dateofBooking;
  private final TextView statusOfBooking;
  private final MaterialButton cancelButton;

  private final MaterialCardView materialCardView;

  private Booking user;
  private TrackBookingAdapter adapter;

  public TrackBookingViewHolder(@NonNull View layoutView) {
    super(layoutView);

    vaccineName = layoutView.findViewById(R.id.custom_user_track_booking_vaccinename);
    childNameAndAGe = layoutView.findViewById(R.id.custom_user_track_booking_childname_age);
    dateofBooking = layoutView.findViewById(R.id.custom_user_track_booking_dateofbooking);
    statusOfBooking = layoutView.findViewById(R.id.custom_user_track_booking_status);
    materialCardView = layoutView.findViewById(R.id.trackUserBookings_card);
    cancelButton = layoutView.findViewById(R.id.custom_user_track_booking_cancel);

    cancelButton.setOnClickListener(
        v -> {
            Intent i = new Intent(layoutView.getContext(), RescheduleOrCancelActivity.class);
            Bundle b = new Bundle();
            b.putSerializable("FROMTRACKHOLDER", (Serializable) user);
            i.putExtras(b);
            layoutView.getContext().startActivity(i);

//          System.out.println("insid ethe cancel");
//          System.out.println(user.toString());
//
//          user.setBoookingStatus("CANCELBYUS");
//          user.setBookingReviewed(true);
//
//          db.collection("bookings").document(user.getFbDocID()).set(user);
//          Toast toast = Toast.makeText(v.getContext(), "Appointment Cancelled", Toast.LENGTH_LONG);
//          toast.show();
//          Intent i = new Intent(layoutView.getContext(), HomeActivity.class);
//          layoutView.getContext().startActivity(i);
        });
  }

  public void bind(Booking booking, TrackBookingAdapter adapter) {
    this.user = booking;
    this.adapter = adapter;
    vaccineName.setText(booking.getVaccineName());
    childNameAndAGe.setText(booking.getName() + " " + booking.getAge());
    dateofBooking.setText(booking.getAppointmentDate());
    if (booking.getBoookingStatus().equals("CONFM")) {
      statusOfBooking.setText("Confirmed");

    } else if (booking.getBookingReviewed() == false && booking.getBoookingStatus().equals("PEND")) {
      statusOfBooking.setText("Pending with CLC");
      materialCardView.setCardBackgroundColor(rgb(238, 255, 230));

    }
    else if (booking.getBoookingStatus().equals("CANCELBYUS")) {
        statusOfBooking.setText("Cancelled");
        // cancelButton.setEnabled(false);
        cancelButton.setVisibility(View.GONE);
        materialCardView.setCardBackgroundColor(rgb(238, 215, 230));

    }
    else if (booking.getBoookingStatus().equals("DECL")){
      statusOfBooking.setText("Declined");
      materialCardView.setCardBackgroundColor(rgb(255, 170, 153));
    }

    // Picasso.get().load(booking.getAvatar_url()).into(userAvatarPic);

  }
}
