package com.solution.services.rules.generic.impl;

import com.solution.entities.Restaurant;
import com.solution.entities.UserMeta;
import com.solution.services.rules.generic.GenericRule;
import com.solution.utils.TopKUtils;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewRestaurantsRecommendationRule implements GenericRule {
    @Override
    public int getOrder() {
        return 5;
    }

    @Override
    public List<Integer> getMatchingRestaurants(UserMeta userMeta, List<Restaurant> restaurants) {

        List<Restaurant> matchedResto = TopKUtils.findTopK(restaurants, 4, Comparator.comparing(Restaurant::getOnboardedTime));
        return matchedResto.stream().map(Restaurant::getRestaurantId).collect(Collectors.toList());
    }
}
