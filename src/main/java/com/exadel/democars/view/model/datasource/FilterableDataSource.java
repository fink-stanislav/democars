package com.exadel.democars.view.model.datasource;

import com.exadel.democars.util.JpqlExpressionBuilder;
import com.exadel.democars.view.model.table.TableDataModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FilterableDataSource<T> extends JpqlDataSource<T> {
    private Map<String, Object> filterParams;

    public FilterableDataSource(TableDataModel tableDataModel) {
        this.tableName = "Car";
        this.pageSize = tableDataModel.getPageSize();
        this.currentPage = tableDataModel.getCurrentPage();
        this.tableDataModel = tableDataModel;
        this.filterParams = new HashMap<String, Object>();
        this.tableAlias = "c";
    }

    public JpqlExpressionBuilder evaluateFilterExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(this);
        builder.buildSelectExpression();

        Set<Map.Entry<String, Object>> entrySet = filterParams.entrySet();
        if (entrySet.isEmpty()) {
            return builder;
        }

        builder.addWhere();
        int counter = 0;
        for (Map.Entry<String, Object> entry : entrySet) {
            counter++;
            if (entry.getValue() instanceof String) {
                builder.buildLikeExpression(entry.getKey(), entry.getValue());
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addAnd();
                }
            } else if (entry.getValue() instanceof Number) {
                builder.buildComparsionExpression(entry.getKey(), entry.getValue(), "<=");
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addAnd();
                }
            }
        }
        return builder;
    }

    public List<T> updateRows() {
        return tableDataModel.getDataManager().executeQuery(evaluateFilterExpression(), this);
    }

    public Integer rowCount() {
        return tableDataModel.getDataManager().getRowCount();
    }

    public Map<String, Object> getFilterParams() {
        return filterParams;
    }

    public void setFilterParams(Map<String, Object> filterParams) {
        this.filterParams = filterParams;
    }
}
