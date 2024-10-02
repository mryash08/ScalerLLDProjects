package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.Gate;
import com.scaler.parking_lot.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingLotRepositoryImpl implements ParkingLotRepository{
    Map<Long, ParkingLot> getByGateId = new HashMap<>();
    Map<Long, ParkingLot> getByParkingLotId = new HashMap<>();
    private static int id = 1;
    @Override
    public Optional<ParkingLot> getParkingLotByGateId(long gateId) {
        return Optional.ofNullable(getByGateId.get(gateId));
    }

    @Override
    public Optional<ParkingLot> getParkingLotById(long id) {
        return Optional.ofNullable(getByParkingLotId.get(id));
    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        if(parkingLot.getId() == 0){
            parkingLot.setId(id++);
        }
        for (Gate gate : parkingLot.getGates())
            getByGateId.put(gate.getId(), parkingLot);

        getByParkingLotId.put(parkingLot.getId(), parkingLot);
        return parkingLot;
    }
}
