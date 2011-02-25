package com.exadel.democars.view.model.datasource;

import com.exadel.democars.util.JpqlExpressionBuilder;
import com.exadel.democars.view.model.table.TableDataModel;
import org.richfaces.component.SortOrder;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SortableDataSource<T> extends JpqlDataSource<T> {
    private Map<String, SortOrder> sortParams;

    public SortableDataSource(TableDataModel tableDataModel) {
        this.tableDataModel = tableDataModel;
        this.tableName = "Car";
        this.pageSize = tableDataModel.getPageSize();
        this.currentPage = tableDataModel.getCurrentPage();
        this.tableAlias = "c";
    }

    public JpqlExpressionBuilder evaluateSortExpression() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(this);
        // builder.buildSelectExpression();

        Set<Map.Entry<String, SortOrder>> entrySet = sortParams.entrySet();
        if (entrySet.isEmpty()) {
            return builder;
        }

        builder.buildOrderByExpression();
        int counter = 0;
        for (Map.Entry<String, SortOrder> entry : entrySet) {
            counter++;
            if (entry.getValue() == SortOrder.ascending) {
                builder.addOrderParams(entry.getKey(), "asc");
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addComma();
                }
            } else if (entry.getValue() == SortOrder.descending) {
                builder.addOrderParams(entry.getKey(), "desc");
                if (builder.isRangeOk(entrySet.size(), counter)) {
                    builder.addComma();
                }
            }
        }
        return builder;
    }

    public List<T> updateRows() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(this);
        builder.buildSelectExpression();
        builder.append(evaluateSortExpression().getExpression());
        return tableDataModel.getDataManager().executeQuery(builder, this);
    }

    public Integer rowCount() {
        return tableDataModel.getDataManager().getRowCount();
    }

    public Map<String, SortOrder> getSortParams() {
        return sortParams;
    }

    public void setSortParams(Map<String, SortOrder> sortParams) {
        this.sortParams = sortParams;
    }
}
