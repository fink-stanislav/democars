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

    private Class<T> entityClass;

    private SequenceRange cachedRange;

    private T dataItem;

    protected AbstractTableModel(Class<T> entityClass) {
        super();
        this.entityClass = entityClass;
    }

    @Override
    public void setRowKey(Object key) {
        dataItem = cachedItems != null ? cachedItems.get(0) : null;
    }

    @Override
    public Object getRowKey() {
        return dataItem;
    }

    @Override
    public boolean isRowAvailable() {
        return dataItem != null;
    }

    @Override
    public int getRowCount() {
        return cachedItems != null ? cachedItems.size() : 0;
    }

    @Override
    public T getRowData() {
        return dataItem != null ? dataItem : null;
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

            PaginationParams paginationParams = new PaginationParams();

            if (sequenceRange != null) {
                int first = sequenceRange.getFirstRow();
                int rows = sequenceRange.getRows();

                // update pagination params
                paginationParams = convertToPaginationParams(sequenceRange);
            }

            this.cachedRange = sequenceRange;
            paginationParams = convertToPaginationParams(sequenceRange);

            DataManager dataManager = new DataManager();
            JpqlParams params = new JpqlParams("Car", "c");
            JpqlExpressionBuilder builder = new JpqlExpressionBuilder(params);
            builder.buildSelectExpression();
            this.cachedItems = dataManager.executeQuery(builder.getExpression(), paginationParams);
        }
    }

    private boolean areEqualRanges(SequenceRange range1, SequenceRange range2) {
        if (range1 == null || range2 == null) {
            return range1 == null && range2 == null;
        } else {
            return range1.getFirstRow() == range2.getFirstRow() && range1.getRows() == range2.getRows();
        }
    }

    private PaginationParams convertToPaginationParams(SequenceRange range) {
        PaginationParams result = new PaginationParams();
        if (range.getFirstRow() == 0) {
            result.setCurrentPage(1);
        }
        return result;
    }

    /**
     * unused
     *
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
        return -1;
    }

    @Override
    public void setRowIndex(int rowIndex) {
    }
}
