package org.richfaces.democars.model.entities;

import org.richfaces.democars.model.enums.BodyType;
import org.richfaces.democars.model.enums.Engine;
import org.richfaces.democars.model.enums.Fuel;
import org.richfaces.democars.model.enums.Transmission;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Features implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    @Enumerated(EnumType.STRING)
    private Fuel fuel;
    @Enumerated(EnumType.STRING)
    private Engine engine;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private String exteriorColor;
    private String interiorColor;

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Body type: ").append(bodyType.toString().toLowerCase())
                .append(", Engine: ").append(engine.toString().toLowerCase())
                .append(", Fuel: ").append(fuel.toString().toLowerCase())
                .append(", Transmission: ").append(transmission.toString().toLowerCase())
                .append(", Exterior color: ").append(exteriorColor.toLowerCase())
                .append(" Interior color: ").append(interiorColor.toLowerCase());
        return sb.toString();
    }
}
