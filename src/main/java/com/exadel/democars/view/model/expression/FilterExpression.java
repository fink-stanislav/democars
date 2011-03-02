package com.exadel.democars.view.model.expression;

import com.exadel.democars.util.JpqlExpressionBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FilterExpression extends DefaultExpression {
    private Map<String, Object> filterParams;

    public FilterExpression(JpqlParams jpqlParams) {
        super(jpqlParams);
        this.filterParams = new HashMap<String, Object>();
    }

    public FilterExpression(PaginationParams paginationParams, JpqlParams jpqlParams) {
        super(paginationParams, jpqlParams);
        this.filterParams = new HashMap<String, Object>();
    }

    public String evaluateExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);

        Set<Map.Entry<String, Object>> entrySet = filterParams.entrySet();
        if (entrySet.isEmpty()) {
            return builder.getExpression();
        }

        builder.addWhere();
        int counter = 0;
        for (Map.Entry<String, Object> entry : entrySet) {
            counter++;
            if (entry.getValue() instanceof String) {
                builder.buildLikeExpression(entry.getKey(), entry.getValue());
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addAnd();
                }
            } else if (entry.getValue() instanceof Number) {
                builder.buildComparsionExpression(entry.getKey(), entry.getValue(), "<=");
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addAnd();
                }
            }
        }
        return builder.getExpression();
    }

    public void setFilterParams(Map<String, Object> filterParams) {
        this.filterParams = filterParams;
    }
}
