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

    public GenerateBillResponseDto generateBill(GenerateBillRequestDto requestDto){
        GenerateBillResponseDto dto = new GenerateBillResponseDto();
        try {
            Bill bill = orderService.generateBill(requestDto.getUserId());
            dto.setBill(bill);
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            dto.setResponseStatus(ResponseStatus.FAILURE);

        }
        return dto;
    }


}
