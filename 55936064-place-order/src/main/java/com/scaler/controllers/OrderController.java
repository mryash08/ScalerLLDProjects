package com.scaler.controllers;

import com.scaler.dtos.*;
import com.scaler.models.Bill;
import com.scaler.models.Order;
import com.scaler.services.OrderService;

public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    public PlaceOrderResponseDto placeOrder(PlaceOrderRequestDto requestDto){
        PlaceOrderResponseDto responseDto = new PlaceOrderResponseDto();
        try {
            Order order = orderService.placeOrder(requestDto.getUserId(), requestDto.getOrderedItems());
            responseDto.setOrder(order);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return responseDto;
        } catch (Exception e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            return responseDto;
        }
    }

}
