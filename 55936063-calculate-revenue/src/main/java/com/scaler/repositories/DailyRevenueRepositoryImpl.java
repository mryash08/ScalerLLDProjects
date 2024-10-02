package com.scaler.repositories;

import com.scaler.models.DailyRevenue;

import java.util.*;

public class DailyRevenueRepositoryImpl implements DailyRevenueRepository{
    Map<Date,DailyRevenue> map = new HashMap<>();
    @Override
    public DailyRevenue save(DailyRevenue dailyRevenue) {
        map.put(dailyRevenue.getDate(),dailyRevenue);
        return null;
    }

    @Override
    public List<DailyRevenue> getDailyRevenueBetweenDates(Date startDate, Date endDate) {
        List<DailyRevenue> dataList = new ArrayList<>();

        while (startDate.toInstant().isBefore(endDate.toInstant())) {
            if(map.containsKey(startDate)){
                dataList.add(map.get(startDate));
            }
            startDate.toInstant().plusSeconds(86400);
        }
        return dataList;
    }
}
