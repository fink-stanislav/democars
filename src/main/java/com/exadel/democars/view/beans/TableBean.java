package com.exadel.democars.view.beans;

import com.exadel.democars.model.entities.Car;
import com.exadel.democars.view.model.TableDataModel;
import org.richfaces.model.Filter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.exadel.democars.model.persistence.DataManager.getEntityListByNamedQuery;

@ManagedBean(name = "tableBean")
@SessionScoped
public class TableBean implements Serializable {
    private final String asc = "ascending";
    private final String desc = "descending";
    private String direction;

    private Map<String, String> rowValue;
    private Filter<?> makeFilter;
    private Filter<?> modelFilter;
    private Filter<?> priceFilter;

    public DataModel getCars() {
        List list = getEntityListByNamedQuery("findAllCars");
        return new TableDataModel(list, 10, list.size());
    }

    public TableBean() {
        this.direction = asc;
        rowValue = new HashMap<String, String>();
    }

    public void changeDirection() {
        setDirection(direction.equals(asc) ? desc : asc);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Map<String, String> getRowValue() {
        return rowValue;
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

    public Filter<?> getMakeFilter() {
        return new Filter<Car>() {
            public boolean accept(Car car) {
                return filterString(rowValue.get("make"), car.getModel().getMake());
            }
        };
    }

    public Filter<?> getModelFilter() {
        return new Filter<Car>() {
            public boolean accept(Car car) {
                return filterString(rowValue.get("model"), car.getModel().getModel());
            }
        };
    }

    public Filter<?> getPriceFilter() {
        return new Filter<Car>() {
            public boolean accept(Car car) {
                return filterNumber(rowValue.get("price"), car.getPrice());
            }
        };
    }
}
