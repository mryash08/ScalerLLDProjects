package com.example.bookrooms.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.bookrooms.models.CustomerSession;

public class CustomerSessionRepositoryImpl implements CustomerSessionRepository{
    Map<Long, CustomerSession> map = new HashMap<>();
    @Override
    public CustomerSession save(CustomerSession customerSession) {
        map.put(customerSession.getUser().getId(),customerSession);
        return customerSession;
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        return Optional.ofNullable(map.get(userId));
    }
    
}
