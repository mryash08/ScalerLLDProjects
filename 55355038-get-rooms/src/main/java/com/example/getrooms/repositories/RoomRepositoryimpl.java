package com.example.getrooms.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.getrooms.models.Room;
import com.example.getrooms.models.RoomType;

public class RoomRepositoryimpl implements RoomRepository {
    private Map<Long, Room> roomsMap;
    private static long idCounter = 0;

    public RoomRepositoryimpl() {
        roomsMap = new HashMap<>();
    }

    @Override
    public Room add(Room room) {
        if (room.getId() == 0) {
            room.setId(++idCounter);
        }
        roomsMap.put(room.getId(), room);
        return room;
    }

    @Override
    public List<Room> getRooms() {
        return roomsMap.values().stream().toList();
    }

    @Override
    public List<Room> getRoomsByRoomType(RoomType roomType) {
        return roomsMap.values().stream().filter(room -> room.getRoomType().equals(roomType)).toList();
    }

    @Override
    public Room save(Room room) {
        if (room.getId() == 0) {
            room.setId(++idCounter);
        }
        roomsMap.put(idCounter, room);
        return room;
    }
    
}
