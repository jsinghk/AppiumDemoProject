package com.example.webdriver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class AppiumServerManager {

    private static Logger log = LoggerFactory.getLogger(AppiumServerManager.class);
    private static AppiumDriverLocalService service;
    private static AppiumServerManager instance;

    private AppiumServerManager() {
        initAppiumServer();
        if (service == null) {
            throw new RuntimeException("Appium Service could not be started");
        }
    }

    public static AppiumDriverLocalService getService() {
        if (instance == null) {
            instance = new AppiumServerManager();
        }
        return service;
    }

    private static void initAppiumServer() {
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                .withLogFile(new File("target/appium.log"))
                .withStartUpTimeOut(60, TimeUnit.SECONDS));

        log.info("Appium service starting at : " + service.getUrl());
        service.start();
    }

}
