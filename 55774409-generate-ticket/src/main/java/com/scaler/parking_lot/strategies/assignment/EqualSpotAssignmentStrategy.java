package com.scaler.parking_lot.strategies.assignment;

import com.scaler.parking_lot.models.*;

import java.util.*;

public class EqualSpotAssignmentStrategy implements SpotAssignmentStrategy{
    @Override
    public Optional<ParkingSpot> assignSpot(ParkingLot parkingLot, VehicleType vehicleType) {
        Map<Long,Integer> map = new HashMap<>();
        for(ParkingFloor floor : parkingLot.getParkingFloors()){
            if(floor.getStatus().equals(FloorStatus.OPERATIONAL)){
                for(ParkingSpot spot : floor.getSpots()){
                    if(spot.getStatus().equals(ParkingSpotStatus.AVAILABLE) && spot.getSupportedVehicleType().equals(vehicleType)){
                        map.put(floor.getId(),map.getOrDefault(floor.getId(),0)+1);
                    }
                }
            }
        }


        int min = Integer.MAX_VALUE;
        Long floorId = 0L;
        for(Long val : map.keySet()){
            if(map.get(val) < min){
                min = map.get(val);
                floorId = val;
            }else if(map.get(val) == min){
                floorId = Math.min(floorId,val);
            }
        }

        List<ParkingSpot> parkingSpots = new ArrayList<>();
        for(ParkingFloor floor : parkingLot.getParkingFloors()){
            if(floor.getId() == floorId){
                parkingSpots = floor.getSpots();
            }
        }
        for(ParkingSpot parkingSpot : parkingSpots){
            if(parkingSpot.getStatus().equals(ParkingSpotStatus.AVAILABLE) && parkingSpot.getSupportedVehicleType().equals(vehicleType)){
                parkingSpot.setStatus(ParkingSpotStatus.OCCUPIED);
                return Optional.of(parkingSpot);
            }
        }
        return Optional.empty();
    }
}
