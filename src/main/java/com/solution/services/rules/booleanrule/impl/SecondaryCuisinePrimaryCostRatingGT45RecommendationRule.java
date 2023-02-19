package com.solution.services.rules.booleanrule.impl;

import com.solution.common.constants.CostBracket;
import com.solution.common.constants.Cuisine;
import com.solution.entities.Restaurant;
import com.solution.entities.UserMeta;
import com.solution.services.rules.booleanrule.BooleanRecommendationRule;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SecondaryCuisinePrimaryCostRatingGT45RecommendationRule implements BooleanRecommendationRule {
    /**
     * All restaurants of secondary cuisine, primary cost bracket with rating >= 4.5
     */

    @Override
    public int getOrder() {
        return 4;
    }

    @Override
    public boolean isMatch(UserMeta userMeta, Restaurant restaurant) {
        CostBracket primaryCost = userMeta.getPrimaryCostBracket();
        Set<Cuisine> secondaryCuisine = userMeta.getSecondaryCuisines();

        return primaryCost == restaurant.getCostBracket()
                && secondaryCuisine.contains(restaurant.getCuisine())
                && restaurant.getRating() >= 4.5;
    }
}
