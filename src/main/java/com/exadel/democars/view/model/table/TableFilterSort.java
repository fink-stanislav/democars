package com.exadel.democars.view.model.table;

import com.exadel.democars.view.model.datasource.FilterableDataSource;
import com.exadel.democars.view.model.datasource.FilterableSortableDataSource;
import com.exadel.democars.view.model.datasource.SortableDataSource;
import org.richfaces.component.SortOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TableFilterSort<T> {
    private TableDataModel tableDataModel;
    private Map<String, SortOrder> sortParams;
    private Map<String, Object> filterParams;
    private SortableDataSource<T> sortableDataSource;
    private FilterableDataSource<T> filterableDataSource;

    public TableFilterSort(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;

        filterParams = new HashMap<String, Object>();
        filterableDataSource = new FilterableDataSource<T>(tableDataModel);
        filterableDataSource.setFilterParams(filterParams);

        sortParams = new HashMap<String, SortOrder>();
        sortableDataSource = new SortableDataSource<T>(tableDataModel);
        sortableDataSource.setSortParams(sortParams);
    }

    public boolean isUnsorted(String key) {
        return SortOrder.unsorted == sortParams.get(key);
    }

    public boolean isAscending(String key) {
        return SortOrder.ascending == sortParams.get(key);
    }

    public boolean isDescending(String key) {
        return SortOrder.descending == sortParams.get(key);
    }

    public void sortAscending(String columnName) {
        sortParams.put(columnName, SortOrder.ascending);
        sort();
    }

    public void sortDescending(String columnName) {
        sortParams.put(columnName, SortOrder.descending);
        sort();
    }

    public void makeUnsorted(String columnName) {
        sortParams.put(columnName, SortOrder.unsorted);
        sort();
    }

    public Map<String, Object> getFilterParams() {
        return filterParams;
    }

    public void sort() {
        SortableDataSource<T> sortableDataSource = new SortableDataSource<T>(tableDataModel);
        sortableDataSource.setSortParams(sortParams);
        this.sortableDataSource = sortableDataSource;
        updateModelDataSource();
    }

    public void filter() {
        FilterableDataSource<T> filterableDataSource = new FilterableDataSource<T>(tableDataModel);
        filterableDataSource.setFilterParams(filterParams);
        this.filterableDataSource = filterableDataSource;
        updateModelDataSource();
    }

    private void updateModelDataSource() {
        FilterableSortableDataSource<T> filterableSortableDataSource =
                new FilterableSortableDataSource<T>(sortableDataSource, filterableDataSource);
        tableDataModel.setCurrentDataSource(filterableSortableDataSource);
    }
}
