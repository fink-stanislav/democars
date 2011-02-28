package com.exadel.democars.view.model.datasource;

public abstract class JpqlDataSource<T> extends PageableDataSource<T> {

    public String getTableName() {
        return tableDataModel.getDbTableName();
    }

    public String getTableAlias() {
        return tableDataModel.getDbTableAlias();
    }
}
