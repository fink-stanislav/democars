package com.exadel.democars.view.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterableDataSource<T> extends PagebleDataSource<T> {
    private String tableName;
    Map<String, Object> filterParams;
    Map<String, String> filterExpressions;

    public FilterableDataSource(TableDataModel tableDataModel) {
        this.tableName = "Car";
        this.pageSize = tableDataModel.getPageSize();
        this.currentPage = tableDataModel.getCurrentPage();
        this.tableDataModel = tableDataModel;
        this.filterParams = new HashMap<String, Object>();
        this.filterExpressions = new HashMap<String, String>();
    }

    public void setFilterParams(Map<String, Object> filterParams) {
        this.filterParams = filterParams;
    }

    public void setFilterExpressions(Map<String, String> filterExpressions) {
        this.filterExpressions = filterExpressions;
    }

    public Map<String, Object> getFilterParams() {
        return filterParams;
    }

    public Map<String, String> getFilterExpressions() {
        return filterExpressions;
    }

    public String getTableName() {
        return tableName;
    }

    public List<T> updateRows() {
        return tableDataModel.getDataManager().getRangedFilteredList(this);
    }

    public Integer rowCount() {
        return tableDataModel.getDataManager().getRowCount();
    }
}
