package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TicketRepositoryImpl implements TicketRepository{
    Map<Long, Ticket> map = new HashMap<>();
    private static int id = 0;
    @Override
    public Ticket save(Ticket ticket) {
        if(ticket.getId() == 0){
            ticket.setId(id++);
        }
        map.put(ticket.getId(), ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> getTicketById(long ticketId) {
        return Optional.ofNullable(map.get(ticketId));
    }
}
