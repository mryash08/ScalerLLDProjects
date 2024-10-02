package com.scaler.parking_lot.respositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.scaler.parking_lot.models.Gate;
import com.scaler.parking_lot.models.ParkingLot;

public class ParkingLotRepoImpl implements ParkingLotRepository {
    Map<Long,ParkingLot> byId;
    Map<Long,ParkingLot> byGateId;

    public ParkingLotRepoImpl() {
        byId = new HashMap<>();
        byGateId = new HashMap<>();
    }

    @Override
    public Optional<ParkingLot> getParkingLotByGateId(long gateId) {
        return Optional.ofNullable(byGateId.get(gateId));
    }

    @Override
    public Optional<ParkingLot> getParkingLotById(long id) {
        return Optional.ofNullable(byId.get(id));

    }

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        byId.put(parkingLot.getId(), parkingLot);
        for(Gate id : parkingLot.getGates()){
            byGateId.put(id.getId(), parkingLot);
        }
        return parkingLot;

    }
    
}
