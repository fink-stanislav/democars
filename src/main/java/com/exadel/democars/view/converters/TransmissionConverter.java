package com.exadel.democars.view.converters;

import com.exadel.democars.beans.car.Transmission;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("TransmissionConverter")
public class TransmissionConverter implements Converter {
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return Transmission.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Transmission) {
            return ((Transmission) value).name().toLowerCase();
        }
        return "";
    }
}
