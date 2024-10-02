package com.scaler.parking_lot.respositories;

import com.scaler.parking_lot.models.Gate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepositoryImpl implements GateRepository{

    Map<Long, Gate> map = new HashMap<>();
    private static int id = 1;
    @Override
    public Optional<Gate> findById(long gateId) {
        return Optional.ofNullable(map.get(gateId));
    }

    @Override
    public Gate save(Gate gate) {
        if(gate.getId() == 0){
            gate.setId(id++);
        }
        map.put(gate.getId(), gate);
        return gate;
    }
}
