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
        this.filterParams = new HashMap<String, Object>();
        sortableDataSource = new SortableDataSource<T>(tableDataModel);
        filterableDataSource = new FilterableDataSource<T>(tableDataModel);
        sortParams = new HashMap<String, SortOrder>();
        sortParams.put("model.make", SortOrder.unsorted);
        sortParams.put("model.model", SortOrder.unsorted);
        sortParams.put("price", SortOrder.unsorted);
        sortParams.put("seller.address.city", SortOrder.unsorted);
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

    public void sort() {
        Map<String, SortOrder> sortParams = new HashMap<String, SortOrder>();
        sortParams.putAll(this.sortParams);
        Set<Map.Entry<String, SortOrder>> entrySet = this.sortParams.entrySet();
        for (Map.Entry<String, SortOrder> entry : entrySet) {
            if (entry.getValue() == SortOrder.unsorted) {
                sortParams.remove(entry.getKey());
            }
        }
        SortableDataSource<T> sortableDataSource = new SortableDataSource<T>(tableDataModel);
        sortableDataSource.setSortParams(sortParams);
        this.sortableDataSource = sortableDataSource;
        execute();
    }

    public Map<String, Object> getFilterParams() {
        return filterParams;
    }

    public void filter() {
        FilterableDataSource<T> filterableDataSource = new FilterableDataSource<T>(tableDataModel);
        filterableDataSource.setFilterParams(filterParams);
        this.filterableDataSource = filterableDataSource;
        execute();
    }

    private void execute() {
        FilterableSortableDataSource<T> filterableSortableDataSource =
                new FilterableSortableDataSource<T>(sortableDataSource, filterableDataSource);
        tableDataModel.setCurrentDataSource(filterableSortableDataSource);
    }
}