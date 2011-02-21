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
    private Integer pageNumber = 1;

    public TableDataModel() {
        dataManager = new DataManager();
        rows = dataManager.getRangedEntityListByNamedQuery("findAllCars", pageSize, pageNumber);
    }

    public TableDataModel(Integer pageSize, Integer pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        dataManager = new DataManager();
        rows = dataManager.getRangedEntityListByNamedQuery("findAllCars", pageSize, pageNumber);
    }

    public void updateRows() {
        rows = dataManager.getRangedEntityListByNamedQuery("findAllCars", pageSize, pageNumber);
    }

    @Override
    public boolean isRowAvailable() {
        return rows.get(getRowIndex()) != null;
    }

    @Override
    public int getRowCount() {
        return dataManager.performCountQuery("countCars");
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

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
