package org.richfaces.democars.view.beans.table;

import org.richfaces.democars.view.model.table.CustomTableModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "customModelBean")
@SessionScoped
public class CustomModelBean implements Serializable {
    private CustomTableModel model;
    private Integer currentPage;
    private Integer pageRowCount;

    public CustomModelBean() {
        model = new CustomTableModel();
        currentPage = 1;
        pageRowCount = 10;
    }

    public CustomTableModel getModel() {
        return model;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageRowCount() {
        return pageRowCount;
    }

    public void setPageRowCount(Integer pageRowCount) {
        this.pageRowCount = pageRowCount;
    }
}
