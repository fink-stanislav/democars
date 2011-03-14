package org.richfaces.democars.view.beans.table;

import org.richfaces.democars.model.entities.Car;
import org.richfaces.democars.model.persistence.DataManager;
import org.richfaces.democars.model.util.JpqlExpressionBuilder;
import org.richfaces.democars.view.model.expression.JpqlParams;
import org.richfaces.democars.view.model.expression.PaginationParams;
import org.richfaces.democars.view.model.table.ArrangeableTableModel;
import org.richfaces.democars.view.model.table.OriginalTableModel;
import org.richfaces.model.ArrangeableStateDefaultImpl;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "arrangeableTableBean")
@SessionScoped
public class ArrangeableTableBean implements Serializable {
    private ArrangeableTableModel tableModel;

    private Integer pageSize = 10;
    private Integer currentPage = 1;

    public ArrangeableTableBean() {
        tableModel = new ArrangeableTableModel(new OriginalTableModel<Car>(), null, null);
        DataManager dataManager = new DataManager();
        tableModel.setWrappedData(dataManager.executeQuery(
                new JpqlExpressionBuilder(new JpqlParams("Car", "c"))
                        .buildSelectExpression().getExpression(),
                new PaginationParams(10, 1)));
    }

    public DataModel getModel() {
        return tableModel.getOriginalModel();
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void filter() {
    }

    public void sort() {
    }

    public boolean isUnsorted(String key) {
        return true;
    }

    public boolean isAscending(String key) {
        return false;
    }

    public boolean isDescending(String key) {
        return false;
    }

    public void sortAscending(String key) {
    }

    public void sortDescending(String key) {
    }

    public void makeUnsorted(String key) {
    }
}