package com.solution;

import com.solution.common.constants.CostBracket;
import com.solution.common.constants.Cuisine;
import com.solution.entities.CostTracking;
import com.solution.entities.CuisineTracking;
import com.solution.entities.Restaurant;
import com.solution.entities.User;
import com.solution.services.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Application.class, args);

        User user = getUser();
        List<Restaurant> restaurants = restaurants();

        RecommendationService service = context.getBean(RecommendationService.class);
        List<Integer> recs = service.getRestaurantRecommendations(user, restaurants);
        log.info(recs.toString());
    }

    private static User getUser() {
        CuisineTracking ct1 = new CuisineTracking(Cuisine.NORTH_INDIAN, 6);
        CuisineTracking ct2 = new CuisineTracking(Cuisine.CHINESE, 1);
        CuisineTracking ct3 = new CuisineTracking(Cuisine.SOUTH_INDIAN, 3);
        List<CuisineTracking> ctrackingList = new ArrayList<>();
        ctrackingList.add(ct1);
        ctrackingList.add(ct2);
        ctrackingList.add(ct3);

        CostTracking cost1 = new CostTracking(CostBracket.TWO, 8);
        CostTracking cost2 = new CostTracking(CostBracket.FIVE, 6);
        CostTracking cost3 = new CostTracking(CostBracket.ONE, 3);
        List<CostTracking> costTrackingList = new ArrayList<>();
        costTrackingList.add(cost1);
        costTrackingList.add(cost2);
        costTrackingList.add(cost3);

        User user = new User();
        user.setCuisineTrackingList(ctrackingList);
        user.setCostTrackingList(costTrackingList);
        return user;
    }

    private static List<Restaurant> restaurants() {
        Restaurant r2 = Restaurant.builder()
                .restaurantId(2)
                .cuisine(Cuisine.NORTH_INDIAN)
                .costBracket(CostBracket.FIVE)
                .rating(4.2f)
                .isRecommended(true)
                .onboardedTime(LocalDateTime.now())
                .build();
        Restaurant r1 = Restaurant.builder()
                .restaurantId(1)
                .cuisine(Cuisine.SOUTH_INDIAN)
                .costBracket(CostBracket.TWO)
                .rating(4.0f)
                .isRecommended(false)
                .onboardedTime(LocalDateTime.now())
                .build();
        Restaurant r3 = Restaurant.builder()
                .restaurantId(3)
                .cuisine(Cuisine.CHINESE)
                .costBracket(CostBracket.TWO)
                .rating(4.7f)
                .isRecommended(true)
                .onboardedTime(LocalDateTime.now())
                .build();
        List<Restaurant> restaurants = new ArrayList<>(3);
        restaurants.add(r1);
        restaurants.add(r2);
        restaurants.add(r3);
        return restaurants;
    }
}