package org.richfaces.democars.view.model.expression;

/**
 * Implements default functionality of {@link DataRetrievalExpression}
 * Retrieves data as it is - without sorting or filtering.
 *
 * @author S. Fink
 */
public class DefaultExpression implements DataRetrievalExpression {
    protected JpqlParams jpqlParams;

    /**
     * Creates {@code DefaultExpression} with custom JPQL parameters
     *
     * @param jpqlParams contains parameters for building expressions; e.g. entity name and its alias
     */
    public DefaultExpression(JpqlParams jpqlParams) {
        this.jpqlParams = jpqlParams;
    }

    /**
     * Returns Empty string
     *
     * @return expression for retrieval all data - empty string
     */
    public String evaluateExpression() {
        return "";
    }

    public JpqlParams getJpqlParams() {
        return jpqlParams;
    }

    public void setJpqlParams(JpqlParams jpqlParams) {
        this.jpqlParams = jpqlParams;
    }
}
