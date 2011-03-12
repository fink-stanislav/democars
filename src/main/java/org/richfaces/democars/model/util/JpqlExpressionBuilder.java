package org.richfaces.democars.model.util;

import org.richfaces.democars.view.model.expression.JpqlParams;

import static org.apache.commons.lang.StringUtils.*;
import static org.apache.commons.lang.StringUtils.INDEX_NOT_FOUND;

/**
 * Class for building JPQL expressions. Based on {@code StringBuilder}
 *
 * @author S. Fink
 */
public class JpqlExpressionBuilder {
    private String entityAlias;
    private String entityName;
    private StringBuilder expression;

    public JpqlExpressionBuilder(JpqlParams params) {
        this.entityName = params.getEntityName();
        this.entityAlias = params.getEntityAlias();
        expression = new StringBuilder();
    }

    /**
     * Builds JPQL select statement, based on specified entity alias and entity class name.
     *
     * @return builder
     */
    public JpqlExpressionBuilder buildSelectExpression() {
        StringBuilder sb = new StringBuilder();
        sb.append(" select ")
                .append(entityAlias)
                .append(" from ")
                .append(entityName).append(" ")
                .append(entityAlias);
        return this.append(sb.toString());
    }

    public JpqlExpressionBuilder buildCountExpression(String dataRetrievalExpression) {
        StringBuilder sb = new StringBuilder();
        sb.append(" select count(")
                .append(entityAlias)
                .append(") from ")
                .append(entityName).append(" ")
                .append(entityAlias);
        append(sb.toString());
        append(dataRetrievalExpression);

        excludeOrderBy();

        return this;
    }

    public JpqlExpressionBuilder excludeOrderBy() {
        int orderByPosition = indexOf(getExpression(), "order by");
        if (orderByPosition != INDEX_NOT_FOUND) {
            replace(substring(getExpression(), 0, orderByPosition));
        }
        return this;
    }

    public JpqlExpressionBuilder buildLikeExpression(String paramName, Object paramValue) {
        StringBuilder sb = new StringBuilder();
        sb.append(" upper(").append(entityAlias).append(".")
                .append(paramName)
                .append(") like '")
                .append(((String) paramValue).toUpperCase())
                .append("%'");
        return this.append(sb.toString());
    }

    public JpqlExpressionBuilder buildComparisonExpression(String paramName, Object paramValue, String sign) {
        StringBuilder sb = new StringBuilder();
        sb.append(entityAlias)
                .append(".")
                .append(paramName).append(" ")
                .append(sign).append(" ")
                .append(paramValue);
        return this.append(sb.toString());
    }

    public JpqlExpressionBuilder append(String expression) {
        this.expression.append(" ").append(expression);
        return this;
    }

    public JpqlExpressionBuilder replace(String expression) {
        this.expression = new StringBuilder(expression);
        return this;
    }

    public JpqlExpressionBuilder buildOrderByExpression() {
        return append("order by ");
    }

    public JpqlExpressionBuilder addComma() {
        return append(", ");
    }

    public JpqlExpressionBuilder addOrderParams(String paramName, String sortOrder) {
        StringBuilder sb = new StringBuilder();
        sb.append(" ")
                .append(entityAlias).append(".")
                .append(paramName).append(" ")
                .append(sortOrder);
        return this.append(sb.toString());
    }

    public JpqlExpressionBuilder addWhere() {
        return append(" where ");
    }

    public JpqlExpressionBuilder addAnd() {
        return append(" and ");
    }

    public String getExpression() {
        return expression.toString();
    }

    /**
     * Checks whether number of params is greater than one and current param is not penultimate.
     * It is used to decide: insert "AND" or "," in expression or not
     *
     * @param number   number of params
     * @param position current param
     * @return is it necessary to insert "," or "AND" or not
     */
    public boolean isRangeOk(Integer number, Integer position) {
        return number > 1 && position <= number - 1;
    }
}
