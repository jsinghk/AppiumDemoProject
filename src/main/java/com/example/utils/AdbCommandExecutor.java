package com.example.utils;

import com.codeborne.selenide.WebDriverRunner;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;

import java.util.*;

public class AdbCommandExecutor {

    private static AppiumDriver driver = (AppiumDriver) WebDriverRunner.getWebDriver();

    public static void playPauseVideo(){
        Map<String,Object> scriptArgs = new LinkedHashMap<>();
        scriptArgs.put("command","input");
        scriptArgs.put("args", Arrays.asList(new String[]{"keyevent","85"}));
        driver.executeScript("mobile:shell", scriptArgs);
    }
}
