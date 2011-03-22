package org.richfaces.democars.model.expression;

/**
 * Common interface for expression-generating classes.
 * Expression is a JPQL query or a part of it. It provides data retrieval from
 * database through JPA. Expressions helps to retrieve sorted or filtered data, or both,
 * or retrieve it as it is.
 *
 * @author S. Fink
 */
public interface DataRetrievalExpression {
    /**
     * Creates and returns Jpql-expression for obtaining necessary rows of data.
     *
     * @return String representation of Jpql-expression
     */
    String evaluate();
}
