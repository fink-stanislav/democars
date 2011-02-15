package com.exadel.democars.application;

import com.exadel.democars.beans.car.CarBean;
import com.exadel.democars.beans.car.Features;
import com.exadel.democars.beans.car.Model;
import com.exadel.democars.model.entities.Car;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.exadel.democars.model.persistence.EntityManagerProvider.getEntityManagerProvider;

public class DataManager {
    public List<CarBean> getCarList() {

        EntityManager entityManager = getEntityManagerProvider().getEntityManager();
        List<Car> entityList = entityManager.createNamedQuery("findAllCars").getResultList();

        List<CarBean> cars = new ArrayList<CarBean>();
        for (Car entity : entityList) {
            CarBean car = new CarBean();
            Features features = new Features();
            Model model = new Model();

            car.setMileage(entity.getMileage());
            car.setPrice(entity.getPrice());

            car.setModel(model);
            car.setFeatures(features);
            cars.add(car);
        }
        return cars;
    }
}
