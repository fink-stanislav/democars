package com.exadel.democars.view.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TableFilter<T> {
    private TableDataModel tableDataModel;
    private Map<String, Object> filterParams;
    private Map<String, String> filterExpressions;

    public TableFilter(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
        this.filterParams = new HashMap<String, Object>();
        this.filterExpressions = new HashMap<String, String>();
    }

    private void evaluateExpressions() {
        Set<Map.Entry<String, Object>> entrySet = filterParams.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            if (entry.getValue() instanceof String) {
                filterExpressions.put(entry.getKey(), (" like '" + entry.getValue() + "%'").toUpperCase());
            }
            if (entry.getValue() instanceof Number) {
                filterExpressions.put(entry.getKey(), " <= " + entry.getValue());
            }
        }
    }

    public Map<String, Object> getFilterParams() {
        return filterParams;
    }

    public Map<String, String> getFilterExpressions() {
        return filterExpressions;
    }

    public void filter() {
        evaluateExpressions();
        FilterableDataSource<T> filterableDataSource = new FilterableDataSource<T>(tableDataModel);
        filterableDataSource.setFilterParams(filterParams);
        filterableDataSource.setFilterExpressions(filterExpressions);
        tableDataModel.setCurrentDataSource(filterableDataSource);
    }
}
