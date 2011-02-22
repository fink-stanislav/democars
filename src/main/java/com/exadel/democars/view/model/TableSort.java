package com.exadel.democars.view.model;

import org.richfaces.component.SortOrder;

import static org.richfaces.component.SortOrder.ascending;
import static org.richfaces.component.SortOrder.descending;

public class TableSort<T> {
    private SortOrder sortOrder;
    private TableDataModel tableDataModel;

    public TableSort(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
        sortOrder = SortOrder.unsorted;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void sortColumn(String columnName) {
        changeSortOrder();
        SortableDataSource<T> sortableDataSource =
                new SortableDataSource<T>("Car", tableDataModel.getPageSize(), tableDataModel.getCurrentPage());
        sortableDataSource.setSortParam(columnName);
        sortableDataSource.setSortOrder(sortOrderToQl());
        sortableDataSource.setDataManager(tableDataModel.getDataManager());
        tableDataModel.setCurrentDataSource(sortableDataSource);
    }

    public void changeSortOrder() {
        sortOrder = sortOrder == ascending ? descending : ascending;
    }

    private String sortOrderToQl() {
        return sortOrder == SortOrder.descending ? "desc" : "asc";
    }
}
