package com.exadel.democars.view.model.datasource;

import com.exadel.democars.util.JpqlExpressionBuilder;
import com.exadel.democars.view.model.table.TableDataModel;

import java.util.List;

public class DefaultDataSource<T> extends JpqlDataSource<T> {

    public DefaultDataSource(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
    }

    public String evaluateExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(this);
        builder.buildSelectExpression();
        return builder.getExpression();
    }

    public void updateRows() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(this);
        builder.append(evaluateExpression());
        tableDataModel.setWrappedData(tableDataModel.getDataManager().executeQuery(builder, this));
    }

    public Integer rowCount() {
        return tableDataModel.getDataManager().getRowCount();
    }
}
