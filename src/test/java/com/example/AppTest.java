package com.example;

import com.example.utils.CapabilitiesLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.net.MalformedURLException;
import java.net.URL;

public class AppTest {


    WebDriver driver;
    @BeforeClass
    public void setup(){
        DesiredCapabilities cap = CapabilitiesLoader.getInstance().setCapabilities();
        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AppiumDriver<>(url, cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws InterruptedException {
//        driver.get("https://www.google.com");
        Thread.sleep(10000);
        driver.findElement(By.name("test-Username")).sendKeys("standard_user");
        driver.findElement(By.name("test-Password")).sendKeys("secret_sauce");
        driver.findElement(By.name("test-LOGIN")).click();
        Thread.sleep(5000);
        String title = driver.findElement(By.name("PRODUCTS")).getText();
        Assert.assertEquals(title,"PRODUCTS");
    }
}
