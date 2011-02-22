package com.exadel.democars.view.model;

import java.util.List;

public class FilterableDataSource<T> extends PageableDataSource<T> {
    private String queryName;
    private String column;
    private String expression;

    public FilterableDataSource(String queryName, Integer pageSize, Integer currentPage) {
        this.queryName = queryName;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<T> updateRows() {
        return dataManager.getRangedFilteredList(queryName, pageSize, currentPage, column, expression);
    }
}
