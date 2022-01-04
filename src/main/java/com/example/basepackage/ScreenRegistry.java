package com.example.basepackage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.example.utils.CapabilitiesLoader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class ScreenRegistry {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static ScreenRegistry instance;
    private Map<Class<?>, BaseScreen<?>> screens = new LinkedHashMap<>();
    private CapabilitiesLoader capabilities = CapabilitiesLoader.getInstance();
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
            try {
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                driver = new AppiumDriver<>(url, capabilities.setCapabilities());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            WebDriverRunner.setWebDriver(driver);
        }
        if (!Optional.ofNullable(screens.get(screenClass)).isPresent()) {
            screens.put(screenClass, Selenide.page(screenClass));
        }

        return screenClass.cast(screens.get(screenClass));
    }
}
