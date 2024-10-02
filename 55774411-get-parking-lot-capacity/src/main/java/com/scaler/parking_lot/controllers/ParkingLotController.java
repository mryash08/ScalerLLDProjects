package com.scaler.parking_lot.controllers;

import com.scaler.parking_lot.dtos.GetParkingLotCapacityRequestDto;
import com.scaler.parking_lot.dtos.GetParkingLotCapacityResponseDto;
import com.scaler.parking_lot.dtos.Response;
import com.scaler.parking_lot.dtos.ResponseStatus;
import com.scaler.parking_lot.exceptions.GetParkingLotRequestValidationException;
import com.scaler.parking_lot.models.VehicleType;
import com.scaler.parking_lot.services.ParkingLotService;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public GetParkingLotCapacityResponseDto getParkingLotCapacity(GetParkingLotCapacityRequestDto getParkingLotCapacityRequestDto) {
        GetParkingLotCapacityResponseDto dto = new GetParkingLotCapacityResponseDto();
        try {
            if(getParkingLotCapacityRequestDto.getParkingLotId() <= 0) {
                throw new GetParkingLotRequestValidationException("Parking Lot Id is Invalid");
            }
            List<String> vehicleTypeList = getParkingLotCapacityRequestDto.getVehicleTypes();
            List<VehicleType> vehicalTypes = new ArrayList<>();
            if(vehicleTypeList != null && !vehicleTypeList.isEmpty()) {
                for(String type : vehicleTypeList) {
                    vehicalTypes.add(VehicleType.valueOf(type));
                }
            }
            dto.setResponse(new Response(ResponseStatus.SUCCESS, "Parking Lot Capacity retrieved successfully"));
            dto.setCapacityMap(parkingLotService.getParkingLotCapacity(getParkingLotCapacityRequestDto.getParkingLotId(), getParkingLotCapacityRequestDto.getParkingFloorIds(), vehicalTypes));
    
        } catch (Exception e) {
            dto.setResponse(new Response(ResponseStatus.FAILURE, e.getMessage()));
        }
        return dto;
    }

}
