package com.scaler.services;

import com.scaler.exceptions.UnAuthorizedAccess;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.*;
import com.scaler.repositories.DailyRevenueRepository;
import com.scaler.repositories.DailyRevenueRepositoryImpl;
import com.scaler.repositories.UserRepository;
import com.scaler.repositories.UserRepositoryimpl;
import com.scaler.utils.DateUtils;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class RevenueServiceImpl implements RevenueService{
    DailyRevenueRepository dailyRevenueRepository;
    UserRepository userRepository;

    public RevenueServiceImpl(DailyRevenueRepository dailyRevenueRepository, UserRepository userRepository) {
        this.dailyRevenueRepository = dailyRevenueRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AggregatedRevenue calculateRevenue(long userId, String queryType) throws UnAuthorizedAccess, UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) throw new UserNotFoundException("User Not Found");
        if (!user.get().getUserType().equals(UserType.BILLING)) throw new UnAuthorizedAccess("Unauthorized User");


        AggregatedRevenue aggregatedRevenue = new AggregatedRevenue();
        Pair<Date,Date> pair= DateUtils.getStartAndEndDateByQueryType(RevenueQueryType.valueOf(queryType));
        List<DailyRevenue> dailyRevenueBetweenDates = dailyRevenueRepository.getDailyRevenueBetweenDates(pair.getLeft(),pair.getRight());
        aggregatedRevenue.setFromDate(pair.getLeft());
        aggregatedRevenue.setToDate(pair.getRight());
        double totalGst = 0;
        double totalFoodSales = 0;
        double totalServiceText = 0;
        for (DailyRevenue dailyRevenue : dailyRevenueBetweenDates){
            totalGst += dailyRevenue.getTotalGst();
            totalFoodSales += dailyRevenue.getRevenueFromFoodSales();
            totalServiceText += dailyRevenue.getTotalServiceCharge();
        }

        aggregatedRevenue.setTotalRevenue(totalGst + totalServiceText + totalFoodSales);
        aggregatedRevenue.setRevenueFromFoodSales(totalFoodSales);
        aggregatedRevenue.setTotalGst(totalGst);
        aggregatedRevenue.setTotalServiceCharge(totalServiceText);


        return aggregatedRevenue;
    }
}
