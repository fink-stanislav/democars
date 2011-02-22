package com.exadel.democars.view.model;

import org.richfaces.component.SortOrder;

import static org.richfaces.component.SortOrder.ascending;
import static org.richfaces.component.SortOrder.descending;

public class TableSort<T> {
    private SortOrder sortOrder;
    private TableDataModel tableDataModel;
    private String queryName;

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
                new SortableDataSource<T>(queryName, tableDataModel.getPageSize(), tableDataModel.getCurrentPage());
        sortableDataSource.setSortParam(columnName);
        sortableDataSource.setDataManager(tableDataModel.getDataManager());
        tableDataModel.setCurrentDataSource(sortableDataSource);
    }

    public void changeSortOrder() {
        if (sortOrder == ascending) {
            sortOrder = descending;
            queryName = "allCarsDesc";

        } else {
            sortOrder = ascending;
            queryName = "allCarsAsc";
        }
    }
}
