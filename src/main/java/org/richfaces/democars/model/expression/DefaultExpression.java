package org.richfaces.democars.model.expression;

import org.richfaces.democars.model.params.EntityParams;

/**
 * Implements default functionality of {@link DataRetrievalExpression}
 * Retrieves data as it is - without sorting or filtering.
 *
 * @author S. Fink
 */
public class DefaultExpression implements DataRetrievalExpression {
    protected EntityParams entityParams;

    /**
     * Creates {@code DefaultExpression} with custom JPQL parameters
     *
     * @param entityParams contains parameters for building expressions; e.g. entity name and its alias
     */
    public DefaultExpression(EntityParams entityParams) {
        this.entityParams = entityParams;
    }

    /**
     * Returns Empty string
     *
     * @return expression for retrieval all data - empty string
     */
    public String evaluate() {
        return "";
    }

    public EntityParams getEntityParams() {
        return entityParams;
    }

    public void setEntityParams(EntityParams entityParams) {
        this.entityParams = entityParams;
    }
}
