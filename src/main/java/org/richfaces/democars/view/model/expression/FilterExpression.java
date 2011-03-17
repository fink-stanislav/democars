package org.richfaces.democars.view.model.expression;

import org.richfaces.democars.model.util.JpqlExpressionBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Provides filter expression evaluation
 *
 * @author S. Fink
 */
public class FilterExpression extends DefaultExpression {
    private Map<String, Object> filterParams;

    public FilterExpression(JpqlParams jpqlParams) {
        super(jpqlParams);
        this.filterParams = new HashMap<String, Object>();
    }

    /**
     * Builds JPQL expression for filtering data.
     * Cleans up {@code filterParams} map from null values and empty strings.
     * If {@code filterParams} map become empty, returns built query - in this case empty string.
     * Otherwise builds like expression for string parameters and comparison expression for numeric ones.
     *
     * @return string representation of JPQL statement
     */
    public String evaluate() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);

        Set<Map.Entry<String, Object>> entrySet = cleanUpParams(filterParams).entrySet();
        if (entrySet.isEmpty()) {
            return builder.getExpression();
        }

        int counter = 0;
        builder.addWhere();
        for (Map.Entry<String, Object> entry : entrySet) {
            counter++;
            if (entry.getValue() instanceof String) {
                builder.buildLikeExpression(entry.getKey(), entry.getValue());
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addAnd();
                }
            } else if (entry.getValue() instanceof Number) {
                builder.buildComparisonExpression(entry.getKey(), entry.getValue(), "<=");
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addAnd();
                }
            }
        }
        return builder.getExpression();
    }

    private Map<String, Object> cleanUpParams(Map<String, Object> params) {
        Map<String, Object> filterParams = new HashMap<String, Object>();
        filterParams.putAll(params);
        Set<Map.Entry<String, Object>> filterParamEntrySet = params.entrySet();
        for (Map.Entry<String, Object> entry : filterParamEntrySet) {
            if (entry.getValue() == null) {
                filterParams.remove(entry.getKey());
            } else if (entry.getValue() instanceof String) {
                if (entry.getValue().equals("")) {
                    filterParams.remove(entry.getKey());
                }
            }
        }
        return filterParams;
    }

    public void setFilterParams(Map<String, Object> filterParams) {
        this.filterParams = filterParams;
    }
}
