package com.exadel.democars.view.model;

import com.exadel.democars.model.persistence.DataManager;

import java.util.List;

public interface DataSource<T> {
    List<T> updateRows();
    void setDataManager(DataManager dataManager);
}
