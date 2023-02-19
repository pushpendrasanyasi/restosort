package com.solution.utils;

import com.solution.common.constants.CostBracket;
import com.solution.common.constants.Cuisine;
import com.solution.entities.CostTracking;
import com.solution.entities.CuisineTracking;
import com.solution.entities.User;
import com.solution.entities.UserMeta;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserMetaTransformer {

    private UserMetaTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static UserMeta getUserMeta(User user, List<CuisineTracking> cuisineTrackings, List<CostTracking> costTrackings) {
        if (CollectionUtils.isEmpty(cuisineTrackings) || CollectionUtils.isEmpty(costTrackings)
                || cuisineTrackings.get(0) == null || costTrackings.get(0) == null) {
            return UserMeta.builder().user(user).build();
        }
        Cuisine primaryCuisine = cuisineTrackings.get(0).getType();
        Set<Cuisine> secondaryCuisine = getSecondaryCuisine(cuisineTrackings);

        CostBracket primaryCostBracket = costTrackings.get(0).getBracket();
        Set<CostBracket> secondaryCostBracket = getSecondaryCostBracket(costTrackings);

        return UserMeta.builder()
                .user(user)
                .primaryCuisine(primaryCuisine)
                .secondaryCuisines(secondaryCuisine)
                .primaryCostBracket(primaryCostBracket)
                .secondaryCostBrackets(secondaryCostBracket)
                .build();
    }

    private static Set<Cuisine> getSecondaryCuisine(List<CuisineTracking> cuisineTrackings) {
        if (CollectionUtils.isEmpty(cuisineTrackings)) {
            return Collections.emptySet();
        }
        Set<Cuisine> out = new HashSet<>();
        int count = 1;
        for (CuisineTracking c : cuisineTrackings) {
            if (count++ == 1) {
                continue;
            }
            out.add(c.getType());
        }
        return out;
    }

    private static Set<CostBracket> getSecondaryCostBracket(List<CostTracking> costTrackings) {
        if (CollectionUtils.isEmpty(costTrackings)) {
            return Collections.emptySet();
        }
        Set<CostBracket> out = new HashSet<>();
        int count = 1;
        for (CostTracking c : costTrackings) {
            if (count++ == 1) {
                continue;
            }
            out.add(c.getBracket());
        }
        return out;
    }
}
