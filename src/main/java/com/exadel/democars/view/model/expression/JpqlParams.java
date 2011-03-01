package com.exadel.democars.view.model.expression;

public class JpqlParams {
    protected String tableName;
    protected String tableAlias;

    public JpqlParams(String tableName, String tableAlias) {
        this.tableName = tableName;
        this.tableAlias = tableAlias;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
