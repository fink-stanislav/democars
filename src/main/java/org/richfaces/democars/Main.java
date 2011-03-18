package org.richfaces.democars;

import org.richfaces.democars.application.DataBasePopulator;

public class Main {
    public static void main(String[] args) {
        DataBasePopulator populator = new DataBasePopulator();
        try {
            populator.populate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
