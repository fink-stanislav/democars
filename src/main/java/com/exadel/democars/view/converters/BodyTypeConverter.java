package com.exadel.democars.view.converters;

import com.exadel.democars.beans.car.BodyType;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("BodyTypeConverter")
public class BodyTypeConverter implements Converter {
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return BodyType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof BodyType) {
            return ((BodyType) value).name().toLowerCase();
        }
        return "";
    }
}
