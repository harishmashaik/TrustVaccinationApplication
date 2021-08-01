package com.team4.getvaxi.recycle;


import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.team4.getvaxi.CLC.BookingConfirmActivity;
import com.team4.getvaxi.CLC.BookingDeclineActivity;
import com.team4.getvaxi.HomeActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Booking;

;import java.io.Serializable;

import static android.graphics.Color.rgb;

public class BookingViewHolder extends RecyclerView.ViewHolder {

    public static final String booking ="BOOKING";
    public static final String docID ="DOCUMENTID";

    private final TextView vaccineName;
    private final TextView childNameAndAGe;
    private final TextView dateofBooking;

    private final MaterialCardView materialCardView;


    private final Button buttonConfirm;
    private final Button buttonDecline;
    
    private Booking user;
    private BookingsAdapter adapter;




    public BookingViewHolder(@NonNull View layoutView) {
        super(layoutView);

        vaccineName = layoutView.findViewById(R.id.custom_booking_vaccinename);
        childNameAndAGe = layoutView.findViewById(R.id.custom_booking_childname_age);
        dateofBooking = layoutView.findViewById(R.id.custom_booking_dateofbooking);

        buttonConfirm = layoutView.findViewById(R.id.custom_booking_confirm);
        buttonDecline = layoutView.findViewById(R.id.custom_booking_decline);

        materialCardView = layoutView.findViewById(R.id.clc_bookings_card);

    buttonConfirm.setOnClickListener(
        v -> {
          Intent i = new Intent(layoutView.getContext(), BookingConfirmActivity.class);
          i.putExtra(booking, user);
          layoutView.getContext().startActivity(i);
        });

        buttonDecline.setOnClickListener(v -> {
            Intent i = new Intent(layoutView.getContext(), BookingDeclineActivity.class);
            i.putExtra(booking,user);
            layoutView.getContext().startActivity(i);
        });

    }

    public void bind(Booking booking, BookingsAdapter adapter){
        this.user = booking;
        this.adapter = adapter;
        vaccineName.setText(booking.getVaccineName());
        childNameAndAGe.setText(booking.getName() + " " +booking.getAge());
        dateofBooking.setText(booking.getAppointmentDate());
        if(booking.getBookingReviewed()){
            materialCardView.setCardBackgroundColor(rgb(255,255,230));

        }
        else{
            materialCardView.setCardBackgroundColor(rgb(255,170,153));
        }

        if(booking.getBoookingStatus().equals("DECL")){
            buttonDecline.setEnabled(false);

        }
        if(booking.getBoookingStatus().equals("CONFM")){
            buttonConfirm.setEnabled(false);

        }

    }

}
