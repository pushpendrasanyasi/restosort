package com.solution.entities;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private List<CuisineTracking> cuisineTrackingList;
    private List<CostTracking> costTrackingList;

}
