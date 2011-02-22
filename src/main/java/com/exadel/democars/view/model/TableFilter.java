package com.exadel.democars.view.model;

import org.apache.commons.lang.StringUtils;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class TableFilter<T> {
    private TableDataModel tableDataModel;
    private String makeValue;
    private String modelValue;
    private String priceValue;

    public TableFilter(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
    }

    public void setMakeValue(String makeValue) {
        this.makeValue = makeValue;
        makeFilter("model.make");
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

    private boolean isFilterable() {
        if (isBlank(makeValue) && isBlank(modelValue) && isBlank(priceValue)) {
            DefaultDataSource defaultDataSource = new DefaultDataSource("allCars",
                    tableDataModel.getPageSize(), tableDataModel.getCurrentPage());
            defaultDataSource.setDataManager(tableDataModel.getDataManager());
            tableDataModel.setCurrentDataSource(defaultDataSource);
            return false;
        }
        return true;
    }

    public void makeFilter(String columnName) {
        if (!isFilterable()) return;
        FilterableDataSource<T> filterableDataSource =
                new FilterableDataSource<T>("filtered", tableDataModel.getPageSize(), tableDataModel.getCurrentPage());
        filterableDataSource.setColumn(columnName);
        filterableDataSource.setExpression(makeValue);
        filterableDataSource.setDataManager(tableDataModel.getDataManager());
        tableDataModel.setCurrentDataSource(filterableDataSource);
    }
}
