package com.scaler.repositories;

import com.scaler.models.CustomerSession;
import com.scaler.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerSessionRepositoryImpl implements CustomerSessionRepository{
    Map<Long,CustomerSession> map = new HashMap<>();
    @Override
    public CustomerSession save(CustomerSession customerSession) {
        map.put(customerSession.getId(), customerSession);
        return customerSession;
    }
    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {

        return map.values().stream().filter(customerSession -> customerSession.getUser().getId() == userId && customerSession.isActive()).findFirst();
    }

}
