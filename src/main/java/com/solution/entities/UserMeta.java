package com.solution.entities;

import com.solution.common.constants.CostBracket;
import com.solution.common.constants.Cuisine;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserMeta {
    private User user;
    private Cuisine primaryCuisine;
    private Set<Cuisine> secondaryCuisines;
    private CostBracket primaryCostBracket;
    private Set<CostBracket> secondaryCostBrackets;
}
