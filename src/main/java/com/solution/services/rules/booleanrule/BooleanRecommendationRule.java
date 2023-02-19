package com.solution.services.rules.booleanrule;

import com.solution.entities.Restaurant;
import com.solution.entities.UserMeta;
import com.solution.services.rules.generic.GenericRule;

import java.util.List;
import java.util.stream.Collectors;

public interface BooleanRecommendationRule extends GenericRule {

    /**
     * @param userMeta    @{@link UserMeta}
     * @param restaurants @{@link List<Restaurant>}
     * @return lLst of matched restaurant ids
     */
    default List<Integer> getMatchingRestaurants(UserMeta userMeta, List<Restaurant> restaurants) {
        return restaurants.stream()
                .filter(restaurant -> isMatch(userMeta, restaurant))
                .map(Restaurant::getRestaurantId)
                .collect(Collectors.toList());
    }

    boolean isMatch(UserMeta userMeta, Restaurant restaurant);
}
