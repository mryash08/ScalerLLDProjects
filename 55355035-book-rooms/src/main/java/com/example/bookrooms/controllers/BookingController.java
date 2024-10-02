package com.example.bookrooms.controllers;

import com.example.bookrooms.dtos.MakeBookingRequestDto;
import com.example.bookrooms.dtos.MakeBookingResponseDto;
import com.example.bookrooms.dtos.ResponseStatus;
import com.example.bookrooms.exceptions.InvalidRoomException;
import com.example.bookrooms.exceptions.UserNotFoundException;
import com.example.bookrooms.models.Booking;
import com.example.bookrooms.services.BookingService;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public MakeBookingResponseDto makeBooking(MakeBookingRequestDto makeBookingRequestDto) {
        MakeBookingResponseDto dto = new MakeBookingResponseDto();
        try {
            Booking booking = bookingService.makeBooking(makeBookingRequestDto.getUserId(), makeBookingRequestDto.getBookedRooms());
            dto.setBooking(booking);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | InvalidRoomException e) {
            dto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return dto;
    }
}
