package com.exadel.democars.view.model.datasource;

import com.exadel.democars.util.JpqlExpressionBuilder;
import com.exadel.democars.view.model.table.TableDataModel;

import java.util.List;

public class DefaultDataSource<T> extends JpqlDataSource<T> {

    public DefaultDataSource(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
        this.pageSize = tableDataModel.getPageSize();
        this.currentPage = tableDataModel.getCurrentPage();
        this.tableName = "Car";
        this.tableAlias = "c";
    }

    public JpqlExpressionBuilder evaluateExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(this);
        builder.buildSelectExpression();
        return builder;
    }

    public List<T> updateRows() {
        return tableDataModel.getDataManager().executeQuery(evaluateExpression(), this);
    }

    public Integer rowCount() {
        return tableDataModel.getDataManager().getRowCount();
    }
}
