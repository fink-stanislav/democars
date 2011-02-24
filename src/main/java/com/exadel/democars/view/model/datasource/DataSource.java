package com.exadel.democars.view.model.datasource;

import java.util.List;

public interface DataSource<T> {
    List<T> updateRows();
}
