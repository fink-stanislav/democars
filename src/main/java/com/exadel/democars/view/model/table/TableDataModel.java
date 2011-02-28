package com.exadel.democars.view.model.table;

import com.exadel.democars.model.persistence.DataManager;
import com.exadel.democars.view.model.datasource.DefaultDataSource;
import com.exadel.democars.view.model.datasource.PageableDataSource;

import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;

public class TableDataModel<T> extends DataModel<T> implements Serializable {
    private DataManager dataManager;
    private Integer rowIndex = 0;
    private List<T> rows;
    private Integer pageSize = 10;
    private Integer currentPage = 1;
    private PageableDataSource currentDataSource;
    private String dbTableName;
    private String dbTableAlias;

    public TableDataModel(Integer pageSize, Integer currentPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        dataManager = new DataManager();
        dbTableName = "Car";
        dbTableAlias = "c";
        currentDataSource = new DefaultDataSource(this);
    }

    public void updateRows() {
        currentDataSource.setCurrentPage(currentPage);
        currentDataSource.setPageSize(pageSize);
        currentDataSource.updateRows();
    }

    @Override
    public boolean isRowAvailable() {
        if (rows.size() <= getRowIndex()) {
            return false;
        }
        return rows.size() > 0 && rows.get(getRowIndex()) != null;
    }

    @Override
    public int getRowCount() {
        return currentDataSource.rowCount();
    }

    @Override
    public T getRowData() {
        return rows.get(getRowIndex());
    }

    @Override
    public int getRowIndex() {
        return rowIndex % pageSize;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public Object getWrappedData() {
        return rows;
    }

    @Override
    public void setWrappedData(Object rows) {
        this.rows = (List<T>) rows;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public PageableDataSource getCurrentDataSource() {
        return currentDataSource;
    }

    public void setCurrentDataSource(PageableDataSource currentDataSource) {
        this.currentDataSource = currentDataSource;
    }

    public String getDbTableName() {
        return dbTableName;
    }

    public void setDbTableName(String dbTableName) {
        this.dbTableName = dbTableName;
    }

    public String getDbTableAlias() {
        return dbTableAlias;
    }

    public void setDbTableAlias(String dbTableAlias) {
        this.dbTableAlias = dbTableAlias;
    }
}
