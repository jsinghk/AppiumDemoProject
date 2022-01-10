package com.example.basepackage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.example.utils.CapabilitiesLoader;
import com.example.webdriver.DriverProvider;
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
            driver = DriverProvider.provideDriver();
            WebDriverRunner.setWebDriver(driver);
        }
        if (!Optional.ofNullable(screens.get(screenClass)).isPresent()) {
            screens.put(screenClass, Selenide.page(screenClass));
        }

        return screenClass.cast(screens.get(screenClass));
    }

    public void quitSession() {
        if (instance != null) {
            log.info("Stopping the driver and service");
            DriverProvider.closeWebDriver();
        } else {
            throw new RuntimeException("Session has not yet started");
        }
    }
}