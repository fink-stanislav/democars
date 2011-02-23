package com.exadel.democars.view.model;

import java.util.HashMap;
import java.util.Map;

public class TableFilter<T> {
    private TableDataModel tableDataModel;
    private Map<String, String> filterParams;

    private String make = "";
    private String model = "";
    private String price = "";

    public TableFilter(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
        this.filterParams = new HashMap<String, String>();
    }

    public Map<String, String> getFilterParams() {
        return filterParams;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void filter() {
        filterParams.put("model.make", make);
        filterParams.put("model.model", model);
        FilterableDataSource<T> filterableDataSource = new FilterableDataSource<T>(tableDataModel);
        filterableDataSource.setFilterParams(filterParams);
        tableDataModel.setCurrentDataSource(filterableDataSource);
    }
}
