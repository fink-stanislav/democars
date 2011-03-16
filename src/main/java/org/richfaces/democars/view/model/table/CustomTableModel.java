package org.richfaces.democars.view.model.table;

import org.richfaces.democars.model.entities.Car;

public class CustomTableModel extends AbstractTableModel<Car> {

    public CustomTableModel() {
        super(Car.class);
    }
}
