package org.richfaces.democars.view.beans.table;

import org.richfaces.component.SortOrder;
import org.richfaces.democars.model.entities.Car;
import org.richfaces.democars.view.model.expression.PaginationParams;
import org.richfaces.democars.view.model.table.arrangeable.ArrangeableTableModel;
import org.richfaces.democars.view.model.table.arrangeable.OriginalTableModel;
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
    private List<FilterField> filterFields;
    private List<SortField> sortFields;
    private Locale locale;

    private Integer pageSize = 10;
    private Integer currentPage = 1;

    public ArrangeableTableBean() {
        tableModel = new ArrangeableTableModel(new OriginalTableModel<Car>(), "", "");
        filterFields = new ArrayList<FilterField>();
        sortFields = new ArrayList<SortField>();
        locale = new Locale("en");
    }

    public DataModel getModel() {
        ArrangeableStateDefaultImpl stateDefault =
                new ArrangeableStateDefaultImpl(filterFields, sortFields, locale);
        tableModel.arrange(FacesContext.getCurrentInstance(), stateDefault);
        return tableModel;
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