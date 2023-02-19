package com.solution.entities;

import com.solution.common.constants.CostBracket;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CostTracking {
    private CostBracket bracket;
    private int noOfOrders;

}
