package com.example.addroomshotelmanagement.services;

import com.example.addroomshotelmanagement.exceptions.UnAuthorizedAccess;
import com.example.addroomshotelmanagement.exceptions.UserNotFoundException;
import com.example.addroomshotelmanagement.models.Room;
import com.example.addroomshotelmanagement.models.RoomType;
import com.example.addroomshotelmanagement.models.User;
import com.example.addroomshotelmanagement.models.UserType;
import com.example.addroomshotelmanagement.repositories.RoomRepository;
import com.example.addroomshotelmanagement.repositories.UserRepository;

public class AddRoomService implements RoomService {

    RoomRepository roomRepository ;
    UserRepository userRepository;

    public AddRoomService(RoomRepository roomRepository,UserRepository userRepository){
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Room addRoom(long userId, String roomName, double price, String roomType, String description)
            throws UserNotFoundException, UnAuthorizedAccess {
         User user = userRepository.findById(userId);
         if(user == null){
             throw new UserNotFoundException("User Not Found");
         }
         if(!user.getUserType().equals(UserType.ADMIN)){
              throw new UnAuthorizedAccess("Only admin can add rooms");
         } 
         Room  room = new Room();
         room.setName(roomName);
         room.setPrice(price);
         if(roomType.equals("DELUXE")){
            room.setRoomType(RoomType.DELUXE);
         }else if(roomType.equals("SUITE")){
            room.setRoomType(RoomType.SUITE);
         }else{
            room.setRoomType(RoomType.SUPER_DELUXE);       
         }
         room.setDescription(description);
         

         return roomRepository.add(room);
    }
    
}
