package com.example.utils;

import org.slf4j.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PropertiesLoader {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static PropertiesLoader instance = null;
    private final Properties properties = new Properties();

    private PropertiesLoader() {
        try {
            this.loadProperties("properties/default.properties");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String propertyName, String defaultValue) {
        String valueFromProperties = getProperty(propertyName);
        return valueFromProperties != null ? valueFromProperties : defaultValue;
    }

    public static String getProperty(String propertyName) {
        if (instance == null) {
            instance = new PropertiesLoader();
        }
        return Optional.ofNullable(instance.properties.getProperty(propertyName)).orElse("");
    }

    private void loadProperties(String propFile) throws IOException {
        log.info("Reading config properties : " + propFile);
        InputStream inputStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(propFile);
        if (inputStream == null) {
            throw new IOException("Unable to open stream for resouce " + propFile);
        } else {
            Properties prop = new Properties();
            prop.load(inputStream);
            inputStream.close();
            Iterator<String> iterator = prop.stringPropertyNames().iterator();
            while (iterator.hasNext()) {
                String propertyName = iterator.next();
                if (propertyName.startsWith("+")) {
                    this.loadProperties("properties/" + propertyName.substring(1));
                }
            }
            this.properties.putAll(prop);
        }
    }
}