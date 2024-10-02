package com.scaler.parking_lot.services;

import com.scaler.parking_lot.exceptions.InvalidGateException;
import com.scaler.parking_lot.exceptions.TicketNotFoundException;

import com.scaler.parking_lot.models.Gate;
import com.scaler.parking_lot.models.GateType;
import com.scaler.parking_lot.models.Invoice;
import com.scaler.parking_lot.models.Ticket;
import com.scaler.parking_lot.models.*;
import com.scaler.parking_lot.respositories.GateRepository;
import com.scaler.parking_lot.respositories.InvoiceRepository;
import com.scaler.parking_lot.respositories.SlabRepository;
import com.scaler.parking_lot.respositories.TicketRepository;
import com.scaler.parking_lot.strategies.pricing.PricingStrategy;
import com.scaler.parking_lot.strategies.pricing.PricingStrategyFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class InvoiceServiceImpl implements InvoiceService {
    private TicketRepository ticketRepository;
    private GateRepository gateRepository;
    private PricingStrategyFactory pricingStrategyFactory;
    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(TicketRepository ticketRepository, GateRepository gateRepository, PricingStrategyFactory pricingStrategyFactory, InvoiceRepository invoiceRepository) {
        this.ticketRepository = ticketRepository;
        this.gateRepository = gateRepository;
        this.pricingStrategyFactory = pricingStrategyFactory;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice createInvoice(long ticketId, long gateId) throws TicketNotFoundException, InvalidGateException {
        Optional<Ticket> optionalTicket = ticketRepository.getTicketById(ticketId);
        if (optionalTicket.isEmpty())
            throw new TicketNotFoundException("Ticket is Invalid");

        Ticket ticket = optionalTicket.get();
        Optional<Gate> optionalGate = this.gateRepository.findById(gateId);
        if (optionalGate.isEmpty()) {
            throw new InvalidGateException("Invalid gate id");
        }
        Gate gate = optionalGate.get();
        // if (gate.getType().equals(GateType.ENTRY)) {
        //     throw new InvalidGateException("Vehicle trying to exit from entry gate");
        // }

        Date exitDate = new Date();
        PricingStrategy strategy = pricingStrategyFactory.getPricingStrategy(exitDate);
        
        double amount = strategy.calculateAmount(ticket.getEntryTime(), exitDate, ticket.getVehicle().getVehicleType());
        List<AdditionalService> services = ticket.getAdditionalServices();
        for(AdditionalService additionalService : services) {
            amount += additionalService.getCost();
        }

        Invoice invoice = new Invoice();
        invoice.setExitTime(exitDate);
        invoice.setGate(gate);
        invoice.setAmount(amount);
        invoice.setTicket(ticket);

        return invoiceRepository.save(invoice);

    }
}
