package com.scaler.parking_lot.services;

import com.scaler.parking_lot.exceptions.InvalidGateException;
import com.scaler.parking_lot.exceptions.InvalidParkingLotException;
import com.scaler.parking_lot.exceptions.ParkingSpotNotAvailableException;
import com.scaler.parking_lot.models.*;
import com.scaler.parking_lot.respositories.GateRepository;
import com.scaler.parking_lot.respositories.ParkingLotRepository;
import com.scaler.parking_lot.respositories.TicketRepository;
import com.scaler.parking_lot.respositories.VehicleRepository;
import com.scaler.parking_lot.strategies.assignment.NearestSpotAssignmentStrategy;
import com.scaler.parking_lot.strategies.assignment.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketServiceImpl implements TicketService{
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private TicketRepository ticketRepository;
    private ParkingLotRepository parkingLotRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    public TicketServiceImpl(GateRepository gateRepository, VehicleRepository vehicleRepository, TicketRepository ticketRepository, ParkingLotRepository parkingLotRepository,SpotAssignmentStrategy spotAssignmentStrategy) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
    }
    @Override
    public Ticket generateTicket(int gateId, String registrationNumber, String vehicleType) throws InvalidGateException, InvalidParkingLotException, ParkingSpotNotAvailableException {
        Optional<Gate> optionalGate = gateRepository.findById(gateId);
        if(optionalGate.isEmpty() || optionalGate.get().getType().equals(GateType.EXIT))
            throw new InvalidGateException("Invalid Gate Id");
        Optional<ParkingLot> optionalParkingLot = parkingLotRepository.getParkingLotByGateId(gateId);
        if(optionalParkingLot.isEmpty())
            throw new InvalidParkingLotException("Parking Lot is Unavailable");

        Optional<Vehicle> optionalVehicle = vehicleRepository.getVehicleByRegistrationNumber(registrationNumber);
        Vehicle saveVehicle;
        if(optionalVehicle.isEmpty()){
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleType(VehicleType.valueOf(vehicleType));
            vehicle.setRegistrationNumber(registrationNumber);
            saveVehicle = vehicleRepository.save(vehicle);
        }else {
            saveVehicle = optionalVehicle.get();
        }
        Optional<ParkingSpot> spot = spotAssignmentStrategy.assignSpot(optionalParkingLot.get(), VehicleType.valueOf(vehicleType));
        if(spot.isEmpty())
            throw new ParkingSpotNotAvailableException("Parking Spot is Unavailable");
        Ticket ticket = new Ticket();
        ticket.setGate(optionalGate.get());
        ticket.setEntryTime(new Date());
        ticket.setVehicle(saveVehicle);
        ticket.setParkingSpot(spot.get());
        ticket.setParkingAttendant(optionalGate.get().getParkingAttendant());

        return ticketRepository.save(ticket);
    }
}

























