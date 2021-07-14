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


public class BookingsAdapter extends RecyclerView.Adapter<BookingViewHolder> {

    private List<Booking> bookings;



    public BookingsAdapter()
    {
        bookings = new ArrayList<>();
    }

    public void setBookings(List<Booking> bookings){
        this.bookings = bookings;
        notifyDataSetChanged(); //going to bind new data to Views.
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View customView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        BookingViewHolder userViewHolder = new BookingViewHolder(customView);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        holder.bind(bookings.get(position), this);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.custom_bookings_view;
    }
}
