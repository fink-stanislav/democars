package com.exadel.democars.view.model.expression;

public interface DataRetrievalExpression {
    /**
     * Creates and returns Jpql-expression for obtaining necessary rows of data.
     * It had been named expression since not every expression could be full Jpql-query;
     * It could be just a part of it.
     *
     * @return String representation of Jpql-expression
     */
    String evaluateExpression();
}
