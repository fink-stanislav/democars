package com.exadel.democars.view.model;

import java.util.List;

public class SortableDataSource<T> extends PagebleDataSource<T> {
    private String sortParam;
    private String sortOrder;
    private String tableName;

    public SortableDataSource(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
        this.tableName = "Car";
        this.pageSize = tableDataModel.getPageSize();
        this.currentPage = tableDataModel.getCurrentPage();
    }

    public List<T> updateRows() {
        return tableDataModel.getDataManager().getRangedSortedList(this);
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getTableName() {
        return tableName;
    }

    public Integer rowCount() {
        return tableDataModel.getDataManager().getSingle("countCars");
    }
}
