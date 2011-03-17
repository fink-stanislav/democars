package org.richfaces.democars.application;

import org.richfaces.democars.model.entities.Address;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AddressHandler extends DefaultHandler {
    private Address address;
    private String elementValue;
    private List<Address> addressList;

    public AddressHandler() {
        addressList = new ArrayList<Address>(25);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("row")) {
            address = new Address();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("row")) {
            addressList.add(address);
        } else if (qName.equalsIgnoreCase("city")) {
            address.setCity(elementValue);
        } else if (qName.equalsIgnoreCase("street")) {
            address.setStreet(elementValue);
        }
    }

    public List<Address> getAddressList() {
        return addressList;
    }
}
