package org.richfaces.democars.model.persistence;

import org.ajax4jsf.model.SequenceRange;
import org.richfaces.component.SortOrder;
import org.richfaces.democars.application.PropertyManager;
import org.richfaces.democars.model.expression.*;
import org.richfaces.democars.model.params.EntityParams;
import org.richfaces.democars.model.util.JpqlExpressionBuilder;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.indexOf;
import static org.apache.commons.lang.StringUtils.substring;

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
        Map<String, SortOrder> sortParams = new HashMap<String, SortOrder>();
        if (!sortFields.isEmpty()) {
            for (SortField field : sortFields) {
                String sortParam = parseParam(field.getSortBy());
                SortOrder sortOrder = field.getSortOrder();
                sortParams.put(sortParam, sortOrder);
            }
        }
        sortExpression.setSortParams(sortParams);
    }

    public void applyFilterParams(List<FilterField> filterFields) {
        Map<String, Object> filterParams = new HashMap<String, Object>();
        if (!filterFields.isEmpty()) {
            for (FilterField field : filterFields) {
                Object filterValue = field.getFilterValue();
                String filterName = parseParam(field.getFilterExpression());
                if (filterValue != null) {
                    filterParams.put(filterName, filterValue);
                }
            }
        }
        filterExpression.setFilterParams(filterParams);
    }

    private String parseParam(ValueExpression expression) {
        String expressionString = expression.getExpressionString();
        int firstDot = indexOf(expressionString, ".");
        int closingCurlyBrace = indexOf(expressionString, "}");
        return substring(expressionString, firstDot + 1, closingCurlyBrace);
    }
}
