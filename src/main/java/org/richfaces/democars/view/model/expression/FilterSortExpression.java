package org.richfaces.democars.view.model.expression;

import org.richfaces.democars.util.JpqlExpressionBuilder;

/**
 * Combines {@link SortExpression} and {@link FilterExpression} functionality.
 *
 * @author S. Fink
 */
public class FilterSortExpression extends DefaultExpression {
    private SortExpression sortExpression;
    private FilterExpression filterExpression;

    public FilterSortExpression(SortExpression sortExpression, FilterExpression filterExpression) {
        super(sortExpression.jpqlParams);
        this.sortExpression = sortExpression;
        this.filterExpression = filterExpression;
    }

    /**
     * Builds expression for retrieving filtered and sorted data.
     * The result depends on parameters of {@code SortExpression} and {@code FilterExpression} and
     * may vary from empty string to statement containing order by and like expressions.
     *
     * @return String representation of JPQL statement
     */
    public String evaluateExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);
        builder.append(filterExpression.evaluateExpression());
        builder.append(sortExpression.evaluateExpression());
        return builder.getExpression();
    }
}
