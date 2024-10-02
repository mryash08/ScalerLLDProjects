package com.scaler.parking_lot.strategies.pricing;

import com.scaler.parking_lot.models.Slab;
import com.scaler.parking_lot.models.VehicleType;
import com.scaler.parking_lot.respositories.SlabRepository;

import java.util.Date;
import java.util.List;

public class WeekendPricingStrategy implements PricingStrategy{

    private SlabRepository slabRepository;

    public WeekendPricingStrategy(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    @Override
    public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {
        List<Slab> slab = slabRepository.getSortedSlabsByVehicleType(vehicleType);
        int hour = (int) Math.ceil((exitTime.getTime() - entryTime.getTime())/ (1000.0 * 60 * 60));
        double amount = 0.0;
        for(Slab s : slab) {
            if(hour > s.getEndHour() && s.getEndHour() != -1) { // this means we have consumed entire slab
                amount += (s.getEndHour() - s.getStartHour()) * s.getPrice();
            }
            else if(s.getEndHour() == -1 || hour <= s.getEndHour()){
                amount += (hour - s.getStartHour()) * s.getPrice();
                break;
            }
        }
        return amount;
    }
}
