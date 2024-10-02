package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.ParkingLot;

import java.util.Optional;

public interface ParkingLotRepository {

    // Do not change the method signatures, feel free to add new methods

    public Optional<ParkingLot> getParkingLotByGateId(long gateId);

    public Optional<ParkingLot> getParkingLotById(long parkingLotId);

    public ParkingLot save(ParkingLot parkingLot);
}
