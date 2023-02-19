package com.solution.services.rules.generic.impl;

import com.solution.common.constants.CostBracket;
import com.solution.common.constants.Cuisine;
import com.solution.entities.Restaurant;
import com.solution.entities.UserMeta;
import com.solution.services.rules.generic.GenericRule;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FeaturedRestaurantRecommendationRule implements GenericRule {

    /**
     * Featured restaurants of primary cuisine and primary cost bracket. If none, then all
     * featured restaurants of primary cuisine, secondary cost and secondary cuisine,
     * primary cost
     *
     * @param userMeta    @{@link UserMeta}
     * @param restaurants @{@link List<Restaurant>}
     * @return list of restaurant ids matching to this rule.
     */
    @Override
    public List<Integer> getMatchingRestaurants(UserMeta userMeta, List<Restaurant> restaurants) {
        List<Integer> out = getPrimaryMatchedResults(userMeta, restaurants);
        if (CollectionUtils.isEmpty(out)) {
            out = getSecondaryMatchedResults(userMeta, restaurants);
        }
        return out;
    }

    private List<Integer> getPrimaryMatchedResults(UserMeta userMeta, List<Restaurant> restaurants) {
        return restaurants.stream()
                .filter(Restaurant::isRecommended)
                .filter(restaurant -> isMatchPrimary(userMeta, restaurant))
                .map(Restaurant::getRestaurantId)
                .collect(Collectors.toList());
    }

    private List<Integer> getSecondaryMatchedResults(UserMeta userMeta, List<Restaurant> restaurants) {
        return restaurants.stream()
                .filter(Restaurant::isRecommended)
                .filter(restaurant -> isMatchSecondary(userMeta, restaurant))
                .map(Restaurant::getRestaurantId)
                .collect(Collectors.toList());
    }

    private boolean isMatchPrimary(UserMeta userMeta, Restaurant restaurant) {
        Cuisine primaryCuisine = userMeta.getPrimaryCuisine();
        CostBracket primaryCostBracket = userMeta.getPrimaryCostBracket();
        if (restaurant.getCuisine() != primaryCuisine) {
            return false;
        }
        return restaurant.getCostBracket() == primaryCostBracket;
    }

    private boolean isMatchSecondary(UserMeta userMeta, Restaurant restaurant) {
        Cuisine primaryCuisine = userMeta.getPrimaryCuisine();
        CostBracket primaryCostBracket = userMeta.getPrimaryCostBracket();
        Set<Cuisine> secondaryCuisines = userMeta.getSecondaryCuisines();
        Set<CostBracket> secondaryCostBrackets = userMeta.getSecondaryCostBrackets();

        if (primaryCuisine == restaurant.getCuisine() && secondaryCostBrackets.contains(restaurant.getCostBracket())) {
            return true;
        }
        return secondaryCuisines.contains(restaurant.getCuisine()) && primaryCostBracket == restaurant.getCostBracket();
    }

    @Override
    public int getOrder() {
        return 1;
    }


}
