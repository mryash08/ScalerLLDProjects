package com.scaler.services;

import com.scaler.exceptions.CustomerSessionNotFound;
import com.scaler.exceptions.InvalidMenuItem;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.Bill;
import com.scaler.models.Order;

import java.util.Map;

public interface OrderService {

    public Order placeOrder(long userId, Map<Long,Integer> orderedItems) throws UserNotFoundException, InvalidMenuItem;

}
