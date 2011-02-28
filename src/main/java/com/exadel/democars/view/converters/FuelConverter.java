package com.exadel.democars.view.converters;

import com.exadel.democars.beans.car.Fuel;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("FuelConverter")
public class FuelConverter implements Converter {
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return Fuel.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Fuel) {
            return ((Fuel) value).name().toLowerCase();
        }
        return "";
    }
}
