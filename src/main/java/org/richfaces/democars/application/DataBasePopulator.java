package org.richfaces.democars.application;

import org.richfaces.democars.model.entities.*;
import org.richfaces.democars.model.persistence.DataManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class DataBasePopulator {
    private DataManager dataManager;
    private Map<String, URL> xmlUrls;
    private Map<String, Class<?>> classes;

    public DataBasePopulator() throws IOException {
        dataManager = new DataManager();
        xmlUrls = new HashMap<String, URL>();
        classes = new HashMap<String, Class<?>>();
        ClassLoader cs = getClass().getClassLoader();

        xmlUrls.put("address", cs.getResource("data/address.xml"));
        xmlUrls.put("car", cs.getResource("data/car.xml"));
        xmlUrls.put("features", cs.getResource("data/features.xml"));
        xmlUrls.put("individualseller", cs.getResource("data/individualseller.xml"));
        xmlUrls.put("legalseller", cs.getResource("data/legalseller.xml"));
        xmlUrls.put("model", cs.getResource("data/model.xml"));
        xmlUrls.put("seller", cs.getResource("data/seller.xml"));

        classes.put("address", Address.class);
        classes.put("car", Car.class);
        classes.put("features", Features.class);
        classes.put("individualseller", IndividualSeller.class);
        classes.put("legalseller", LegalSeller.class);
        classes.put("model", Seller.class);
        classes.put("seller", Model.class);
    }

    public <T> List<T> parse(URL xmlUrl, Class<T> entityClass) throws Exception {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        SAXHandler<T> handler = new SAXHandler<T>(entityClass);
        sp.parse(xmlUrl.toString(), handler);
        return handler.getEntityList();
    }

    public void build() throws Exception {
        URL url0 = xmlUrls.get("address");
        Class<Address> clazz0 = (Class<Address>) classes.get("address");
        List<Address> list0 = parse(url0, clazz0);

        URL url1 = xmlUrls.get("features");
        Class<Address> clazz1 = (Class<Address>) classes.get("features");
        List<Address> list1 = parse(url1, clazz1);

        URL url2 = xmlUrls.get("individualseller");
        Class<Address> clazz2 = (Class<Address>) classes.get("individualseller");
        List<Address> list2 = parse(url2, clazz2);

        URL url3 = xmlUrls.get("legalseller");
        Class<Address> clazz3 = (Class<Address>) classes.get("legalseller");
        List<Address> list3 = parse(url3, clazz3);

        URL url4 = xmlUrls.get("model");
        Class<Address> clazz4 = (Class<Address>) classes.get("model");
        List<Address> list4 = parse(url4, clazz4);

        URL url5 = xmlUrls.get("seller");
        Class<Address> clazz5 = (Class<Address>) classes.get("seller");
        List<Address> list5 = parse(url5, clazz5);

        URL url6 = xmlUrls.get("car");
        Class<Address> clazz6 = (Class<Address>) classes.get("car");
        List<Address> list6 = parse(url6, clazz6);
    }
}
