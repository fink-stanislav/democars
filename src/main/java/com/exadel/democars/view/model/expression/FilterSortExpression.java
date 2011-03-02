package com.exadel.democars.view.model.expression;

import com.exadel.democars.util.JpqlExpressionBuilder;

public class FilterSortExpression extends DefaultExpression {
    private SortExpression sortExpression;
    private FilterExpression filterExpression;

    public FilterSortExpression(SortExpression sortExpression, FilterExpression filterExpression) {
        super(sortExpression.getPaginationParams(), sortExpression.getJpqlParams());
        this.sortExpression = sortExpression;
        this.filterExpression = filterExpression;
    }

    public String evaluateExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);
        builder.append(filterExpression.evaluateExpression());
        builder.append(sortExpression.evaluateExpression());
        return builder.getExpression();
    }
}
