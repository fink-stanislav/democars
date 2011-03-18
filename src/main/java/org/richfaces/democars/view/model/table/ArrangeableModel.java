package org.richfaces.democars.view.model.table;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.democars.application.PropertyManager;
import org.richfaces.democars.model.params.EntityParams;
import org.richfaces.democars.model.persistence.DataManager;
import org.richfaces.democars.model.util.JpqlExpressionBuilder;
import org.richfaces.democars.view.model.Identifiable;
import org.richfaces.democars.model.expression.DataRetrievalExpression;
import org.richfaces.democars.model.expression.DefaultExpression;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;

import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrangeableModel<T extends Identifiable> extends ExtendedDataModel<T> implements Arrangeable {

    private SequenceRange cachedRange;

    private Map<Integer, T> cachedRows;

    private T rowItem;

    private Class<T> itemClass;

    private Integer rowKey; // item id

    private DataManager<T> dataManager;

    private DataRetrievalExpression expression;

    private EntityParams persistenceParams;

    public ArrangeableModel(Class<T> itemClass) {
        dataManager = new DataManager<T>(itemClass);
        persistenceParams = new EntityParams(
                PropertyManager.getPropertyManager().getProperty("entity.name"),
                PropertyManager.getPropertyManager().getProperty("entity.alias")
        );
        expression = new DefaultExpression(persistenceParams);
        cachedRows = new HashMap<Integer, T>();
        this.itemClass = itemClass;
    }

    @Override
    public void setRowKey(Object key) {
        rowKey = (Integer) key;
    }

    @Override
    public Object getRowKey() {
        return rowKey;
    }

    @Override
    public boolean isRowAvailable() {
        return rowKey != null && cachedRows.get(rowKey) != null;
    }

    @Override
    public T getRowData() {
        if (rowKey != null) {
            T result = cachedRows.get(rowKey);
            if (result == null) {
                result = dataManager.getEntityById(rowKey, itemClass);
                cachedRows.put(rowKey, result);
                return result;
            } else {
                return result;
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
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(persistenceParams);
        return dataManager.executeQuery(builder.buildCountExpression(expression.evaluate()).getExpression());
    }

    /**
     * updates filter and sort parameters, modifies table data in appropriate way
     */
    public void arrange(FacesContext context, ArrangeableState state) {
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
        SequenceRange sequenceRange = (SequenceRange) range;

        int firstRow = sequenceRange.getFirstRow();
        int numberOfRows = sequenceRange.getRows();

        List<T> items = dataManager.executeQuery(builder.getExpression(), firstRow, numberOfRows);

        for (T item : items) {
            cachedRows.put(item.getId(), item);
            visitor.process(facesContext, item.getId(), argument);
        }
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

    @Override
    public int getRowIndex() {
        return -1;
    }

    @Override
    public void setRowIndex(int rowIndex) {
    }
}
