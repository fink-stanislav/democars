package com.exadel.democars.view.model.datasource;

import com.exadel.democars.view.model.table.TableDataModel;

public abstract class PageableDataSource<T> implements DataSource<T> {
    protected Integer pageSize = 10;
    protected Integer currentPage = 1;
    protected TableDataModel tableDataModel;

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public abstract Integer rowCount();
}