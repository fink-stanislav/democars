package com.exadel.democars.view.model.datasource;

import com.exadel.democars.util.JpqlExpressionBuilder;
import com.exadel.democars.view.model.table.TableDataModel;

import java.util.HashMap;
import java.util.List;

public class FilterableSortableDataSource<T> extends JpqlDataSource<T> {
    private SortableDataSource sortableDataSource;
    private FilterableDataSource filterableDataSource;

    public FilterableSortableDataSource(SortableDataSource sortableDataSource,
                                        FilterableDataSource filterableDataSource) {
        this.tableName = "Car";
        tableDataModel = sortableDataSource.tableDataModel;
        this.pageSize = tableDataModel.getPageSize();
        this.currentPage = tableDataModel.getCurrentPage();
        this.tableAlias = "c";
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
