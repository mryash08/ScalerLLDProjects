package com.example.addroomshotelmanagement.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.addroomshotelmanagement.models.Room;
import com.example.addroomshotelmanagement.models.RoomType;

public class RoomRepositoryimpl implements RoomRepository {
    Map<RoomType,List<Room>> map = new HashMap<>();
    List<Room> rooms = new ArrayList<>();

    @Override
    public Room add(Room room) {
        List<Room> roomsMap = map.getOrDefault(room.getRoomType(), new ArrayList<>());
        roomsMap.add(room);
        map.put(room.getRoomType(),roomsMap);
        rooms.add(room);
        return room;
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public List<Room> getRoomsByRoomType(RoomType roomType) {
        return map.get(roomType);
    }

    @Override
    public Room save(Room room) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
