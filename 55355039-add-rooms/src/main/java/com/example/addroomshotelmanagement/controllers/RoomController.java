package com.example.addroomshotelmanagement.controllers;

import com.example.addroomshotelmanagement.dtos.AddRoomRequestDto;
import com.example.addroomshotelmanagement.dtos.AddRoomResponseDto;
import com.example.addroomshotelmanagement.dtos.ResponseStatus;
import com.example.addroomshotelmanagement.models.Room;
import com.example.addroomshotelmanagement.services.RoomService;

public class RoomController {
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public AddRoomResponseDto addRoom(AddRoomRequestDto requestDto) {
        AddRoomResponseDto dto = new AddRoomResponseDto();

        try{
        Room room = roomService.addRoom(requestDto.getUserId(), requestDto.getName(), requestDto.getPrice(), requestDto.getRoomType(), requestDto.getDescription());
        dto.setRoom(room);
        dto.setResponseStatus(ResponseStatus.SUCCESS);

        }catch(Exception e) {
            dto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return dto;
    }
}