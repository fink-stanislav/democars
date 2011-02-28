package com.exadel.democars.view.converters;

import com.exadel.democars.beans.car.Engine;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("EngineConverter")
public class EngineConverter implements Converter {
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return Engine.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Engine) {
            return ((Engine) value).name().toLowerCase();
        }
        return "";
    }
}
