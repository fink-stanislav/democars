package org.richfaces.democars.model.expression;

import org.richfaces.democars.model.util.JpqlExpressionBuilder;

/**
 * Combines {@link SortExpression} and {@link FilterExpression} functionality.
 *
 * @author S. Fink
 */
public class FilterSortExpression extends DefaultExpression {
    private SortExpression sortExpression;
    private FilterExpression filterExpression;

    public FilterSortExpression(SortExpression sortExpression, FilterExpression filterExpression) {
        super(sortExpression.entityParams);
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
    public String evaluate() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(entityParams);
        builder.append(filterExpression.evaluate());
        builder.append(sortExpression.evaluate());
        return builder.getExpression();
    }
}
