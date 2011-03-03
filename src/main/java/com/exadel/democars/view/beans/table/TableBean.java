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
     * Returns updated TableDataModel. It is called when view updates.
     *
     * @return data model for the view
     */
    public DataModel getModel() {
        tableModel.setPaginationParams(paginationParams);
        tableModel.updateRows();
        return tableModel;
    }

    /**
     * Updates {@link TableDataModel} filtering parameters.
     * After that {@code TableDataModel} view (RichFaces dataTable)
     * forces {@code TableDataModel} to update.
     * <p/>
     * Number of filtered data rows could be less than number of displayed rows. Hence filtered
     * rows may become invisible - their number stays below current page number multiplied by page size.
     * That's the reason to set current page number to one;
     */
    public void filter() {
        tableModel.setFilterParams(filterParams);
        paginationParams.setCurrentPage(1);
        tableModel.setPaginationParams(paginationParams);
    }

    /**
     * Updates {@link TableDataModel} sorting parameters.
     */
    public void sort() {
        tableModel.setSortParams(sortParams);
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

    /**
     * Checks if specified key is set to unsorted.
     * This method is used in sort menu in header of RichFaces dataTable
     *
     * @param key entity's property name
     * @return is corresponding key set to unsorted
     */
    public boolean isUnsorted(String key) {
        return SortOrder.unsorted == sortParams.get(key);
    }

    /**
     * Checks if specified key is set to ascending.
     * This method is used in sort menu in header of RichFaces dataTable
     *
     * @param key entity's property name
     * @return is corresponding key set to ascending
     */
    public boolean isAscending(String key) {
        return SortOrder.ascending == sortParams.get(key);
    }

    /**
     * Checks if specified key is set to descending.
     * This method is used in sort menu in header of RichFaces dataTable
     *
     * @param key entity's property name
     * @return is corresponding key set to descending
     */
    public boolean isDescending(String key) {
        return SortOrder.descending == sortParams.get(key);
    }

    /**
     * Sets appropriate sort order (ascending) to appropriate key
     * and updates sorting parameters
     *
     * @param key entity's property name;
     */
    public void sortAscending(String key) {
        sortParams.put(key, SortOrder.ascending);
        sort();
    }

    /**
     * Sets appropriate sort order (descending) to appropriate key
     * and updates sorting parameters
     *
     * @param key entity's property name;
     */
    public void sortDescending(String key) {
        sortParams.put(key, SortOrder.descending);
        sort();
    }

    /**
     * Sets appropriate sort order (unsorted) to appropriate key
     * and updates sorting parameters
     *
     * @param key entity's property name;
     */
    public void makeUnsorted(String key) {
        sortParams.put(key, SortOrder.unsorted);
        sort();
    }

    /**
     * Exposes map of filtering parameters to view
     *
     * @return map of filtering parameters
     */
    public Map<String, Object> getFilterParams() {
        return filterParams;
    }
}