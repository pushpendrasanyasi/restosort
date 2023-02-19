package com.solution.services;

import com.solution.entities.Restaurant;
import com.solution.entities.User;

import java.util.List;

public interface RecommendationService {
    List<Integer> getRestaurantRecommendations(User user, List<Restaurant> availableRestaurants);
}
