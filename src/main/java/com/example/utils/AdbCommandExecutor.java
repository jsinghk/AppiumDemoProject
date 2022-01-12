package com.example.utils;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumDriver;

import java.util.*;

public class AdbCommandExecutor {

    private static AppiumDriver driver = (AppiumDriver) WebDriverRunner.getWebDriver();
    private static Map<String, Object> scriptArgs;

    public static void executeKeyevent(String keyevent) {
        scriptArgs = new LinkedHashMap<>();
        scriptArgs.put("command", "input");
        scriptArgs.put("args", Arrays.asList(new String[]{"keyevent", keyevent}));
        driver.executeScript("mobile:shell", scriptArgs);
    }

    public static void swipeToPosition(int startX, int startY, int endX, int endY) {
        scriptArgs = new LinkedHashMap<>();
        scriptArgs.put("command", "input");
        scriptArgs.put("args", Arrays.asList(new String[]{"swipe", String.valueOf(startX), String.valueOf(startY),
                String.valueOf(endX), String.valueOf(endY), "1500"}));
        driver.executeScript("mobile:shell", scriptArgs);
    }

    public static void tapAtPosition(String coX, String coY) {
        scriptArgs = new LinkedHashMap<>();
        scriptArgs.put("command", "input");
        scriptArgs.put("args", Arrays.asList(new String[]{"tap", coX, coY}));
        driver.executeScript("mobile:shell", scriptArgs);
    }

    /*
    First disable accelerometer rotation.
    orientation value can be 0 -> Portrait
                             1 -> Landscape
                             2 -> Portrait Reversed
                             3 -> Landscape Reversed
    */
    public static void changeOrientation(String orientation) {
        setAccelerometerRotation("0");
        scriptArgs = new LinkedHashMap<>();
        scriptArgs.put("command", "settings");
        scriptArgs.put("args", Arrays.asList(new String[]{"put", "system", "user_rotation", orientation}));
        driver.executeScript("mobile:shell", scriptArgs);
    }

    /*
    enabled value can be 0 -> disable accelerometer rotation
                         1 -> enable accelerometer rotation
    */
    public static void setAccelerometerRotation(String enabled) {
        scriptArgs = new LinkedHashMap<>();
        scriptArgs.put("command", "settings");
        scriptArgs.put("args", Arrays.asList(new String[]{"put", "system", "accelerometer_rotation", enabled}));
        driver.executeScript("mobile:shell", scriptArgs);
    }

    public static String getMediaSessionInfo(String grepArgument) {
        scriptArgs = new LinkedHashMap<>();
        scriptArgs.put("command", "dumpsys");
        scriptArgs.put("args", Arrays.asList(new String[]{"media_session", "|", "grep", grepArgument}));
        String info = (String) driver.executeScript("mobile:shell", scriptArgs);
        return info;
    }
}
