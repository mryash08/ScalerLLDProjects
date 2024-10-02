package com.example.bookrooms.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.bookrooms.models.Booking;

public class BookingRepositoryImpl implements BookingRepository {
    List<Booking> bookings = new ArrayList<>();
    @Override
    public Booking save(Booking booking) {
        bookings.add(booking);
        return booking;
    }
    
}
