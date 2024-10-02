package com.example.bookrooms.repositories;

import java.util.Optional;

import com.example.bookrooms.models.Room;
import com.example.bookrooms.models.RoomType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepositoryImpl implements RoomRepository{

    Map<Long,Room> map = new HashMap<>();
    List<Room> rooms = new ArrayList<>();

    @Override
    public Optional<Room> findById(long roomId) {
        return Optional.ofNullable(map.get(roomId));
    }

    @Override
    public Room save(Room room) {
        map.put(room.getId(), room);
        return room;
    }
    
}
