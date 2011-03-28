package org.richfaces.democars.view.beans.table;

import org.richfaces.component.SortOrder;
import org.richfaces.democars.model.entities.Car;
import org.richfaces.democars.model.persistence.DataFacade;
import org.richfaces.democars.view.model.table.ArrangeableModel;
import org.richfaces.model.Filter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "arrangeableModelBean")
@SessionScoped
public class ArrangeableModelBean implements Serializable {
    private ArrangeableModel<Car> model;

    private String makeFilterValue;
    private String modelFilterValue;
    private Double priceFilterValue;

    private SortOrder makeSortOrder;
    private SortOrder modelSortOrder;
    private SortOrder priceSortOrder;

    public ArrangeableModelBean() {
        model = new ArrangeableModel<Car>(new DataFacade<Car>(Car.class));
    }

    public ArrangeableModel<Car> getModel() {
        return model;
    }

    public String getMakeFilterValue() {
        return makeFilterValue;
    }

    public void setMakeFilterValue(String makeFilterValue) {
        this.makeFilterValue = makeFilterValue;
    }

    public String getModelFilterValue() {
        return modelFilterValue;
    }

    public void setModelFilterValue(String modelFilterValue) {
        this.modelFilterValue = modelFilterValue;
    }

    public Double getPriceFilterValue() {
        return priceFilterValue;
    }

    public void setPriceFilterValue(Double priceFilterValue) {
        this.priceFilterValue = priceFilterValue;
    }

    public SortOrder getMakeSortOrder() {
        return makeSortOrder;
    }

    public void setMakeSortOrder(SortOrder makeSortOrder) {
        this.makeSortOrder = makeSortOrder;
    }

    public SortOrder getModelSortOrder() {
        return modelSortOrder;
    }

    public void setModelSortOrder(SortOrder modelSortOrder) {
        this.modelSortOrder = modelSortOrder;
    }

    public SortOrder getPriceSortOrder() {
        return priceSortOrder;
    }

    public void setPriceSortOrder(SortOrder priceSortOrder) {
        this.priceSortOrder = priceSortOrder;
    }
}
