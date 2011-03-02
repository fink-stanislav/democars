package com.exadel.democars.view.model.table;

import com.exadel.democars.model.persistence.DataManager;
import com.exadel.democars.util.JpqlExpressionBuilder;
import com.exadel.democars.view.model.expression.*;
import org.richfaces.component.SortOrder;

import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Implements {@link DataModel} for RichFaces dataTable
 *
 * @param <T> entity class
 * @author S. Fink
 */
public class TableDataModel<T> extends DataModel<T> implements Serializable {
    private DataManager dataManager;
    private Integer rowIndex = 0;
    private List<T> rows;
    private PaginationParams paginationParams;
    private JpqlParams jpqlParams;

    private FilterExpression filterExpression;
    private SortExpression sortExpression;

    public TableDataModel(PaginationParams paginationParams) {
        this.paginationParams = new PaginationParams(paginationParams);
        jpqlParams = new JpqlParams("Car", "c");
        dataManager = new DataManager();
        filterExpression = new FilterExpression(jpqlParams);
        sortExpression = new SortExpression(jpqlParams);
    }

    /**
     * Updates table rows. Evaluates data retrieval expression,
     * executes it and sets new value to the rows list.
     */
    public void updateRows() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);
        builder.buildSelectExpression();
        FilterSortExpression filterSortExpression =
                new FilterSortExpression(sortExpression, filterExpression);
        builder.append(filterSortExpression.evaluateExpression());
        rows = dataManager.executeQuery(builder.getExpression(), paginationParams);
    }

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
     * @return int row number of retrieved data
     */
    public int getRowCount() {
        return dataManager.getRowCount();
    }

    public T getRowData() {
        return rows.get(getRowIndex());
    }

    /**
     * Returns row index in range of pagination
     *
     * @return int row index
     */
    public int getRowIndex() {
        return rowIndex % paginationParams.getPageSize();
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public Object getWrappedData() {
        return rows;
    }

    public void setWrappedData(Object rows) {
        this.rows = (List<T>) rows;
    }

    public PaginationParams getPaginationParams() {
        return paginationParams;
    }

    public void setPaginationParams(PaginationParams paginationParams) {
        this.paginationParams = paginationParams;
    }

    public JpqlParams getJpqlParams() {
        return jpqlParams;
    }

    public void setJpqlParams(JpqlParams jpqlParams) {
        this.jpqlParams = jpqlParams;
    }

    /**
     * Sets sorting parameters
     *
     * @param sortParams Map&lt;String, SortOrder&gt;
     *                   map retrieved from bean associated with RichFaces dataTable
     */
    public void setSortParams(Map<String, SortOrder> sortParams) {
        sortExpression.setSortParams(sortParams);
    }

    /**
     * Sets filtering parameters
     *
     * @param filterParams Map&lt;String, Object&gt;
     *                     map retrieved from bean associated with RichFaces dataTable
     */
    public void setFilterParams(Map<String, Object> filterParams) {
        filterExpression.setFilterParams(filterParams);
    }
}
