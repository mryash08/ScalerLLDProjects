package com.scaler.repositories;

import com.scaler.models.Order;
import com.scaler.models.User;

import java.util.HashMap;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository{
    private Map<Long, Order> orderMap;
    private  long id = 0;

    public OrderRepositoryImpl() {
        this.orderMap = new HashMap<>();
    }
    @Override
    public Order save(Order order) {
        if (order.getId() == 0){
            order.setId(++id);
        }

        orderMap.put(order.getId(), order);
        return order;
    }
}
