package org.richfaces.democars.view.model.table;

import org.ajax4jsf.model.ExtendedDataModel;
import org.richfaces.model.Arrangeable;

public abstract class AbstractArrangeableModel<T> extends ExtendedDataModel<T> implements Arrangeable {
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

    @Override
    public Object getRowKey() {
        return null;
    }
}
