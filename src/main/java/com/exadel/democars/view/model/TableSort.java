package com.exadel.democars.view.model;

import org.richfaces.component.SortOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TableSort {
    private Map<String, SortOrder> sortOrder;

    public TableSort() {
        sortOrder = new HashMap<String, SortOrder>();
        sortOrder.put("make", SortOrder.unsorted);
        sortOrder.put("model", SortOrder.unsorted);
        sortOrder.put("price", SortOrder.unsorted);
        sortOrder.put("condition", SortOrder.unsorted);
        sortOrder.put("mileage", SortOrder.unsorted);
        sortOrder.put("seller", SortOrder.unsorted);
    }

    public Map<String, SortOrder> getSortOrder() {
        return sortOrder;
    }

    private void resetSortOrder(String key) {
        Set<Map.Entry<String, SortOrder>> entrySet = this.sortOrder.entrySet();
        for (Map.Entry<String, SortOrder> entry : entrySet) {
            if (!entry.getKey().equals(key)) {
                entry.setValue(SortOrder.unsorted);
            }
        }
    }

    public void changeSortOrder(String key) {
        resetSortOrder(key);
        if (sortOrder.get(key).equals(SortOrder.ascending)) {
            sortOrder.put(key, SortOrder.descending);
        } else {
            sortOrder.put(key, SortOrder.ascending);
        }
    }
}
