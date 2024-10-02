package com.example.ecom.controllers;

import com.example.ecom.dtos.*;
import com.example.ecom.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InventoryController {


    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public CreateOrUpdateResponseDto createOrUpdateInventory(CreateOrUpdateRequestDto requestDto){
        CreateOrUpdateResponseDto dto = new CreateOrUpdateResponseDto();
        try {
            dto.setInventory(inventoryService.createOrUpdateInventory(requestDto.getUserId(), requestDto.getProductId(), requestDto.getQuantity()));
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            dto.setResponseStatus(ResponseStatus.FAILURE);

        }
        return dto;
    }

    public DeleteInventoryResponseDto deleteInventory(DeleteInventoryRequestDto requestDto){
        DeleteInventoryResponseDto dto = new DeleteInventoryResponseDto();
        try {
            inventoryService.deleteInventory(requestDto.getUserId(), requestDto.getProductId());
            dto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            dto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return dto;
    }
}
