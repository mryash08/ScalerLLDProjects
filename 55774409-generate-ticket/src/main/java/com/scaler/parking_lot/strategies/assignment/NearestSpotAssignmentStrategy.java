package com.scaler.parking_lot.strategies.assignment;

import com.scaler.parking_lot.models.*;

import java.util.*;

public class NearestSpotAssignmentStrategy implements SpotAssignmentStrategy{

    @Override
    public Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        for(ParkingFloor floor : parkingLot.getParkingFloors()){
            if(floor.getStatus().equals(FloorStatus.OPERATIONAL)){
                for(ParkingSpot spot : floor.getSpots()){
                    if(spot.getStatus().equals(ParkingSpotStatus.AVAILABLE) && spot.getSupportedVehicleType().equals(vehicleType)){
                        spot.setStatus(ParkingSpotStatus.OCCUPIED);
                        return Optional.of(spot);
                    }
                }
            }
        }
        return Optional.empty();
    }
}
