package com.example.webdriver;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.example.utils.CapabilitiesLoader;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.utils.PropertiesLoader.getProperty;

public class DriverProvider {
    private static Logger log = LoggerFactory.getLogger(DriverProvider.class);
    private DesiredCapabilities desiredCapabilities;
    private static AppiumDriverLocalService service;
    private static DriverProvider driverProvider;
    private static WebDriver driver;

    private DriverProvider() {
        createDriver();
    }

    public static WebDriver provideDriver() {
        if (driverProvider == null) {
            driverProvider = new DriverProvider();
        }
        return driver;
    }

    private void createDriver() {
        String platform = getProperty("mobile.platform").toUpperCase();
        service = AppiumServerManager.getService();
        desiredCapabilities = CapabilitiesLoader.getInstance().setCapabilities();
        log.info("Creating new driver for : " + platform);
        switch (platform) {
            case "ANDROID":
                driver = new AndroidDriver<MobileElement>(service, desiredCapabilities);
                break;
            case "IOS":
                driver = new IOSDriver<MobileElement>(service, desiredCapabilities);
                break;
            case "WINDOWS":
                driver = new WindowsDriver<MobileElement>(service, desiredCapabilities);
                break;
            default:
                throw new RuntimeException("Mobile Platform provided does not match any existing platform");
        }
    }

    public static void closeWebDriver() {
        if (driverProvider != null) {
            WebDriverRunner.closeWebDriver();
            service.stop();
        } else {
            throw new RuntimeException("Webdriver has not yet started");
        }
    }
}
