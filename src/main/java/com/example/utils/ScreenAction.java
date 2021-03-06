package com.example.utils;

import com.codeborne.selenide.WebDriverRunner;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class ScreenAction {
    private static AppiumDriver driver = (AppiumDriver) WebDriverRunner.getWebDriver();

    public static void waitForScreenToLoad() {
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public static void pressEnter() {
        driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
    }

    public static void scrollToElement(String elementPath) {
        if (driver instanceof AndroidDriver) {
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(10)" +
                            ".scrollIntoView(new UiSelector().descriptionContains(\"" + elementPath + "\"))"));
        }
    }

    public static void tapElement(String element) {
        WebElement webElement = driver.findElement(By.xpath(element));
        TouchAction action = new TouchAction(driver);
        action.tap(ElementOption.element(webElement)).perform();
    }

    public static void tapAtLocation(int coX, int coY) {
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(coX, coY)).perform();
    }

    public static void swipeToLocation(int startX, int startY, int endX, int endY) {
        TouchAction action = new TouchAction(driver);
        action.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
    }
}
