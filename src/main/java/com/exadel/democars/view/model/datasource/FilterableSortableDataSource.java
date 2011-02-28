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

    public Integer rowCount() {
        return tableDataModel.getDataManager().getRowCount();
    }

    public List<T> updateRows() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(this);
        builder.buildSelectExpression();
        builder.append(filterableDataSource.evaluateFilterExpression().getExpression());
        builder.append(sortableDataSource.evaluateSortExpression().getExpression());
        return tableDataModel.getDataManager().executeQuery(builder, this);
    }
}
