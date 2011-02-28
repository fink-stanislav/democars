package com.exadel.democars.view.model.datasource;

import com.exadel.democars.view.model.table.TableDataModel;

public abstract class PageableDataSource implements DataSource {
    protected TableDataModel tableDataModel;

    public void setPageSize(Integer pageSize) {
        tableDataModel.setPageSize(pageSize);
    }

    public void setCurrentPage(Integer currentPage) {
        tableDataModel.setCurrentPage(currentPage);
    }

    public Integer getPageSize() {
        return tableDataModel.getPageSize();
    }

    public Integer getCurrentPage() {
        return tableDataModel.getCurrentPage();
    }

    public abstract Integer rowCount();
}
