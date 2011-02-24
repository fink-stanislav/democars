package com.exadel.democars.view.model.datasource;

import com.exadel.democars.view.model.table.TableDataModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterableDataSource<T> extends JpqlDataSource<T> {
    private Map<String, Object> filterParams;

    public FilterableDataSource(TableDataModel tableDataModel) {
        this.tableName = "Car";
        this.pageSize = tableDataModel.getPageSize();
        this.currentPage = tableDataModel.getCurrentPage();
        this.tableDataModel = tableDataModel;
        this.filterParams = new HashMap<String, Object>();
        this.tableAlias = "c";
    }

    public List<T> updateRows() {
        return tableDataModel.getDataManager().getRangedFilteredList(this);
    }

    public Integer rowCount() {
        return tableDataModel.getDataManager().getRowCount();
    }

    public Map<String, Object> getFilterParams() {
        return filterParams;
    }

    public void setFilterParams(Map<String, Object> filterParams) {
        this.filterParams = filterParams;
    }
}
