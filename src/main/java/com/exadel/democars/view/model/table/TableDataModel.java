package com.exadel.democars.view.model.table;

import com.exadel.democars.model.persistence.DataManager;
import com.exadel.democars.view.model.expression.JpqlParams;
import com.exadel.democars.view.model.expression.PaginationParams;

import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;

public class TableDataModel<T> extends DataModel<T> implements Serializable {
    private DataManager dataManager;
    private Integer rowIndex = 0;
    private List<T> rows;
    private PaginationParams paginationParams;
    private TableFilterSort tableFilterSort;

    public TableDataModel(Integer pageSize, Integer currentPage) {
        paginationParams = new PaginationParams(pageSize, currentPage);
        JpqlParams jpqlParams = new JpqlParams("Car", "c");
        dataManager = new DataManager();
        tableFilterSort = new TableFilterSort(paginationParams, jpqlParams);
    }

    public void updateRows() {
        String expression = tableFilterSort.buildFilterSortExpression();
        setWrappedData(dataManager.executeQuery(expression, paginationParams));
    }

    @Override
    public boolean isRowAvailable() {
        if (rows.size() <= getRowIndex()) {
            return false;
        }
        return rows.size() > 0 && rows.get(getRowIndex()) != null;
    }

    /**
     * Returns number of rows, corresponding to evaluated expression. In case of sorting,
     * number of obtained rows is equal to total number of rows in database table.
     *
     * @return total number of columns in database table
     */
    @Override
    public int getRowCount() {
        return dataManager.getRowCount();
    }

    @Override
    public T getRowData() {
        return rows.get(getRowIndex());
    }

    @Override
    public int getRowIndex() {
        return rowIndex % paginationParams.getPageSize();
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public Object getWrappedData() {
        return rows;
    }

    @Override
    public void setWrappedData(Object rows) {
        this.rows = (List<T>) rows;
    }

    public void setCurrentPage(Integer currentPage) {
        paginationParams.setCurrentPage(currentPage);
    }

    public Integer getCurrentPage() {
        return paginationParams.getCurrentPage();
    }

    public Integer getPageSize() {
        return paginationParams.getPageSize();
    }

    public void setPageSize(Integer pageSize) {
        paginationParams.setPageSize(pageSize);
    }

    public TableFilterSort getTableFilterSort() {
        return tableFilterSort;
    }
}
