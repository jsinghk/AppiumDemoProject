package com.example.screens;

import com.codeborne.selenide.Condition;
import com.example.basepackage.BaseScreen;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginScreen extends BaseScreen<LoginScreen> {

    private final String USERNAME_FIELD = "test-Username";
    private final String PASSWORD_FIELD = "test-Password";
    private final String LOGIN_BUTTON = "test-LOGIN";

    public ProductHomeScreen login(String username, String password) {
        $(By.name(USERNAME_FIELD)).shouldBe(Condition.visible).sendKeys(username);
        $(By.name(PASSWORD_FIELD)).shouldBe(Condition.visible).sendKeys(password);
        $(By.name(LOGIN_BUTTON)).shouldBe(Condition.visible).click();
        return screen.getScreen(ProductHomeScreen.class);
    }
}