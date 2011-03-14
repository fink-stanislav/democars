package org.richfaces.democars.view.model.table;

import org.ajax4jsf.model.ExtendedDataModel;
import org.richfaces.democars.model.entities.Car;
import org.richfaces.model.ArrangeableModel;

public class ArrangeableTableModel extends ArrangeableModel {
    private ExtendedDataModel originalModel;

    public ArrangeableTableModel(ExtendedDataModel<?> originalModel, String var, String filterVar) {
        super(originalModel, var, filterVar);
        this.originalModel = originalModel;
    }

    public ExtendedDataModel getOriginalModel() {
        return originalModel;
    }
}
