package com.exadel.democars.view.model;

import java.util.List;

public class DefaultDataSource<T> extends PagebleDataSource<T> {
    private String queryName;

    public DefaultDataSource(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
        this.pageSize = tableDataModel.getPageSize();
        this.currentPage = tableDataModel.getCurrentPage();
        this.queryName = "allCars";
    }

    public String getQueryName() {
        return queryName;
    }

    public List<T> updateRows() {
        return tableDataModel.getDataManager().getRangedList(this);
    }

    public Integer rowCount() {
        return tableDataModel.getDataManager().getSingle("countCars");
    }
}
