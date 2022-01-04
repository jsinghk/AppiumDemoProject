package com.example.basepackage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.example.utils.CapabilitiesLoader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.utils.PropertiesLoader.getProperty;
import static java.lang.Integer.parseInt;

public class ScreenRegistry {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static ScreenRegistry instance;
    private Map<Class<?>, BaseScreen<?>> screens = new LinkedHashMap<>();
    private CapabilitiesLoader capabilities = CapabilitiesLoader.getInstance();
    private final String APPIUM_SERVER_HOST = getProperty("appium.server.host");
    private final int APPIUM_SERVER_PORT = parseInt(getProperty("appium.server.port"));
    private final String APPIUM_SERVER_PATH = getProperty("appium.server.path");
    WebDriver driver;

    public static ScreenRegistry getInstance() {
        if (instance == null) {
            instance = new ScreenRegistry();
        }
        return instance;
    }

    public <T extends BaseScreen<?>> T getScreen(Class<T> screenClass) {
        if (!WebDriverRunner.hasWebDriverStarted()) {
            screens.clear();
            setWebDriver();
        }
        if (!Optional.ofNullable(screens.get(screenClass)).isPresent()) {
            screens.put(screenClass, Selenide.page(screenClass));
        }

        return screenClass.cast(screens.get(screenClass));
    }

    public void setWebDriver() {
        try {
            URL url = new URL("http", APPIUM_SERVER_HOST, APPIUM_SERVER_PORT, APPIUM_SERVER_PATH);
            log.info("Setting the server url : " + url);
            driver = new AppiumDriver<>(url, capabilities.setCapabilities());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        WebDriverRunner.setWebDriver(driver);
    }
}