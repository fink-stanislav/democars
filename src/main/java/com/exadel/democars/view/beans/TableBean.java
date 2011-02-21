package com.exadel.democars.view.beans;

import com.exadel.democars.model.entities.Car;
import com.exadel.democars.view.model.TableDataModel;
import com.exadel.democars.view.model.TableFilter;
import com.exadel.democars.view.model.TableSort;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import java.io.Serializable;

@ManagedBean(name = "tableBean")
@SessionScoped
public class TableBean implements Serializable {
    private TableDataModel<Car> tableModel;
    private TableFilter tableFilter;
    private TableSort tableSort;
    private Integer currentPage = 1;
    private Integer pageSize = 10;

    public TableBean() {
        tableModel = new TableDataModel<Car>(pageSize, currentPage);
        tableFilter = new TableFilter();
        tableSort = new TableSort();
    }

    public DataModel getCars() {
        tableModel.setPageNumber(currentPage);
        tableModel.updateRows();
        return tableModel;
    }

    public void sortRows() {
        tableModel.sortRows("model.make");
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public TableSort getTableSort() {
        return tableSort;
    }

    public TableFilter getTableFilter() {
        return tableFilter;
    }
}
