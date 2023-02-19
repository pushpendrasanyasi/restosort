package com.solution.services.impl;

import com.solution.entities.CostTracking;
import com.solution.entities.CuisineTracking;
import com.solution.entities.User;
import com.solution.entities.UserMeta;
import com.solution.services.UserService;
import com.solution.utils.TopKUtils;
import com.solution.utils.UserMetaTransformer;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserMeta getUserMeta(User user) {
        List<CuisineTracking> cuisineTrackings = user.getCuisineTrackingList();
        List<CostTracking> costTrackings = user.getCostTrackingList();

        List<CuisineTracking> top3CuisineTracking = getTop3Cuisines(cuisineTrackings);
        List<CostTracking> top3CostTracking = getTop3CostTrackings(costTrackings);

        return UserMetaTransformer.getUserMeta(user, top3CuisineTracking, top3CostTracking);
    }

    private List<CuisineTracking> getTop3Cuisines(List<CuisineTracking> cuisineTrackings) {
        return TopKUtils.findTopK(cuisineTrackings, 3, Comparator.comparingInt(CuisineTracking::getNoOfOrders));
    }

    private List<CostTracking> getTop3CostTrackings(List<CostTracking> costTrackings) {
        return TopKUtils.findTopK(costTrackings, 3, Comparator.comparingInt(CostTracking::getNoOfOrders));
    }


}
