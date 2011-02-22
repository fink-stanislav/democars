package com.exadel.democars.view.model;

import java.util.List;

public class SortableDataSource<T> extends PagedDataSource<T> {
    private String sortParam;
    private String sortOrder;
    private String tableName;

    public SortableDataSource(String tableName, Integer pageSize, Integer currentPage) {
        this.tableName = tableName;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public List<T> updateRows() {
        return dataManager.getRangedSortedList(tableName, pageSize, currentPage, sortParam, sortOrder);
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
}
