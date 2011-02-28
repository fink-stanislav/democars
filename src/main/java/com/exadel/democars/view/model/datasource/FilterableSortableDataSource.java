package com.exadel.democars.view.model.datasource;

import com.exadel.democars.util.JpqlExpressionBuilder;

import java.util.List;

public class FilterableSortableDataSource<T> extends JpqlDataSource<T> {
    private SortableDataSource sortableDataSource;
    private FilterableDataSource filterableDataSource;

    public FilterableSortableDataSource(SortableDataSource sortableDataSource,
                                        FilterableDataSource filterableDataSource) {
        tableDataModel = sortableDataSource.tableDataModel;
        this.sortableDataSource = sortableDataSource;
        this.filterableDataSource = filterableDataSource;
    }

    public String evaluateExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(this);
        builder.append(filterableDataSource.evaluateExpression());
        builder.append(sortableDataSource.evaluateExpression());
        return builder.getExpression();
    }

    public Integer rowCount() {
        return tableDataModel.getDataManager().getRowCount();
    }
}
