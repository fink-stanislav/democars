package com.exadel.democars.view.model.expression;

import com.exadel.democars.util.JpqlExpressionBuilder;

/**
 * Implements default functionality of data {@link DataRetrievalExpression}
 * Retrieves data as it is - without sorting or filtering.
 *
 * @author S. Fink
 */
public class DefaultExpression implements DataRetrievalExpression {
    protected JpqlParams jpqlParams;

    /**
     * Creates <code>DefaultExpression</code> with custom JPQL parameters
     *
     * @param jpqlParams contains parameters for building expressions; e.g. entity name and its alias
     */
    public DefaultExpression(JpqlParams jpqlParams) {
        this.jpqlParams = jpqlParams;
    }

    /**
     * Builds JPQL select statement
     *
     * @return String representation of JPQL select statement
     */
    public String evaluateExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);
        builder.buildSelectExpression();
        return builder.getExpression();
    }

    public JpqlParams getJpqlParams() {
        return jpqlParams;
    }

    public void setJpqlParams(JpqlParams jpqlParams) {
        this.jpqlParams = jpqlParams;
    }
}
