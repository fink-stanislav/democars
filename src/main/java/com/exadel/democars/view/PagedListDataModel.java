package com.exadel.democars.view;

import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;

public class PagedListDataModel extends DataModel implements Serializable {
    private List list;
    private int pagesize;
    private int rowIndex;
    private int totalListSize;

    public PagedListDataModel() {
        super();
    }

    public PagedListDataModel(List list, int pagesize, int totalListSize) {
        super();
        this.list = list;
        this.pagesize = pagesize;
        this.totalListSize = totalListSize;
    }

    @Override
    public boolean isRowAvailable() {
        return list != null;
    }

    @Override
    public int getRowCount() {
        return totalListSize;
    }

    @Override
    public Object getRowData() {
        return list.get(rowIndex);
    }

    @Override
    public int getRowIndex() {
        return rowIndex % pagesize;
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
