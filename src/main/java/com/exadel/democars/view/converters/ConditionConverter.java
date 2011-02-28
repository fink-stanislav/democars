package com.exadel.democars.view.converters;

import com.exadel.democars.beans.car.Condition;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter("ConditionConverter")
public class ConditionConverter extends EnumConverter {
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return Condition.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Condition) {
            return ((Condition) value).name().toLowerCase();
        }
        return "";
    }
}
