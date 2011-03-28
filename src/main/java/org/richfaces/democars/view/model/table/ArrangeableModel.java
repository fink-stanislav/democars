package org.richfaces.democars.view.model.table;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.component.SortOrder;
import org.richfaces.democars.model.entities.Identifiable;
import org.richfaces.democars.model.persistence.DataFacade;
import org.richfaces.democars.model.persistence.DataRetrievalInterface;
import org.richfaces.model.ArrangeableState;
import org.richfaces.renderkit.Expression;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model implementation for table.
 * Uses {@code DataRetrievalInterface} for data access.
 *
 * @param <T> entity class.
 * @author S. Fink
 */
public class ArrangeableModel<T extends Identifiable> extends AbstractArrangeableModel<T> {
    private Map<Integer, T> cachedRows;
    private Integer rowKey;
    private DataRetrievalInterface<T> data;

    public ArrangeableModel(DataRetrievalInterface<T> data) {
        this.data = data;
        cachedRows = new HashMap<Integer, T>();
    }

    /**
     * updates filter and sort parameters
     */
    public void arrange(FacesContext context, ArrangeableState state) {
        if (state != null) {
            data.applyFilterParams(state.getFilterFields());
            data.applySortParams(state.getSortFields());
        }
    }

    /**
     * Gets specified range of rows, caches it and process.
     * Caching is not necessary.
     * {@code visitor.process(...)} method is important - it forces framework to obtain row data by keys.
     */
    @Override
    public void walk(FacesContext facesContext, DataVisitor visitor, Range range, Object argument) {
        SequenceRange sequenceRange = (SequenceRange) range;

        List<T> items = data.getArrangedList(sequenceRange);

        for (T item : items) {
            cachedRows.put(item.getId(), item);
            visitor.process(facesContext, item.getId(), argument);
        }
    }

    @Override
    public void setRowKey(Object key) {
        rowKey = (Integer) key;
    }

    @Override
    public boolean isRowAvailable() {
        return rowKey != null && cachedRows.get(rowKey) != null;
    }

    /**
     * @return row data obtained by key from cache or persistent storage.
     */
    @Override
    public T getRowData() {
        T rowItem = null;
        if (rowKey != null) {
            rowItem = cachedRows.get(rowKey);
            if (rowItem == null) {
                rowItem = data.getItemByKey(rowKey);
                cachedRows.put(rowKey, rowItem);
                return rowItem;
            }
            return rowItem;
        }
        return rowItem;
    }

    /**
     * @return total row amount
     */
    @Override
    public int getRowCount() {
        return data.getRowCount();
    }
}
