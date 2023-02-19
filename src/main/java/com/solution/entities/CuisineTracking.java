package com.solution.entities;

import com.solution.common.constants.Cuisine;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CuisineTracking {
    private Cuisine type;
    private int noOfOrders;
}
