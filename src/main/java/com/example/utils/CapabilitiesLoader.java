package com.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static com.example.utils.PropertiesLoader.getProperty;

public class CapabilitiesLoader {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static CapabilitiesLoader instance = null;
    private Map<String, String> jsonMap = new HashMap<>();
    private static DesiredCapabilities desiredCapabilities = null;

    private CapabilitiesLoader(String jsonFile) {
        try {
            loadCapabilities("capabilities/" + jsonFile + ".json");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static CapabilitiesLoader getInstance() {
        if (instance == null) {
            instance = new CapabilitiesLoader(getProperty("mobile.platform"));
            desiredCapabilities = new DesiredCapabilities();
        }
        return instance;
    }

    private void loadCapabilities(String capFile) throws IOException {
        log.info("Reading capabilities file : " + capFile);
        InputStream inputStream = CapabilitiesLoader.class.getClassLoader().getResourceAsStream(capFile);
        if (inputStream == null) {
            throw new IOException("Unable to open stream for resource " + capFile);
        } else {
            ObjectMapper mapper = new ObjectMapper();
            jsonMap.putAll(mapper.readValue(inputStream, Map.class));
        }
    }

    public DesiredCapabilities setCapabilities() {
        String app = jsonMap.get("app");
        if (app != null) {
            setAppPath(app);
        }
        for (Map.Entry<String, String> capability : jsonMap.entrySet()) {
            desiredCapabilities.setCapability(capability.getKey(), capability.getValue());
        }
        return desiredCapabilities;
    }

    private void setAppPath(String app) {
        String appPath = Paths.get("src/main/resources/app").toAbsolutePath() + "/" + app;
        log.info("Setting the app path : " + appPath);
        jsonMap.put("app", appPath);
    }

}