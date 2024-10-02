package com.scaler.repositories;

import com.scaler.models.CustomerSession;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class CustomerSessionRepositoryImpl implements CustomerSessionRepository{
    private  Map<Long, CustomerSession> customerSessionMap;
    private  long id = 0;

    public CustomerSessionRepositoryImpl() {
        this.customerSessionMap = new HashMap<>();
    }
    @Override
    public CustomerSession save(CustomerSession customerSession) {
        if (customerSession.getId() == 0){
            customerSession.setId(++id);
        }
        customerSessionMap.put(customerSession.getId(), customerSession);
        return customerSession;
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        return customerSessionMap.values().stream().filter(customerSession -> customerSession.getUser().getId() == userId && customerSession.isActive()).findFirst();
    }
}
