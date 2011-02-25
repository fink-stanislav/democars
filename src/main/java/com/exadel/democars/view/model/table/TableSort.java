package com.exadel.democars.view.model.table;

import com.exadel.democars.view.model.datasource.SortableDataSource;
import org.richfaces.component.SortOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TableSort<T> {
    private TableDataModel tableDataModel;
    private Map<String, SortOrder> sortParams;

    public TableSort(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
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

    public SortableDataSource sort() {
        Map<String, SortOrder> sortParams = new HashMap<String, SortOrder>();
        sortParams.putAll(this.sortParams);
        Set<Map.Entry<String, SortOrder>> entrySet = this.sortParams.entrySet();
        for (Map.Entry<String, SortOrder> entry : entrySet) {
            if (entry.getValue() == SortOrder.unsorted) {
                sortParams.remove(entry.getKey());
            }
        }
        SortableDataSource sortableDataSource = new SortableDataSource(tableDataModel);
        sortableDataSource.setSortParams(sortParams);
        return sortableDataSource;
    }
}
