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

    private Map<String, String> rowValues;
    private Filter<?> makeFilter;
    private Filter<?> modelFilter;
    private Filter<?> priceFilter;

    public DataModel getCars() {
        List list = getEntityListByNamedQuery("findAllCars");
        return new TableDataModel(list, 10, list.size());
    }

    public TableBean() {
        this.direction = asc;
        rowValues = new HashMap<String, String>();
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

    public Map<String, String> getRowValues() {
        return rowValues;
    }

    public Filter<?> getMakeFilter() {
        return new Filter<Car>() {
            public boolean accept(Car car) {
                return rowValues.get("make") == null ||
                        car.getModel().getMake().toUpperCase().startsWith(rowValues.get("make").toUpperCase());
            }
        };
    }

    public Filter<?> getModelFilter() {
        return new Filter<Car>() {
            public boolean accept(Car car) {
                return rowValues.get("model") == null ||
                        car.getModel().getModel().toUpperCase().startsWith(rowValues.get("model").toUpperCase());
            }
        };
    }

    public Filter<?> getPriceFilter() {
        return new Filter<Car>() {
            public boolean accept(Car car) {
                return true;
            }
        };
    }
}
