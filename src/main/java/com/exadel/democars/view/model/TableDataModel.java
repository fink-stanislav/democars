package com.exadel.democars.view.model;

import com.exadel.democars.model.persistence.DataManager;

import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;

public class TableDataModel extends DataModel implements Serializable {
    private List list;
    private int pageSize;
    private int rowIndex;
    private DataManager dataManager;

    public TableDataModel() {
        super();
        dataManager = new DataManager();
        list = dataManager.getEntityListByNamedQuery("findAllCars");
        pageSize = 10;
    }

    public TableDataModel(int currentPage, int pageSize) {
        super();
        this.pageSize = pageSize;
        list = dataManager.getRangedEntityListByNamedQuery("findAllCars", currentPage, pageSize);
    }

    @Override
    public boolean isRowAvailable() {
        return list != null;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public Object getRowData() {
        return list.get(rowIndex);
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
        return list;
    }

    @Override
    public void setWrappedData(Object o) {
        this.list = (List) o;
    }
}
