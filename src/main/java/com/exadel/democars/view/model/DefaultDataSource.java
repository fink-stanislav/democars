package com.exadel.democars.view.model;

import java.util.List;

public class DefaultDataSource<T> extends PagedDataSource<T> {
    private String queryName;

    public DefaultDataSource(String queryName) {
        this.queryName = queryName;
    }

    public DefaultDataSource(String queryName, Integer pageSize, Integer currentPage) {
        this.queryName = queryName;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public List<T> updateRows() {
        return dataManager.getRangedList(queryName, pageSize, currentPage);
    }
}
