package com.exadel.democars.view.model;

import com.exadel.democars.model.persistence.DataManager;

import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;

public class TableDataModel<T> extends DataModel<T> implements Serializable {
    private DataManager dataManager;
    private Integer rowIndex = 0;
    private List<T> rows;
    private Integer pageSize = 10;
    private Integer currentPage = 1;
    private PageableDataSource<T> currentDataSource;

    public TableDataModel() {
        dataManager = new DataManager();
        currentDataSource = new DefaultDataSource<T>("allCars", pageSize, currentPage);
        currentDataSource.setDataManager(dataManager);
    }

    public TableDataModel(Integer pageSize, Integer currentPage) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        dataManager = new DataManager();
        currentDataSource = new DefaultDataSource<T>("allCars", pageSize, currentPage);
        currentDataSource.setDataManager(dataManager);
    }

    public void updateRows() {
        currentDataSource.setCurrentPage(currentPage);
        currentDataSource.setPageSize(pageSize);
        rows = currentDataSource.updateRows();
    }

    @Override
    public boolean isRowAvailable() {
        return rows.size() > 0 && rows.get(getRowIndex()) != null;
    }

    @Override
    public int getRowCount() {
        return dataManager.getSingle("countCars");
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
}
