package com.scaler.parking_lot.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.scaler.parking_lot.exceptions.InvalidParkingLotException;
import com.scaler.parking_lot.models.ParkingFloor;
import com.scaler.parking_lot.models.ParkingLot;
import com.scaler.parking_lot.models.ParkingSpot;
import com.scaler.parking_lot.models.ParkingSpotStatus;
import com.scaler.parking_lot.models.VehicleType;
import com.scaler.parking_lot.respositories.ParkingLotRepository;
import com.scaler.parking_lot.utils.ParkingFloorUtils;

public class ParkingLotServiceImpl implements ParkingLotService {
    ParkingLotRepository parkingLotRepository;

    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public Map<ParkingFloor, Map<String, Integer>> getParkingLotCapacity(long parkingLotId, List<Long> parkingFloorIds,
            List<VehicleType> requiredVehicleTypes) throws InvalidParkingLotException {
                Optional<ParkingLot> parkingLotOptional = parkingLotRepository.getParkingLotById(parkingLotId);
        if(parkingLotOptional.isEmpty()){
            throw new InvalidParkingLotException("Invalid Parking Lot Id");
        }

        ParkingLot parkingLot = parkingLotOptional.get();
        Map<ParkingFloor, Map<String, Integer>> map = new HashMap<>();
        Set<Long> parkingFloorIdSet = parkingFloorIds == null ? new HashSet<>(): new HashSet<>(parkingFloorIds);

        if(requiredVehicleTypes == null || requiredVehicleTypes.isEmpty()){
            requiredVehicleTypes = Arrays.asList(VehicleType.values());
        }

        for(ParkingFloor parkingFloor : parkingLot.getParkingFloors()){
            if(parkingFloorIdSet.size() > 0 && !parkingFloorIdSet.contains(parkingFloor.getId())){
                continue;
            }
            Map<String, Integer> vehicleTypeIntegerMap = new HashMap<>();
            for(VehicleType vehicleType : requiredVehicleTypes){
                vehicleTypeIntegerMap.put(vehicleType.name(), ParkingFloorUtils.calculateAvailableSpotsByVehicleType(parkingFloor, vehicleType));
            }
            map.put(parkingFloor, vehicleTypeIntegerMap);
        }
                return map;
        
    }
    
}
