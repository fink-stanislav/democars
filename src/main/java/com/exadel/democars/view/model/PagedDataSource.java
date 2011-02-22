package com.exadel.democars.view.model;

import com.exadel.democars.model.persistence.DataManager;

import java.util.List;

public abstract class PagedDataSource<T> implements DataSource<T> {
    protected Integer pageSize = 10;
    protected Integer currentPage = 1;
    protected DataManager dataManager;
    protected String queryName;

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
