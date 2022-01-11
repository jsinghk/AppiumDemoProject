package com.example.swaglabs;

import com.example.BaseTestCase;
import com.example.screens.swaglabs.LoginScreen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static com.example.utils.PropertiesLoader.getProperty;

public class LoginTest extends BaseTestCase {

    private String CORRECT_USERNAME = getProperty("correct.username");
    private String CORRECT_PASSWORD = getProperty("correct.password");

    @Test
    public void validateSuccessfulLogin() {

        String title = screenRegistry
                .getScreen(LoginScreen.class)
                .login(CORRECT_USERNAME,CORRECT_PASSWORD)
                .getScreenTitle();

        Assert.assertEquals(title,"PRODUCTS");
    }

    @AfterClass
    public void tearDown(){
        screenRegistry.quitSession();
    }
}