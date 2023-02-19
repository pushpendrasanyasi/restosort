package com.solution.services.rules.booleanrule.impl;

import com.solution.common.constants.CostBracket;
import com.solution.common.constants.Cuisine;
import com.solution.entities.Restaurant;
import com.solution.entities.UserMeta;
import com.solution.services.rules.booleanrule.BooleanRecommendationRule;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PrimaryCuisineSecondaryCostRatingGT45RecommendationRule implements BooleanRecommendationRule {
    /**
     * All restaurants of Primary cuisine, secondary cost bracket with rating >= 4.5
     */

    @Override
    public int getOrder() {
        return 3;
    }

    @Override
    public boolean isMatch(UserMeta userMeta, Restaurant restaurant) {
        Cuisine primaryCuisine = userMeta.getPrimaryCuisine();
        Set<CostBracket> secondaryCostBrackets = userMeta.getSecondaryCostBrackets();

        return primaryCuisine == restaurant.getCuisine()
                && secondaryCostBrackets.contains(restaurant.getCostBracket())
                && restaurant.getRating() >= 4.5;

    }
}
