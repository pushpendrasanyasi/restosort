package com.solution.services;

import com.solution.common.constants.ApplicationConstants;
import com.solution.entities.Restaurant;
import com.solution.entities.UserMeta;
import com.solution.services.rules.booleanrule.BooleanRecommendationRule;
import com.solution.services.rules.generic.GenericRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class RecommendationRuleProcessor {

    private final List<GenericRule> rules;

    @Autowired
    public RecommendationRuleProcessor(List<GenericRule> genericRules, List<BooleanRecommendationRule> booleanRules) {
        this.rules = genericRules;
        this.rules.addAll(booleanRules);
    }

    @PostConstruct
    public void init() {
        rules.sort(AnnotationAwareOrderComparator.INSTANCE);
    }

    public List<Integer> sortRestaurants(UserMeta userMeta, List<Restaurant> restaurants) {
        List<Restaurant> remainingRestaurants = restaurants;
        List<Integer> out = new ArrayList<>();
        for (GenericRule rule : rules) {
            List<Integer> matchedRestIds = rule.getMatchingRestaurants(userMeta, remainingRestaurants);
            out.addAll(matchedRestIds);
            remainingRestaurants = remainingRestaurants(matchedRestIds, remainingRestaurants);
            if (CollectionUtils.isEmpty(remainingRestaurants) || out.size() >= ApplicationConstants.MAX_RECOMMENDATION_COUNT) {
                break;
            }
        }
        return truncateExtraRecommendations(out);
    }

    private List<Restaurant> remainingRestaurants(List<Integer> recommendations, List<Restaurant> restaurants) {
        Set<Integer> recommendationSet = new HashSet<>(recommendations);
        return restaurants.stream()
                .filter(restaurant -> !recommendationSet.contains(restaurant.getRestaurantId()))
                .collect(Collectors.toList());
    }

    private List<Integer> truncateExtraRecommendations(List<Integer> out) {
        if (CollectionUtils.isEmpty(out)) {
            return Collections.emptyList();
        }
        if (out.size() >= ApplicationConstants.MAX_RECOMMENDATION_COUNT) {
            return out.subList(0, ApplicationConstants.MAX_RECOMMENDATION_COUNT);
        }
        return out;
    }
}
