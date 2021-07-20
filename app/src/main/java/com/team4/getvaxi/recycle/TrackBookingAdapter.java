package com.team4.getvaxi.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team4.getvaxi.R;
import com.team4.getvaxi.models.Booking;

import java.util.ArrayList;
import java.util.List;

public class TrackBookingAdapter extends RecyclerView.Adapter<TrackBookingViewHolder> {

  private List<Booking> bookings;

  public TrackBookingAdapter() {
    bookings = new ArrayList<>();
  }

  public void setBookings(List<Booking> bookings) {
    this.bookings = bookings;
    notifyDataSetChanged(); // going to bind new data to Views.
  }

  @NonNull
  @Override
  public TrackBookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final View customView =
        LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    TrackBookingViewHolder userViewHolder = new TrackBookingViewHolder(customView);
    return userViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull TrackBookingViewHolder holder, int position) {
    holder.bind(bookings.get(position), this);
  }

  @Override
  public int getItemCount() {
    return bookings.size();
  }

  @Override
  public int getItemViewType(int position) {
    return R.layout.custom_user_track_bookings_view;
  }
}
