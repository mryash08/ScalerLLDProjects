package com.example.getrooms.services;

import java.util.List;

import com.example.getrooms.models.Room;
import com.example.getrooms.models.RoomType;
import com.example.getrooms.repositories.RoomRepository;

public class Bookroom implements RoomService{
    RoomRepository repository ;

    public Bookroom(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Room> getRooms(String roomType) throws Exception {
        if(roomType == null) return repository.getRooms();
        return repository.getRoomsByRoomType(RoomType.valueOf(roomType));
    }
    
}
