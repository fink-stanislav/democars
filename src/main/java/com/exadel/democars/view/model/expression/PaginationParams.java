package com.exadel.democars.view.model.expression;

public class PaginationParams {
    protected Integer pageSize;
    protected Integer currentPage;

    public PaginationParams() {
        this.pageSize = 10;
        this.currentPage = 1;
    }

    public PaginationParams(Integer pageSize, Integer currentPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
