package com.scaler.controllers;

import com.scaler.dtos.CalculateRevenueRequestDto;
import com.scaler.dtos.CalculateRevenueResponseDto;
import com.scaler.dtos.ResponseStatus;
import com.scaler.models.AggregatedRevenue;
import com.scaler.services.RevenueService;

public class RevenueController {

    private RevenueService revenueService;

    public RevenueController(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    public CalculateRevenueResponseDto calculateRevenue(CalculateRevenueRequestDto requestDto){
        CalculateRevenueResponseDto dto = new CalculateRevenueResponseDto();
        try {
           AggregatedRevenue aggregatedRevenue = revenueService.calculateRevenue(requestDto.getUserId(),requestDto.getRevenueQueryType());
           dto.setAggregatedRevenue(aggregatedRevenue);
           dto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            dto.setResponseStatus(ResponseStatus.FAILURE);
        }

        return  dto;
    }
}
