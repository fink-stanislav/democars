package com.exadel.democars.view.beans.table;

import com.exadel.democars.model.entities.Car;
import com.exadel.democars.view.model.expression.PaginationParams;
import com.exadel.democars.view.model.table.TableDataModel;
import org.richfaces.component.SortOrder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean for connecting {@link TableDataModel} and RichFaces data table
 *
 * @author S. Fink
 */

@ManagedBean(name = "tableBean")
@SessionScoped
public class TableBean implements Serializable {
    private TableDataModel<Car> tableModel;
    private PaginationParams paginationParams;
    private Map<String, SortOrder> sortParams;
    private Map<String, Object> filterParams;

    public TableBean() {
        paginationParams = new PaginationParams();
        tableModel = new TableDataModel<Car>(paginationParams);
        filterParams = new HashMap<String, Object>();
        sortParams = new HashMap<String, SortOrder>();
    }

    /**
     * Returns updated TableDataModel.
     *
     * @return DataModel
     */
    public DataModel getModel() {
        tableModel.setPaginationParams(paginationParams);
        tableModel.updateRows();
        return tableModel;
    }

    public void filter() {
        tableModel.setFilterParams(filterParams);
        tableModel.filter();
    }

    public void sort() {
        tableModel.setSortParams(sortParams);
        tableModel.sort();
    }

    public Integer getCurrentPage() {
        return paginationParams.getCurrentPage();
    }

    public void setCurrentPage(Integer currentPage) {
        paginationParams.setCurrentPage(currentPage);
    }

    public Integer getPageSize() {
        return paginationParams.getPageSize();
    }

    public void setPageSize(Integer pageSize) {
        paginationParams.setPageSize(pageSize);
    }

    public boolean isUnsorted(String key) {
        return SortOrder.unsorted == sortParams.get(key);
    }

    public boolean isAscending(String key) {
        return SortOrder.ascending == sortParams.get(key);
    }

    public boolean isDescending(String key) {
        return SortOrder.descending == sortParams.get(key);
    }

    public void sortAscending(String columnName) {
        sortParams.put(columnName, SortOrder.ascending);
        sort();
    }

    public void sortDescending(String columnName) {
        sortParams.put(columnName, SortOrder.descending);
        sort();
    }

    public void makeUnsorted(String columnName) {
        sortParams.put(columnName, SortOrder.unsorted);
        sort();
    }

    public Map<String, Object> getFilterParams() {
        return filterParams;
    }
}