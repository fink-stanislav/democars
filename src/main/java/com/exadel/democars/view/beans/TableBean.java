package com.exadel.democars.view.beans;

import com.exadel.democars.model.entities.Car;
import com.exadel.democars.view.model.TableDataModel;
import org.richfaces.component.SortOrder;
import org.richfaces.model.Filter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ManagedBean(name = "tableBean")
@SessionScoped
public class TableBean implements Serializable {
    private Map<String, SortOrder> sortOrder;
    private String makeValue;
    private String modelValue;
    private String priceValue;

    private Filter<?> makeFilter;
    private Filter<?> modelFilter;
    private Filter<?> priceFilter;

    public TableBean() {
        sortOrder = new HashMap<String, SortOrder>();
        sortOrder.put("make", SortOrder.unsorted);
        sortOrder.put("model", SortOrder.unsorted);
        sortOrder.put("price", SortOrder.unsorted);
        sortOrder.put("condition", SortOrder.unsorted);
        sortOrder.put("mileage", SortOrder.unsorted);
        sortOrder.put("seller", SortOrder.unsorted);
    }

    public void setMakeValue(String makeValue) {
        this.makeValue = makeValue;
    }

    public void setModelValue(String modelValue) {
        this.modelValue = modelValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }

    public String getMakeValue() {
        return makeValue;
    }

    public String getModelValue() {
        return modelValue;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public Map<String, SortOrder> getSortOrder() {
        return sortOrder;
    }

    public DataModel getCars() {
        return new TableDataModel();
    }

    private void resetSortOrder(String key) {
        Set<Map.Entry<String, SortOrder>> entrySet = this.sortOrder.entrySet();
        for (Map.Entry<String, SortOrder> entry : entrySet) {
            if (!entry.getKey().equals(key)) {
                entry.setValue(SortOrder.unsorted);
            }
        }
    }

    public void changeSortOrder(String key) {
        resetSortOrder(key);
        if (sortOrder.get(key).equals(SortOrder.ascending)) {
            sortOrder.put(key, SortOrder.descending);
        } else {
            sortOrder.put(key, SortOrder.ascending);
        }
    }

    public Filter<?> getMakeFilter() {
        return new Filter<Car>() {
            public boolean accept(Car car) {
                return filterString(makeValue, car.getModel().getMake());
            }
        };
    }

    public Filter<?> getModelFilter() {
        return new Filter<Car>() {
            public boolean accept(Car car) {
                return filterString(modelValue, car.getModel().getModel());
            }
        };
    }

    public Filter<?> getPriceFilter() {
        return new Filter<Car>() {
            public boolean accept(Car car) {
                return filterNumber(priceValue, car.getPrice());
            }
        };
    }

    private boolean filterString(String filterValue, String valueToFilter) {
        return filterValue == null || valueToFilter.toUpperCase().startsWith(filterValue.toUpperCase());
    }

    private boolean filterNumber(String filterValue, Double valueToFilter) {
        try {
            return filterValue == null || Double.parseDouble(filterValue) >= valueToFilter;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
