package com.scaler.parking_lot.controllers;

import com.scaler.parking_lot.dtos.GenerateTicketRequestDto;
import com.scaler.parking_lot.dtos.GenerateTicketResponseDto;
import com.scaler.parking_lot.dtos.ResponseStatus;
import com.scaler.parking_lot.models.Ticket;
import com.scaler.parking_lot.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto){
        GenerateTicketResponseDto generateTicketResponseDto = new GenerateTicketResponseDto();
        try {
            generateTicketResponseDto.setTicket(ticketService.generateTicket(requestDto.getGateId(),requestDto.getRegistrationNumber(),requestDto.getVehicleType()));
            generateTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            generateTicketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return generateTicketResponseDto;
    }
}
