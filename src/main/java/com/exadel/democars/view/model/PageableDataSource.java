package com.exadel.democars.view.model;

import com.exadel.democars.model.persistence.DataManager;

public abstract class PageableDataSource<T> implements DataSource<T> {
    protected Integer pageSize = 10;
    protected Integer currentPage = 1;
    protected DataManager dataManager;

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
