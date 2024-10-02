package com.scaler.repositories;

import com.scaler.models.CustomerSession;
import com.scaler.models.Order;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository{
    Map<Long, Order> map = new HashMap<>();
    @Override
    public Order save(Order order) {
        if (order.getCustomerSession().getId() == 0){
            order.getCustomerSession().setId(map.size()+1);
        }
        map.put(order.getId(), order);
        return order;
    }

    @Override
    public List<Order> findOrdersByCustomerSession(long customerSessionId) {


        return map.values().stream().filter(order -> order.getCustomerSession().getId() == customerSessionId).toList();

    }
}
