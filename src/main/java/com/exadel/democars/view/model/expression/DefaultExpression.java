package com.exadel.democars.view.model.expression;

import com.exadel.democars.util.JpqlExpressionBuilder;

public class DefaultExpression implements DataRetrievalExpression {
    protected JpqlParams jpqlParams;
    protected PaginationParams paginationParams;

    public DefaultExpression(JpqlParams jpqlParams) {
        this.paginationParams = new PaginationParams();
        this.jpqlParams = jpqlParams;
    }

    public DefaultExpression(PaginationParams paginationParams, JpqlParams jpqlParams) {
        this.paginationParams = paginationParams;
        this.jpqlParams = jpqlParams;
    }

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

    public PaginationParams getPaginationParams() {
        return paginationParams;
    }

    public void setPaginationParams(PaginationParams paginationParams) {
        this.paginationParams = paginationParams;
    }
}
