package com.exadel.democars.view.model.datasource;

import com.exadel.democars.util.JpqlExpressionBuilder;

public abstract class JpqlDataSource<T> extends PageableDataSource<T> {

    public String getTableName() {
        return tableDataModel.getDbTableName();
    }

    public String getTableAlias() {
        return tableDataModel.getDbTableAlias();
    }

    public void updateRows() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(this);
        builder.buildSelectExpression();
        builder.append(evaluateExpression());
        tableDataModel.setWrappedData(tableDataModel.getDataManager().executeQuery(builder, this));
    }

    public abstract String evaluateExpression();
}
