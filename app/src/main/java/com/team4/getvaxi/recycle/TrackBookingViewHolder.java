package com.team4.getvaxi.recycle;


import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.CLC.BookingConfirmActivity;
import com.team4.getvaxi.CLC.BookingDeclineActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Booking;

;

public class TrackBookingViewHolder extends RecyclerView.ViewHolder {

    public static final String booking ="BOOKING";
    public static final String docID ="DOCUMENTID";

    private final TextView vaccineName;
    private final TextView childNameAndAGe;
    private final TextView dateofBooking;
    private final TextView statusOfBooking;

    private Booking user;
    private TrackBookingAdapter adapter;




    public TrackBookingViewHolder(@NonNull View layoutView) {
        super(layoutView);

        vaccineName = layoutView.findViewById(R.id.custom_user_track_booking_vaccinename);
        childNameAndAGe = layoutView.findViewById(R.id.custom_user_track_booking_childname_age);
        dateofBooking = layoutView.findViewById(R.id.custom_user_track_booking_dateofbooking);
        statusOfBooking = layoutView.findViewById(R.id.custom_user_track_booking_status);





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
        if(booking.getBookingStatus()) statusOfBooking.setText("Confirmed");
        else statusOfBooking.setText("Declined");
        //Picasso.get().load(booking.getAvatar_url()).into(userAvatarPic);

    }

}
