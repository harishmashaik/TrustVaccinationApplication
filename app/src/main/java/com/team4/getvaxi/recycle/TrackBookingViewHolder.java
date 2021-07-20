package com.team4.getvaxi.recycle;


import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Booking;

;import static android.graphics.Color.rgb;

public class TrackBookingViewHolder extends RecyclerView.ViewHolder {

    public static final String booking ="BOOKING";
    public static final String docID ="DOCUMENTID";

    private final TextView vaccineName;
    private final TextView childNameAndAGe;
    private final TextView dateofBooking;
    private final TextView statusOfBooking;

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





//        layoutView.setOnClickListener(v -> {
//            Intent i = new Intent(layoutView.getContext(), UserActivity.class);
//            i.putExtra(username,user.getUser_name());
//            layoutView.getContext().startActivity(i);
//        });


    }

    public void bind(Booking booking, TrackBookingAdapter adapter){
        this.user = booking;
        this.adapter = adapter;
        vaccineName.setText(booking.getVaccineName());
        childNameAndAGe.setText(booking.getName() + " " +booking.getAge());
        dateofBooking.setText(booking.getAppointmentDate());
        if(booking.getBoookingStatus().equals("CONFM")){
            statusOfBooking.setText("Confirmed");

        }
        else if(booking.getBookingReviewed() == false) {
            statusOfBooking.setText("Pending with CLC");
            materialCardView.setCardBackgroundColor(rgb(238,255,230));


        }
        else{
            statusOfBooking.setText("Declined");
            materialCardView.setCardBackgroundColor(rgb(255,170,153));
        }

        //Picasso.get().load(booking.getAvatar_url()).into(userAvatarPic);

    }

}
