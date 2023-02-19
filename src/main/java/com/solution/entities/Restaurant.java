package com.solution.entities;

import com.solution.common.constants.CostBracket;
import com.solution.common.constants.Cuisine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private int restaurantId;
    private Cuisine cuisine;
    private CostBracket costBracket;
    private float rating;
    private boolean isRecommended;
    private LocalDateTime onboardedTime;

}
