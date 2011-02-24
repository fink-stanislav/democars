package com.exadel.democars.view.model.datasource;

public abstract class JpqlDataSource<T> extends PageableDataSource<T> {
    protected String tableName;
    protected String tableAlias;

    public String getTableName() {
        return tableName;
    }

    public String getTableAlias() {
        return tableAlias;
    }
}
