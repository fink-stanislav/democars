package com.exadel.democars.view.model.expression;

/**
 * Encapsulates parameters for pagination of retrieved data.
 * This class was provided for convenience of using such parameters.
 * Number of parameters to pass was reduced by using this class.
 * @author S. Fink
 */
public class PaginationParams {
    protected Integer pageSize;
    protected Integer currentPage;

    public PaginationParams() {
        this.pageSize = 10;
        this.currentPage = 1;
    }

    public PaginationParams(PaginationParams paginationParams) {
        this.pageSize = paginationParams.getPageSize();
        this.currentPage = paginationParams.getCurrentPage();
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
