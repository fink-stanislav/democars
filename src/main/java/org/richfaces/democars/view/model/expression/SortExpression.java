package org.richfaces.democars.view.model.expression;

import org.richfaces.democars.model.util.JpqlExpressionBuilder;
import org.richfaces.component.SortOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Provides sorting expression evaluation.
 *
 * @author S. Fink
 */
public class SortExpression extends DefaultExpression {
    private Map<String, SortOrder> sortParams;

    public SortExpression(JpqlParams jpqlParams) {
        super(jpqlParams);
        sortParams = new HashMap<String, SortOrder>();
    }

    /**
     * Builds JPQL expression for filtering sqlscript
     * First, cleans up the parameters from {@code SortOrder.unsorted} values;
     * Second, builds ORDER BY statement: inserts appropriate parameter names and ASC or DESC words.
     *
     * @return String representation of sorting JPQL-expression.
     */
    public String evaluateExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);

        Set<Map.Entry<String, SortOrder>> entrySet = cleanUpParams(sortParams).entrySet();
        if (entrySet.isEmpty()) {
            return builder.getExpression();
        }

        builder.buildOrderByExpression();
        int counter = 0;
        for (Map.Entry<String, SortOrder> entry : entrySet) {
            counter++;
            if (entry.getValue() == SortOrder.ascending) {
                builder.addOrderParams(entry.getKey(), "asc");
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addComma();
                }
            } else if (entry.getValue() == SortOrder.descending) {
                builder.addOrderParams(entry.getKey(), "desc");
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addComma();
                }
            }
        }
        return builder.getExpression();
    }

    private Map<String, SortOrder> cleanUpParams(Map<String, SortOrder> params) {
        Map<String, SortOrder> sortParams = new HashMap<String, SortOrder>();
        sortParams.putAll(params);
        Set<Map.Entry<String, SortOrder>> sortParamEntrySet = params.entrySet();
        for (Map.Entry<String, SortOrder> entry : sortParamEntrySet) {
            if (entry.getValue() == SortOrder.unsorted) {
                sortParams.remove(entry.getKey());
            }
        }
        return sortParams;
    }

    public void setSortParams(Map<String, SortOrder> sortParams) {
        this.sortParams = sortParams;
    }
}
