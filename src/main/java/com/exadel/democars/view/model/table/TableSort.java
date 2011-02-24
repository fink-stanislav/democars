package com.exadel.democars.view.model.table;

import com.exadel.democars.view.model.datasource.SortableDataSource;
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

    public void sort() {}

    public void sortColumn(String columnName) {
        changeSortOrder();
        SortableDataSource<T> sortableDataSource =
                new SortableDataSource<T>(tableDataModel);
        sortableDataSource.setSortParam(columnName);
        sortableDataSource.setSortOrder(sortOrderToQl());
        tableDataModel.setCurrentDataSource(sortableDataSource);
    }

    private void changeSortOrder() {
        sortOrder = sortOrder == ascending ? descending : ascending;
    }

    private String sortOrderToQl() {
        return sortOrder == SortOrder.descending ? "desc" : "asc";
    }
}
