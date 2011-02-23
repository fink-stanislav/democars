package com.exadel.democars.view.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterableDataSource<T> extends PagebleDataSource<T> {
    private String tableName;
    Map<String, String> filterParams;

    public FilterableDataSource(TableDataModel tableDataModel) {
        this.tableName = "Car";
        this.pageSize = tableDataModel.getPageSize();
        this.currentPage = tableDataModel.getCurrentPage();
        this.tableDataModel = tableDataModel;
        this.filterParams = new HashMap<String, String>();
    }

    public void setFilterParams(Map<String, String> filterParams) {
        this.filterParams = filterParams;
    }

    public Map<String, String> getFilterParams() {
        return filterParams;
    }

    public String getTableName() {
        return tableName;
    }

    public List<T> updateRows() {
        return tableDataModel.getDataManager().getRangedFilteredList(this);
    }
}
