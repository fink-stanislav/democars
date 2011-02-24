package com.exadel.democars.view.model.table;

import com.exadel.democars.view.model.datasource.FilterableDataSource;

import java.util.HashMap;
import java.util.Map;

public class TableFilter<T> {
    private TableDataModel tableDataModel;
    private Map<String, Object> filterParams;

    public TableFilter(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
        this.filterParams = new HashMap<String, Object>();
    }

    public Map<String, Object> getFilterParams() {
        return filterParams;
    }

    public void filter() {
        FilterableDataSource<T> filterableDataSource = new FilterableDataSource<T>(tableDataModel);
        filterableDataSource.setFilterParams(filterParams);
        tableDataModel.setCurrentDataSource(filterableDataSource);
    }
}
