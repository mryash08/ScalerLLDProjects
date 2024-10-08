package com.scaler.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scaler.models.WaitListPosition;

public class WaitListRepoImpl implements WaitListPositionRepository {

    private Map<Long, WaitListPosition> map;

    public WaitListRepoImpl() {
        this.map = new HashMap<>();
    }

    private static int idCounter = 0;

    @Override
    public WaitListPosition save(WaitListPosition waitListPosition) {
        if(waitListPosition.getId() == 0){
            waitListPosition.setId(++idCounter);
        }
        map.put(waitListPosition.getId(), waitListPosition);
        return waitListPosition;
    }

    @Override
    public List<WaitListPosition> findAll() {
        return map.values().stream().toList();
    }

    @Override
    public WaitListPosition delete(WaitListPosition waitListPosition) {
        return map.remove(waitListPosition.getId());
    }
    
}
