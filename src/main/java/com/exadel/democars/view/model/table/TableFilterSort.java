package com.exadel.democars.view.model.table;

import com.exadel.democars.util.JpqlExpressionBuilder;
import com.exadel.democars.view.model.expression.*;
import org.richfaces.component.SortOrder;

import java.util.HashMap;
import java.util.Map;

public class TableFilterSort {
    private Map<String, SortOrder> sortParams;
    private Map<String, Object> filterParams;
    private SortExpression sortExpression;
    private FilterExpression filterExpression;

    public TableFilterSort(PaginationParams paginationParams, JpqlParams jpqlParams) {
        filterExpression = new FilterExpression(paginationParams, jpqlParams);
        filterParams = new HashMap<String, Object>();
        filterExpression.setFilterParams(filterParams);

        sortExpression = new SortExpression(paginationParams, jpqlParams);
        sortParams = new HashMap<String, SortOrder>();
        sortExpression.setSortParams(sortParams);
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
        sortExpression.setSortParams(sortParams);
        sortExpression.evaluateExpression();
    }

    public void filter() {
        filterExpression.setFilterParams(filterParams);
        filterExpression.evaluateExpression();
    }

    public String buildFilterSortExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(sortExpression.getJpqlParams());
        builder.buildSelectExpression();
        FilterSortExpression filterSortExpression =
                new FilterSortExpression(sortExpression, filterExpression);
        builder.append(filterSortExpression.evaluateExpression());
        return builder.getExpression();
    }
}
