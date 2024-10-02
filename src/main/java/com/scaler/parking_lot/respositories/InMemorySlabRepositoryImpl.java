package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.Slab;
import com.scaler.parking_lot.models.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemorySlabRepositoryImpl implements SlabRepository{

    Map<VehicleType, List<Slab>> map;
    public InMemorySlabRepositoryImpl() {
        map = new HashMap<>();
    }
    @Override
    public List<Slab> getSortedSlabsByVehicleType(VehicleType vehicleType) {
        return map.get(vehicleType);
    }

    @Override
    public Slab save(Slab slab) {
        map.putIfAbsent(slab.getVehicleType(), new ArrayList<>());
        map.get(slab.getVehicleType()).add(slab);
        return slab;
    }
}
