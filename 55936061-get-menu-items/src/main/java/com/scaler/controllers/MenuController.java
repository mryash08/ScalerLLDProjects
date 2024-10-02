package com.scaler.controllers;

import com.scaler.dtos.*;
import com.scaler.models.MenuItem;
import com.scaler.services.MenuService;

import java.awt.*;
import java.util.List;

public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    public GetMenuItemsResponseDto getMenuItems(GetMenuItemsRequestDto requestDto){
        GetMenuItemsResponseDto getMenuItemsResponseDto = new GetMenuItemsResponseDto();

        try{
            List<MenuItem> list = menuService.getMenuItems(requestDto.getDietaryRequirement());
            getMenuItemsResponseDto.setMenuItems(list);
            getMenuItemsResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            return getMenuItemsResponseDto;
        }
        catch (Exception e){
            getMenuItemsResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            return getMenuItemsResponseDto;
        }
        
    }
}
