package com.solution.services.rules.booleanrule.impl;

import com.solution.common.constants.CostBracket;
import com.solution.common.constants.Cuisine;
import com.solution.entities.Restaurant;
import com.solution.entities.UserMeta;
import com.solution.services.rules.booleanrule.BooleanRecommendationRule;
import org.springframework.stereotype.Component;

@Component
public class PrimaryCostPrimaryCuisineRatingGT4RecommendationRule implements BooleanRecommendationRule {
    /**
     * All restaurants of Primary cuisine, primary cost bracket with rating >= 4
     */

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public boolean isMatch(UserMeta userMeta, Restaurant restaurant) {
        Cuisine primaryCuisine = userMeta.getPrimaryCuisine();
        CostBracket primaryCostBracket = userMeta.getPrimaryCostBracket();

        return primaryCuisine == restaurant.getCuisine()
                && primaryCostBracket == restaurant.getCostBracket()
                && restaurant.getRating() >= 4;
    }
}
