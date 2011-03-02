package com.exadel.democars.util;

import com.exadel.democars.view.model.expression.JpqlParams;

public class JpqlExpressionBuilder {
    private String entityAlias;
    private String entityName;
    private StringBuilder expression;

    public JpqlExpressionBuilder(JpqlParams params) {
        this.entityName = params.getEntityName();
        this.entityAlias = params.getEntityAlias();
        expression = new StringBuilder();
    }

    public void buildSelectExpression() {
        StringBuilder sb = new StringBuilder();
        sb.append(" select ")
                .append(entityAlias)
                .append(" from ")
                .append(entityName).append(" ")
                .append(entityAlias);
        expression.append(sb);
    }

    public void buildLikeExpression(String paramName, Object paramValue) {
        StringBuilder sb = new StringBuilder();
        sb.append(" upper(").append(entityAlias).append(".")
                .append(paramName)
                .append(") like '")
                .append(((String) paramValue).toUpperCase())
                .append("%'");
        expression.append(sb);
    }

    public void buildComparisonExpression(String paramName, Object paramValue, String sign) {
        StringBuilder sb = new StringBuilder();
        sb.append(entityAlias)
                .append(".")
                .append(paramName).append(" ")
                .append(sign).append(" ")
                .append(paramValue);
        expression.append(sb);
    }

    public JpqlExpressionBuilder append(String expression) {
        this.expression.append(" ").append(expression);
        return this;
    }

    public void buildOrderByExpression() {
        expression.append(" order by ");
    }

    public void addComma() {
        expression.append(" , ");
    }

    public void addOrderParams(String paramName, String sortOrder) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ")
                .append(entityAlias).append(".")
                .append(paramName).append(" ")
                .append(sortOrder);
        expression.append(sb);
    }

    public void addWhere() {
        expression.append(" where ");
    }

    public void addAnd() {
        expression.append(" and ");
    }

    public String getExpression() {
        return expression.toString();
    }

    public boolean isRangeOk(Integer size, Integer position) {
        return size > 1 && position <= size - 1;
    }
}
