package com.scaler.services;

import com.scaler.exceptions.InvalidMenuItem;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.*;
import com.scaler.repositories.CustomerSessionRepository;
import com.scaler.repositories.MenuItemRepository;
import com.scaler.repositories.OrderRepository;
import com.scaler.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{
    CustomerSessionRepository customerSessionRepository;
    MenuItemRepository menuItemRepository;
    OrderRepository orderRepository;
    UserRepository userRepository;

    public OrderServiceImpl(CustomerSessionRepository customerSessionRepository, MenuItemRepository menuItemRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.customerSessionRepository = customerSessionRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order placeOrder(long userId, Map<Long, Integer> orderedItems) throws UserNotFoundException, InvalidMenuItem {
        Optional<CustomerSession> customerSession = customerSessionRepository.findActiveCustomerSessionByUserId(userId);
        CustomerSession customerSession1;
        if (customerSession.isEmpty()){
             customerSession1= new CustomerSession();
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()){
                throw new UserNotFoundException("User Not Found");
            }
            customerSession1.setCustomerSessionStatus(CustomerSessionStatus.ACTIVE);
            customerSession1.setUser(user.get());
            customerSession1 = customerSessionRepository.save(customerSession1);
        }else {
            customerSession1 = customerSession.get();
        }

        Order order = new Order();
        Map<MenuItem, Integer> menuItemQuantityMap = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : orderedItems.entrySet()) {
            Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(entry.getKey());
            if(optionalMenuItem.isPresent()){
                menuItemQuantityMap.put(optionalMenuItem.get(), entry.getValue());
            } else {
                throw new InvalidMenuItem("Menu item not found");
            }
        }
        order.setOrderedItems(menuItemQuantityMap);
        order.setCustomerSession(customerSession1);
        order = orderRepository.save(order);
        return order;
    }
}
