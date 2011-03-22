package org.richfaces.democars.view.model.table;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.democars.model.entities.Identifiable;
import org.richfaces.democars.model.persistence.DataFacade;
import org.richfaces.democars.model.persistence.DataRetrievalInterface;
import org.richfaces.model.ArrangeableState;

import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrangeableModel<T extends Identifiable> extends AbstractArrangeableModel<T> {
    private SequenceRange cachedRange;
    private Map<Integer, T> cachedRows;
    private T rowItem;
    private Integer rowKey;
    private DataRetrievalInterface<T> data;

    public ArrangeableModel(Class<T> itemClass) {
        data = new DataFacade<T>(itemClass);
        cachedRows = new HashMap<Integer, T>();
    }

    /**
     * updates filter and sort parameters, modifies table data in appropriate way
     */
    public void arrange(FacesContext context, ArrangeableState state) {
        // data.applyFilterParams(state.getFilterFields());
        // data.applySortParams(state.getSortFields());
    }

    /**
     * process rows
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

    @Override
    public T getRowData() {
        if (rowKey != null) {
            rowItem = cachedRows.get(rowKey);
            if (rowItem == null) {
                rowItem = data.getItemByKey(rowKey);
                cachedRows.put(rowKey, rowItem);
                return rowItem;
            } else {
                return rowItem;
            }
        } else {
            return null;
        }
    }

    /**
     * @return total row amount
     */
    @Override
    public int getRowCount() {
        return data.getRowCount();
    }
}
