package com.exadel.democars.view.model;

import com.exadel.democars.model.entities.Car;
import org.richfaces.model.Filter;

public class TableFilter {
    private String makeValue;
    private String modelValue;
    private String priceValue;

    private Filter<?> makeFilter;
    private Filter<?> modelFilter;
    private Filter<?> priceFilter;

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
