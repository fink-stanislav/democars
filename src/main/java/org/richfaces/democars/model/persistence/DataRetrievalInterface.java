package org.richfaces.democars.model.persistence;

import org.ajax4jsf.model.SequenceRange;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;

import javax.faces.context.FacesContext;
import java.util.List;

public interface DataRetrievalInterface<T> {
    int getRowCount();

    T getItemByKey(Object key);

    List<T> getArrangedList(SequenceRange range);

    void applySortParams(List<SortField> sortFields);

    void applyFilterParams(List<FilterField> filterFields);
}
