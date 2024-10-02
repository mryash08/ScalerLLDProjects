package com.scaler.parking_lot.strategies.pricing;

import com.scaler.parking_lot.models.VehicleType;

import java.util.Date;

public class WeekdayPricingStrategy implements PricingStrategy{

    @Override
    public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
        int hour = (int) Math.ceil((exitTime.getTime() - entryTime.getTime())/ (1000.0 * 60 * 60));
        return (double) (hour * 10);
    }
}
