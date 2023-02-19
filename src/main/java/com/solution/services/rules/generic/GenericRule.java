package com.solution.services.rules.generic;

import com.solution.entities.Restaurant;
import com.solution.entities.UserMeta;
import org.springframework.core.Ordered;

import java.util.List;

public interface GenericRule extends Ordered {
    List<Integer> getMatchingRestaurants(UserMeta userMeta, List<Restaurant> restaurants);
}
