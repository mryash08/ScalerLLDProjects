package com.example.bookrooms.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import com.example.bookrooms.exceptions.InvalidRoomException;
import com.example.bookrooms.exceptions.UserNotFoundException;
import com.example.bookrooms.models.Booking;
import com.example.bookrooms.models.CustomerSession;
import com.example.bookrooms.models.CustomerSessionStatus;
import com.example.bookrooms.models.Room;
import com.example.bookrooms.models.User;
import com.example.bookrooms.repositories.BookingRepository;
import com.example.bookrooms.repositories.CustomerSessionRepository;
import com.example.bookrooms.repositories.RoomRepository;
import com.example.bookrooms.repositories.UserRepository;

public class BookingServiceImpl implements BookingService{

    private BookingRepository bookingRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    CustomerSessionRepository customerSessionRepository;
    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository, UserRepository userRepository, CustomerSessionRepository customerSessionRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.customerSessionRepository = customerSessionRepository;
    }

    @Override
    public Booking makeBooking(long userId, Map<Long, Integer> roomsToBeBooked)
            throws UserNotFoundException, InvalidRoomException {
        Optional<CustomerSession> optionalCustomerSession = customerSessionRepository.findActiveCustomerSessionByUserId(userId);
        CustomerSession customerSession;
        if (optionalCustomerSession.isEmpty()) {
            customerSession = new CustomerSession();
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) 
                throw new UserNotFoundException("User not found");
            
            customerSession.setUser(optionalUser.get());
            customerSession.setCustomerSessionStatus(CustomerSessionStatus.ACTIVE);
            customerSession = customerSessionRepository.save(customerSession);
        } else 
            customerSession = optionalCustomerSession.get();

        Booking booking = new Booking();
        booking.setCustomerSession(customerSession);
        Map<Room, Integer> bookedRoomsQtyMap = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : roomsToBeBooked.entrySet()) {
            Optional<Room> optionalRoom = roomRepository.findById(entry.getKey());
            if (optionalRoom.isEmpty()) {
                throw new InvalidRoomException("Room doesn't exist");
            } else {
                bookedRoomsQtyMap.put(optionalRoom.get(), entry.getValue());
            }
        }
        booking.setBookedRooms(bookedRoomsQtyMap);
        booking = bookingRepository.save(booking);
        return booking;
            
    }
    
}
