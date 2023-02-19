package com.solution.services.rules.booleanrule.impl;

import com.solution.entities.Restaurant;
import com.solution.entities.UserMeta;
import com.solution.services.rules.booleanrule.BooleanRecommendationRule;
import org.springframework.stereotype.Component;

@Component
public class AllRemainingRestaurantsRecommendationRule implements BooleanRecommendationRule {
    @Override
    public int getOrder() {
        return 9;
    }

    @Override
    public boolean isMatch(UserMeta userMeta, Restaurant restaurant) {
        return true;
    }
}
