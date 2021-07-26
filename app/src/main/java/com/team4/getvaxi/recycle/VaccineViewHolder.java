package com.team4.getvaxi.recycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.BookVaccineActivity;
import com.team4.getvaxi.CLC.BookingConfirmActivity;
import com.team4.getvaxi.ChildInfoActivity;
import com.team4.getvaxi.HomeActivity;
import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Booking;
import com.team4.getvaxi.models.Vaccine;;
import java.io.Serializable;

public class VaccineViewHolder extends RecyclerView.ViewHolder {

  public static final String booking = "BOOKING";
  public static final String docID = "DOCUMENTID";

  private final TextView vaccineByAgeVName;

  private Vaccine vaccine;
  private VaccineAdapter adapter;

  public VaccineViewHolder(@NonNull View layoutView) {
    super(layoutView);

    vaccineByAgeVName = layoutView.findViewById(R.id.custom_vaccinebyage_vaccinename);

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

    layoutView.setOnClickListener(
        v -> {
          Intent i = new Intent(layoutView.getContext(), BookVaccineActivity.class);
          Bundle b = new Bundle();
          b.putSerializable("vaccineDetails", (Serializable) vaccine);
          i.putExtras(b);
          // i.putExtra("vaccineName", vaccine.getVaccineName());

          layoutView.getContext().startActivity(i);
        });
  }

  public void bind(Vaccine vaccine, VaccineAdapter adapter) {
    this.vaccine = vaccine;
    this.adapter = adapter;
    vaccineByAgeVName.setText(vaccine.getVaccineName());
    //        vaccineName.setText(booking.getVaccineName());
    //        childNameAndAGe.setText(booking.getName() + " " +booking.getAge());
    //        dateofBooking.setText(booking.getAppointmentDate());
    //

  }
}
