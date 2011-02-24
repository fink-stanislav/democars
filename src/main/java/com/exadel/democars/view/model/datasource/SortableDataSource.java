package com.exadel.democars.view.model.datasource;

import com.exadel.democars.view.model.table.TableDataModel;
import org.richfaces.component.SortOrder;

import java.util.List;
import java.util.Map;

public class SortableDataSource<T> extends JpqlDataSource<T> {
    private Map<String, SortOrder> sortParams;

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

    public Map<String, SortOrder> getSortParams() {
        return sortParams;
    }

    public void setSortParams(Map<String, SortOrder> sortParams) {
        this.sortParams = sortParams;
    }
}
