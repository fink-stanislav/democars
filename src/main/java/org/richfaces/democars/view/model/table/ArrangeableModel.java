package org.richfaces.democars.view.model.table;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.democars.model.persistence.DataManager;
import org.richfaces.democars.model.util.JpqlExpressionBuilder;
import org.richfaces.democars.view.model.expression.DataRetrievalExpression;
import org.richfaces.democars.view.model.expression.DefaultExpression;
import org.richfaces.democars.view.model.expression.JpqlParams;
import org.richfaces.democars.view.model.expression.PaginationParams;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

public class ArrangeableModel<T> extends ExtendedDataModel<T> implements Arrangeable {

    private List<FilterField> filterFields;

    private List<SortField> sortFields;

    private SequenceRange cachedRange;

    private List<T> rows;

    private Object rowKey;

    private int rowIndex;

    private DataManager dataManager;

    private JpqlParams persistenceParams;

    private PaginationParams paginationParams;

    private DataRetrievalExpression expression;

    public ArrangeableModel() {
        dataManager = new DataManager();
        persistenceParams = new JpqlParams("Car", "c");
        paginationParams = new PaginationParams();
        expression = new DefaultExpression(persistenceParams);
    }

    /**
     * index to key
     */
    @Override
    public void setRowKey(Object key) {
        if (null == key) {
            setRowIndex(-1);
            rowKey = null;
        } else {
            setRowIndex(((Integer) key).intValue());
        }
    }

    @Override
    public Object getRowKey() {
        if (rowIndex < 0) {
            return null;
        }
        return rowKey;
    }

    /**
     * obtain index as usual
     */
    @Override
    public int getRowIndex() {
        return rowIndex % paginationParams.getPageSize();
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public boolean isRowAvailable() {
        if (getRowKey() == null) {
            return false;
        }
        if (rows.size() <= (Integer) getRowKey()) {
            return false;
        }
        if (rows.size() > 0 && rows.get((Integer) getRowKey()) != null) {
            return true;
        }
        return false;
    }

    @Override
    public T getRowData() {
        return rows.get((Integer) getRowKey());
    }

    /**
     * @return total row amount
     */
    @Override
    public int getRowCount() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(persistenceParams);
        return dataManager.executeQuery(builder.buildCountExpression(expression.evaluate()).getExpression());
    }

    /**
     * updates filter and sort parameters, modifies table data in appropriate way
     */
    public void arrange(FacesContext context, ArrangeableState state) {
        if (state != null) {
            filterFields = state.getFilterFields();
            sortFields = state.getSortFields();
        }
        filter();
        sort();
    }

    /**
     * build filter expression
     */
    private void filter() {
    }

    /**
     * build sort expression
     */
    private void sort() {
    }

    /**
     * process rows
     */
    @Override
    public void walk(FacesContext facesContext, DataVisitor visitor, Range range, Object argument) {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(persistenceParams);
        builder.buildSelectExpression();
        rows = dataManager.executeQuery(builder.getExpression(), paginationParams);
    }

    /**
     * @return number of rows per page
     */
    public Integer getPageRowCount() {
        return paginationParams.getPageSize();
    }

    public void setPageRowCount(Integer pageRowCount) {
        paginationParams.setPageSize(pageRowCount);
    }

    /**
     * @return number of current page
     */
    public Integer getCurrentPage() {
        return paginationParams.getCurrentPage();
    }

    public void setCurrentPage(Integer currentPage) {
        paginationParams.setCurrentPage(currentPage);
    }

    /**
     * obsolete methods
     */
    @Override
    public Object getWrappedData() {
        return null;
    }

    @Override
    public void setWrappedData(Object data) {
    }
}
