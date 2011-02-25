package com.exadel.democars.view.model.table;

import com.exadel.democars.view.model.datasource.FilterableSortableDataSource;

public class TableFilterSort<T> {
    private TableSort<T> tableSort;
    private TableFilter<T> tableFilter;
    private TableDataModel<T> tableDataModel;

    public TableFilterSort(TableDataModel<T> tableDataModel) {
        this.tableDataModel = tableDataModel;
        tableFilter = new TableFilter<T>(tableDataModel);
        tableSort = new TableSort<T>(tableDataModel);
    }

    public void execute() {
        FilterableSortableDataSource<T> filterableSortableDataSource =
                new FilterableSortableDataSource<T>(tableSort.sort(), tableFilter.filter());
        tableDataModel.setCurrentDataSource(filterableSortableDataSource);
    }

    public TableSort<T> getTableSort() {
        return tableSort;
    }

    public TableFilter<T> getTableFilter() {
        return tableFilter;
    }
}
