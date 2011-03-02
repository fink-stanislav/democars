package com.exadel.democars.view.model.expression;

/**
 * Encapsulates JPQL parameters for retrieving data
 * This class was provided for convenience of using such parameters.
 * Number of parameters to pass was reduced by using this class.
 *
 * @author S. Fink
 */
public class JpqlParams {
    protected String entityName;
    protected String entityAlias;

    public JpqlParams(String entityName, String entityAlias) {
        this.entityName = entityName;
        this.entityAlias = entityAlias;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityAlias() {
        return entityAlias;
    }

    public void setEntityAlias(String entityAlias) {
        this.entityAlias = entityAlias;
    }
}
