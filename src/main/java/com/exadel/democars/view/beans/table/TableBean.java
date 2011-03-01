package com.exadel.democars.view.beans.table;

import com.exadel.democars.model.entities.Car;
import com.exadel.democars.view.model.table.TableDataModel;
import com.exadel.democars.view.model.table.TableFilterSort;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import java.io.Serializable;

@ManagedBean(name = "tableBean")
@SessionScoped
public class TableBean implements Serializable {
    private TableDataModel<Car> tableModel;
    private Integer currentPage = 1;
    private Integer pageSize = 10;

    public TableBean() {
        tableModel = new TableDataModel<Car>(pageSize, currentPage);
    }

    public DataModel getCars() {
        tableModel.setCurrentPage(currentPage);
        tableModel.setPageSize(pageSize);
        tableModel.updateRows();
        return tableModel;
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

    public TableFilterSort getTableFilterSort() {
        return tableModel.getTableFilterSort();
    }
}