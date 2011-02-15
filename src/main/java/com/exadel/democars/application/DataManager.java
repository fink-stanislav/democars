package com.exadel.democars.application;

import com.exadel.democars.beans.car.CarBean;
import com.exadel.democars.beans.car.FeaturesBean;
import com.exadel.democars.beans.car.ModelBean;
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
            FeaturesBean features = new FeaturesBean();
            ModelBean model = new ModelBean();

            model.setMake(entity.getModel().getMake());
            model.setModel(entity.getModel().getModel());

            features.setBodyType(entity.getFeatures().getBodyType());
            features.setEngine(entity.getFeatures().getEngine());
            features.setFuel(entity.getFeatures().getFuel());
            features.setTransmission(entity.getFeatures().getTransmission());
            features.setExteriorColor(entity.getFeatures().getExteriorColor());
            features.setInteriorColor(entity.getFeatures().getInteriorColor());

            car.setMileage(entity.getMileage());
            car.setPrice(entity.getPrice());
            car.setCondition(entity.getCondition());
            car.setVin(entity.getVin());
            car.setSeller(entity.getSeller());

            car.setModel(model);
            car.setFeatures(features);
            cars.add(car);
        }
        return cars;
    }
}
