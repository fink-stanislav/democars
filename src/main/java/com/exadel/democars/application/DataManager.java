package com.exadel.democars.application;

import com.exadel.democars.beans.car.Car;
import com.exadel.democars.beans.car.Features;
import com.exadel.democars.beans.car.Model;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public List getCarList() {
        List cars = new ArrayList();
        Car car = new Car();
        car.setFeatures(new Features());
        car.setModel(new Model());
        car.getModel().setMake("Ford");
        cars.add(car);
        return cars;
    }
}
