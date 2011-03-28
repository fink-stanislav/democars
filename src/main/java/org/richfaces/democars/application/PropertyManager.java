package org.richfaces.democars.application;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Manages application properties.
 * {@code PropertyManager} Loads them once and it become available from the whole application.
 *
 * @author S. Fink
 */
public class PropertyManager {
    private static PropertyManager instance = new PropertyManager("application.properties");
    private Properties properties;

    public static PropertyManager getPropertyManager() {
        return instance;
    }

    private PropertyManager(String resourceName) {
        try {
            URL resUrl = getClass().getClassLoader().getResource(resourceName);
            FileInputStream inputStream = new FileInputStream(resUrl.getFile());
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key.toLowerCase());
    }
}
