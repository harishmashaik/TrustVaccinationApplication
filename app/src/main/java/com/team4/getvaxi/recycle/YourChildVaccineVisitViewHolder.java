package com.team4.getvaxi.recycle;

import android.content.Context;
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

public class YourChildVaccineVisitViewHolder extends RecyclerView.ViewHolder {

  public static final String booking = "BOOKING";
  public static final String docID = "DOCUMENTID";
  FirebaseFirestore db = FirebaseFirestore.getInstance();

  private final TextView vaccineName;
  private final TextView childNameAndAGe;
  private final TextView dateofBooking;
  private final TextView statusOfBooking;
  private final TextView remarksOfBooking;
  private final TextView infoOfBooking;
  private final MaterialButton cancelButton;

  private final MaterialCardView materialCardView;

  private String nameLabel;
  private String ageLabel;
  private String appDateLabel;
  private String vaccinatedLabel;

  private Booking user;
  private YourChildVaccineVisitAdapter adapter;

  public YourChildVaccineVisitViewHolder(@NonNull View layoutView) {
    super(layoutView);

    vaccineName = layoutView.findViewById(R.id.custom_user_track_booking_vaccinename);
    childNameAndAGe = layoutView.findViewById(R.id.custom_user_track_booking_childname_age);
    dateofBooking = layoutView.findViewById(R.id.custom_user_track_booking_dateofbooking);
    statusOfBooking = layoutView.findViewById(R.id.custom_user_track_booking_status);
    materialCardView = layoutView.findViewById(R.id.trackUserBookings_card);
    cancelButton = layoutView.findViewById(R.id.custom_user_track_booking_cancel);
    remarksOfBooking = layoutView.findViewById(R.id.custom_user_track_booking_remarks);
    infoOfBooking = layoutView.findViewById(R.id.custom_user_track_booking_remarks_label);

    nameLabel = layoutView.getContext().getString(R.string.user_track_bookings_name_label);
    ageLabel = layoutView.getContext().getString(R.string.user_track_bookings_dob_label);
    appDateLabel = layoutView.getContext().getString(R.string.user_track_bookings_appointment_date_label);
    vaccinatedLabel = layoutView.getContext().getString(R.string.booking_status_vacinated);

  }

  public void bind(Booking booking, YourChildVaccineVisitAdapter adapter) {
    this.user = booking;
    this.adapter = adapter;
    vaccineName.setText(booking.getVaccineName());
  StringBuilder remarks = new StringBuilder();
  remarks.append(vaccinatedLabel +" ");
  remarks.append(booking.getVaccinationCenterDetails().get("Name&Addr").toString());
  remarks.append(", on " + booking.getAppointmentDate());

    childNameAndAGe.setText(nameLabel+": " + booking.getName() + " ,  " +ageLabel+": " + booking.getAge());
    dateofBooking.setText(appDateLabel+": " + booking.getAppointmentDate());
    if (booking.getBoookingStatus().equals("CONFM")) {
      cancelButton.setVisibility(View.GONE);
      statusOfBooking.setText(R.string.booking_status_completed);
      if (booking.getVaccinationCenterDetails() != null)
       remarksOfBooking.setText(remarks);
      else remarksOfBooking.setVisibility(View.GONE);

    } else if (booking.getBookingReviewed() == false
        && booking.getBoookingStatus().equals("PEND")) {
      statusOfBooking.setText(R.string.booking_status_pending_with_CLSC);
      infoOfBooking.setVisibility(View.GONE);
      materialCardView.setCardBackgroundColor(rgb(238, 255, 230));

    } else if (booking.getBoookingStatus().equals("CANCELBYUS")) {
      statusOfBooking.setText(R.string.booking_status_cancelled);
      // cancelButton.setEnabled(false);
      cancelButton.setVisibility(View.GONE);
      materialCardView.setCardBackgroundColor(rgb(238, 215, 230));

    } else if (booking.getBoookingStatus().equals("DECL")) {
      statusOfBooking.setText(R.string.booking_status_declined);
      if (booking.getRemarks() != null) remarksOfBooking.setText(booking.getRemarks());
      else remarksOfBooking.setVisibility(View.GONE);
      materialCardView.setCardBackgroundColor(rgb(255, 170, 153));
    }
  }
}
