package com.assignment.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.assignment.models.Bill;

public class BillRepoimpl implements BillRepository {

    Map<Long  , Bill> map = new HashMap<>();

    @Override
    public Bill save(Bill bill) {
       map.put(bill.getId(), bill);
       return bill;
    }

    @Override
    public Optional<Bill> findById(long billId) {
        return Optional.ofNullable(map.get(billId));
         
    }
    
}
