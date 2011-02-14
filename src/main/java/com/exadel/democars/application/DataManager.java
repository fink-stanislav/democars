package com.exadel.democars.application;

import com.exadel.democars.beans.Car;
import com.exadel.democars.beans.Features;
import com.exadel.democars.beans.Model;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public List getCarList() {
        List cars = new ArrayList();
        Car car = new Car();
        car.setFeatures(new Features());
        car.setModel(new Model());
        cars.add(car);
        return cars;
    }
}
