package org.richfaces.democars.model.persistence;

import org.ajax4jsf.model.SequenceRange;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;

import java.util.List;

/**
 * Interface for data access.
 * Might be implemented for any kind of persistent storage.
 *
 * @param <T> entity class.
 * @author S. Fink
 */
public interface DataRetrievalInterface<T> {
    /**
     * @return total row count
     */
    int getRowCount();

    /**
     * @param key for obtaining rows.
     * @return data row for specified key
     */
    T getItemByKey(Object key);

    /**
     * @param range row range. Specifies how many rows to obtain and which is first.
     * @return arranged list. (might be filtered and/or sorted or neither)
     */
    List<T> getArrangedList(SequenceRange range);

    /**
     * Converts {@code org.richfaces.model.SortField} to type applicable for underlying implementation
     * and initializes sorting params.
     *
     * @param sortFields list of fields to sort
     */
    void applySortParams(List<SortField> sortFields);

    /**
     * Converts {@code org.richfaces.model.FilterField} to type applicable for underlying implementation
     * and initializes filtering params.
     *
     * @param filterFields list of fields to filter
     */
    void applyFilterParams(List<FilterField> filterFields);
}
