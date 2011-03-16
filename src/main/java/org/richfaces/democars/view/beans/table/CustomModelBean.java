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
        model.setCurrentPage(1);
        model.setPageRowCount(10);
    }

    public CustomTableModel getModel() {
        return model;
    }

    public Integer getCurrentPage() {
        return model.getCurrentPage();
    }

    public void setCurrentPage(Integer currentPage) {
        model.setCurrentPage(currentPage);
    }

    public Integer getPageRowCount() {
        return model.getPageRowCount();
    }

    public void setPageRowCount(Integer pageRowCount) {
        model.setPageRowCount(pageRowCount);
    }
}
