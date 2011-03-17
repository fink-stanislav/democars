package org.richfaces.democars.application;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SAXHandler<T> extends DefaultHandler {
    private String elementValue;
    private List<T> entityList;
    private Field[] fields;
    private T entity;
    private Class<T> entityClass;

    public SAXHandler(Class<T> clazz) {
        entityList = new ArrayList<T>(25);
        entityClass = clazz;
    }

    private void prepare() {
        fields = entityClass.getDeclaredFields();
    }

    private Field getField(String elementName) {
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("id") && field.getName().equalsIgnoreCase(elementName)) {
                return field;
            }
        }
        return null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("row")) {
            try {
                prepare();
                entity = entityClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("row")) {
            entityList.add(entity);
        } else {
            Field field = getField(qName);
            if (field != null) {
                try {
                    field.setAccessible(true);
                    field.set(entity, elementValue);
                } catch (IllegalAccessException e) {
                }
            }
        }
    }

    public List<T> getEntityList() {
        return entityList;
    }
}
