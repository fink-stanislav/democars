package com.exadel.democars.view.model.expression;

import com.exadel.democars.util.JpqlExpressionBuilder;
import org.richfaces.component.SortOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Provides sorting of rows. Sorting based on Jpql ORDER BY clause.
 */
public class SortExpression extends DefaultExpression {
    /**
     * Contains values to be placed in ORDER BY statement
     */
    private Map<String, SortOrder> sortParams;
    private String expressionString = "";

    public SortExpression(PaginationParams paginationParams, JpqlParams jpqlParams) {
        super(paginationParams, jpqlParams);
        sortParams = new HashMap<String, SortOrder>();
    }

    public SortExpression(JpqlParams jpqlParams) {
        super(jpqlParams);
        sortParams = new HashMap<String, SortOrder>();
    }

    /**
     * Evaluates sort expression.
     * First, clears up the parameters from <code>SortOrder.unsorted</code> values;
     * Second, builds ORDER BY statement, inserting appropriate param names and Jpql ASC and DESC words.
     * @return String representation of sorting Jpql-expression.
     */
    public String evaluateExpression() {

        Map<String, SortOrder> sortParams = new HashMap<String, SortOrder>();
        sortParams.putAll(this.sortParams);
        Set<Map.Entry<String, SortOrder>> sortParamEntrySet = this.sortParams.entrySet();
        for (Map.Entry<String, SortOrder> entry : sortParamEntrySet) {
            if (entry.getValue() == SortOrder.unsorted) {
                sortParams.remove(entry.getKey());
            }
        }

        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);

        Set<Map.Entry<String, SortOrder>> entrySet = sortParams.entrySet();
        if (entrySet.isEmpty()) {
            return builder.getExpression();
        }

        builder.buildOrderByExpression();
        int counter = 0;
        for (Map.Entry<String, SortOrder> entry : entrySet) {
            counter++;
            if (entry.getValue() == SortOrder.ascending) {
                builder.addOrderParams(entry.getKey(), "asc");
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addComma();
                }
            } else if (entry.getValue() == SortOrder.descending) {
                builder.addOrderParams(entry.getKey(), "desc");
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addComma();
                }
            }
        }
        expressionString = builder.getExpression();
        return expressionString;
    }

    /**
     * Sorting parameters setter
     * @param sortParams from backing bean that uses this DataSource
     */
    public void setSortParams(Map<String, SortOrder> sortParams) {
        this.sortParams = sortParams;
    }

    public String getExpressionString() {
        return expressionString;
    }
}
