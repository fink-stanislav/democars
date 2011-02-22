package com.exadel.democars.view.model;

import java.util.List;

public class SortableDataSource<T> extends PagedDataSource<T> {
    private String sortParam;

    public SortableDataSource(String queryName) {
        this.queryName = queryName;
    }

    public SortableDataSource(String queryName, Integer pageSize, Integer currentPage) {
        this.queryName = queryName;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public List<T> updateRows() {
        return dataManager.getRangedSortedList(queryName, pageSize, currentPage, sortParam);
    }

    public String getSortParam() {
        return sortParam;
    }

    public void setSortParam(String sortParam) {
        this.sortParam = sortParam;
    }
}
