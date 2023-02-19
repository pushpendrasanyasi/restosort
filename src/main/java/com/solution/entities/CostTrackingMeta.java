package com.solution.entities;

import com.solution.common.constants.CostBracket;
import lombok.Data;

import java.util.Set;

@Data
public class CostTrackingMeta {
    private CostBracket primary;
    private Set<CostTracking> secondary;
}
