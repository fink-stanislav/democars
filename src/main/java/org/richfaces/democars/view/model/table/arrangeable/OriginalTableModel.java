package org.richfaces.democars.view.model.table.arrangeable;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.richfaces.democars.model.persistence.DataManager;
import org.richfaces.democars.model.util.JpqlExpressionBuilder;
import org.richfaces.democars.view.model.expression.DefaultExpression;
import org.richfaces.democars.view.model.expression.JpqlParams;
import org.richfaces.democars.view.model.expression.PaginationParams;

import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import java.util.List;

public class OriginalTableModel<T> extends ExtendedDataModel<T> {
    private List<T> rows;
    private Integer rowCount = 0;
    private Integer pageSize = 10;
    private Integer rowIndex = 0;

    public List getRows() {
        JpqlParams jpqlParams = new JpqlParams("Car", "c");
        JpqlExpressionBuilder builder = new JpqlExpressionBuilder(jpqlParams);
        DataManager dataManager = new DataManager();
        PaginationParams paginationParams = new PaginationParams();
        return dataManager.executeQuery(builder.buildSelectExpression().getExpression(), paginationParams);
    }

    public boolean isRowAvailable() {
        if (rows.size() <= getRowIndex()) {
            return false;
        }
        return rows.size() > 0 && rows.get(getRowIndex()) != null;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getRowIndex() {
        return rowIndex % pageSize;
    }

    public T getRowData() {
        return rows.get(getRowIndex());
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

    @Override
    public void setRowKey(Object key) {
    }

    @Override
    public Object getRowKey() {
        return null;
    }

    @Override
    public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) {
    }
}
