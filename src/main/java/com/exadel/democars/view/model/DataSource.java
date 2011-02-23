package com.exadel.democars.view.model;

import java.util.List;

public interface DataSource<T> {
    List<T> updateRows();
}
