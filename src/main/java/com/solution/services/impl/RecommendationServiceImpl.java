package com.solution.services.impl;

import com.solution.entities.Restaurant;
import com.solution.entities.User;
import com.solution.entities.UserMeta;
import com.solution.services.RecommendationRuleProcessor;
import com.solution.services.RecommendationService;
import com.solution.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    private final UserService userService;
    private final RecommendationRuleProcessor recommendationRuleProcessor;

    @Autowired
    public RecommendationServiceImpl(UserService userService, RecommendationRuleProcessor recommendationRuleProcessor) {
        this.userService = userService;
        this.recommendationRuleProcessor = recommendationRuleProcessor;
    }

    public List<Integer> getRestaurantRecommendations(User user, List<Restaurant> availableRestaurants) {
        // Takes user and restaurant while returning back list of restaurant Ids in the right sorting order
        UserMeta userMeta = userService.getUserMeta(user);
        return recommendationRuleProcessor.sortRestaurants(userMeta, availableRestaurants);
    }
}
