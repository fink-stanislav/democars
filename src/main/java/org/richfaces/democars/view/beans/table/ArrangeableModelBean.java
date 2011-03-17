package org.richfaces.democars.view.beans.table;

import org.richfaces.democars.model.entities.Car;
import org.richfaces.democars.view.model.table.ArrangeableModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "arrangeableModelBean")
@SessionScoped
public class ArrangeableModelBean implements Serializable {
    private ArrangeableModel<Car> model;
    private Integer currentPage;
    private Integer pageRowCount;

    public ArrangeableModelBean() {
        model = new ArrangeableModel<Car>();
        model.setCurrentPage(1);
        model.setPageRowCount(10);
    }

    public ArrangeableModel<Car> getModel() {
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
