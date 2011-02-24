package com.exadel.democars.view.model.datasource;

import com.exadel.democars.view.model.table.TableDataModel;

import java.util.List;

public class SortableDataSource<T> extends JpqlDataSource<T> {
    private String sortParam;
    private String sortOrder;

    public SortableDataSource(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
        this.tableName = "Car";
        this.pageSize = tableDataModel.getPageSize();
        this.currentPage = tableDataModel.getCurrentPage();
        this.tableAlias = "c";
    }

    public List<T> updateRows() {
        return tableDataModel.getDataManager().getRangedSortedList(this);
    }

    public Integer rowCount() {
        return tableDataModel.getDataManager().getRowCount();
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
