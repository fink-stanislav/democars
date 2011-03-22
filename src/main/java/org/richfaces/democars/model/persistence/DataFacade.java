package org.richfaces.democars.model.persistence;

import org.ajax4jsf.model.SequenceRange;
import org.richfaces.democars.application.PropertyManager;
import org.richfaces.democars.model.expression.*;
import org.richfaces.democars.model.params.EntityParams;
import org.richfaces.democars.model.util.JpqlExpressionBuilder;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;

import java.util.List;

public class DataFacade<T> implements DataRetrievalInterface<T> {
    private DataManager<T> dataManager;
    private EntityParams persistenceParams;
    private FilterExpression filterExpression;
    private SortExpression sortExpression;
    private DataRetrievalExpression expression;
    private Class<T> itemClass;

    public DataFacade(Class<T> itemClass) {
        dataManager = new DataManager<T>(itemClass);
        persistenceParams = new EntityParams(
                PropertyManager.getPropertyManager().getProperty("entity.name"),
                PropertyManager.getPropertyManager().getProperty("entity.alias")
        );
        expression = new DefaultExpression(persistenceParams);
        sortExpression = new SortExpression(persistenceParams);
        filterExpression = new FilterExpression(persistenceParams);
        this.itemClass = itemClass;
    }

    public int getRowCount() {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(persistenceParams);
        return dataManager.executeQuery(builder.buildCountExpression(expression.evaluate()).getExpression());
    }

    public T getItemByKey(Object key) {
        return dataManager.getEntityById(key, itemClass);
    }

    public List<T> getArrangedList(SequenceRange range) {
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(persistenceParams);
        builder.buildSelectExpression();

        FilterSortExpression filterSortExpression =
                new FilterSortExpression(sortExpression, filterExpression);
        String dataRetrievalExpression = filterSortExpression.evaluate();
        builder.append(dataRetrievalExpression);

        expression = filterSortExpression;

        return dataManager.executeQuery(builder.getExpression(),
                range.getFirstRow(), range.getRows());
    }

    public void applySortParams(List<SortField> sortFields) {
    }

    public void applyFilterParams(List<FilterField> filterFields) {
    }
}
