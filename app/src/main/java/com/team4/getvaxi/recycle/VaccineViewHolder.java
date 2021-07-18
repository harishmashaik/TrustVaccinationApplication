package com.team4.getvaxi.recycle;


import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.CLC.BookingConfirmActivity;
import com.team4.getvaxi.HomeActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.models.Vaccine;

;

public class VaccineViewHolder extends RecyclerView.ViewHolder {

    public static final String booking ="BOOKING";
    public static final String docID ="DOCUMENTID";

    private final TextView vaccineName;
    private final TextView childNameAndAGe;
    private final TextView dateofBooking;

    private final Button buttonConfirm;
    private final Button buttonDecline;

    private Vaccine vaccine;
    private VaccineAdapter adapter;




    public VaccineViewHolder(@NonNull View layoutView) {
        super(layoutView);

        vaccineName = layoutView.findViewById(R.id.custom_booking_vaccinename);
        childNameAndAGe = layoutView.findViewById(R.id.custom_booking_childname_age);
        dateofBooking = layoutView.findViewById(R.id.custom_booking_dateofbooking);

        buttonConfirm = layoutView.findViewById(R.id.custom_booking_confirm);
        buttonDecline = layoutView.findViewById(R.id.custom_booking_decline);

        buttonConfirm.setOnClickListener(v -> {
//            Intent i = new Intent(layoutView.getContext(), BookingConfirmActivity.class);
//            i.putExtra(booking, (Parcelable) vaccine);
//            layoutView.getContext().startActivity(i);
        });

        buttonDecline.setOnClickListener(v -> {
            Intent i = new Intent(layoutView.getContext(), HomeActivity.class);
            //i.putExtra(username,user.getUser_name());
            layoutView.getContext().startActivity(i);
        });


//        layoutView.setOnClickListener(v -> {
//            Intent i = new Intent(layoutView.getContext(), UserActivity.class);
//            i.putExtra(username,user.getUser_name());
//            layoutView.getContext().startActivity(i);
//        });


    }

    public void bind(Vaccine vaccine, VaccineAdapter adapter){
        this.vaccine = vaccine;
        this.adapter = adapter;
//        vaccineName.setText(booking.getVaccineName());
//        childNameAndAGe.setText(booking.getName() + " " +booking.getAge());
//        dateofBooking.setText(booking.getAppointmentDate());
//

    }

}
