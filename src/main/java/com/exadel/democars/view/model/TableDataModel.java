package com.exadel.democars.view.model;

import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;

import static com.exadel.democars.model.persistence.DataManager.getEntityListByNamedQuery;
import static com.exadel.democars.model.persistence.DataManager.getRangedEntityListByNamedQuery;

public class TableDataModel extends DataModel implements Serializable {
    private List list;
    private int pageSize;
    private int rowIndex;

    public TableDataModel() {
        super();
        list = getEntityListByNamedQuery("findAllCars");
    }

    public TableDataModel(int currentPage, int pageSize) {
        super();
        this.pageSize = pageSize;
        list = getRangedEntityListByNamedQuery("findAllCars", currentPage, pageSize);
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
