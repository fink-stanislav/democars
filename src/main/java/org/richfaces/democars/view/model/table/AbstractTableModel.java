package org.richfaces.democars.view.model.table;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.democars.model.persistence.DataManager;
import org.richfaces.democars.model.util.JpqlExpressionBuilder;
import org.richfaces.democars.view.model.expression.JpqlParams;
import org.richfaces.democars.view.model.expression.PaginationParams;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;

import javax.faces.context.FacesContext;
import java.util.List;

public abstract class AbstractTableModel<T> extends ExtendedDataModel<T> implements Arrangeable {

    private List<T> cachedItems;

    private List<FilterField> filterFields;

    private List<SortField> sortFields;

    private SequenceRange cachedRange;

    private String rowKey;

    private Integer rowIndex;

    private DataManager dataManager;
    private JpqlParams jpqlParams;
    private Integer currentPage;
    private Integer pageRowCount;

    protected AbstractTableModel(Class<T> entityClass) {
        super();
        dataManager = new DataManager();
        jpqlParams = new JpqlParams("Car", "c");
    }

    @Override
    public void setRowKey(Object key) {
        this.rowKey = (String) key;
    }

    @Override
    public Object getRowKey() {
        return rowKey;
    }

    @Override
    public boolean isRowAvailable() {
        return cachedItems != null &&
                cachedItems.size() > getRowIndex() &&
                cachedItems.size() > 0 &&
                cachedItems.get(getRowIndex()) != null;
    }

    @Override
    public int getRowCount() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);
        return dataManager.executeQuery(builder.buildCountExpression("").getExpression());
    }

    @Override
    public T getRowData() {
        return cachedItems.get(getRowIndex());
    }

    public void arrange(FacesContext facesContext, ArrangeableState arrangeableState) {
        filter();
        sort();
    }

    private void filter() {
    }

    private void sort() {
    }

    @Override
    public void walk(FacesContext facesContext, DataVisitor visitor, Range range, Object argument) {
        SequenceRange sequenceRange = (SequenceRange) range;

        if (this.cachedItems == null || !areEqualRanges(this.cachedRange, sequenceRange)) {

            // update filter and sort settings

            if (sequenceRange != null) {
                int first = sequenceRange.getFirstRow();
                int rows = sequenceRange.getRows();
            }

            this.cachedRange = sequenceRange;

            JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);
            builder.buildSelectExpression();
            this.cachedItems = dataManager.executeQuery(builder.getExpression(),
                    new PaginationParams(pageRowCount, currentPage));
        }
    }

    private boolean areEqualRanges(SequenceRange range1, SequenceRange range2) {
        if (range1 == null || range2 == null) {
            return range1 == null && range2 == null;
        } else {
            return range1.getFirstRow() == range2.getFirstRow() && range1.getRows() == range2.getRows();
        }
    }

    public Integer getPageRowCount() {
        return pageRowCount;
    }

    public void setPageRowCount(Integer pageRowCount) {
        this.pageRowCount = pageRowCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * unused
     */
    @Override
    public Object getWrappedData() {
        return null;
    }

    @Override
    public void setWrappedData(Object data) {
    }

    @Override
    public int getRowIndex() {
        return rowIndex % 10;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }
}
