package com.exadel.democars.view.model.datasource;

import com.exadel.democars.util.JpqlExpressionBuilder;
import com.exadel.democars.view.model.table.TableDataModel;
import org.richfaces.component.SortOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SortableDataSource<T> extends JpqlDataSource<T> {
    private Map<String, SortOrder> sortParams;

    public SortableDataSource(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
        sortParams = new HashMap<String, SortOrder>();
    }

    public String evaluateExpression() {

        Map<String, SortOrder> sortParams = new HashMap<String, SortOrder>();
        sortParams.putAll(this.sortParams);
        Set<Map.Entry<String, SortOrder>> sortParamEntrySet = this.sortParams.entrySet();
        for (Map.Entry<String, SortOrder> entry : sortParamEntrySet) {
            if (entry.getValue() == SortOrder.unsorted) {
                sortParams.remove(entry.getKey());
            }
        }

        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(this);

        Set<Map.Entry<String, SortOrder>> entrySet = sortParams.entrySet();
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

    public Integer rowCount() {
        return tableDataModel.getDataManager().getRowCount();
    }

    public Map<String, SortOrder> getSortParams() {
        return sortParams;
    }

    public void setSortParams(Map<String, SortOrder> sortParams) {
        this.sortParams = sortParams;
    }
}
