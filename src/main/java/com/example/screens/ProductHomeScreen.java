package com.example.screens;

import com.codeborne.selenide.Condition;
import com.example.basepackage.BaseScreen;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductHomeScreen extends BaseScreen<ProductHomeScreen> {

    private final String SCREEN_TITLE = "PRODUCTS";

    public String getScreenTitle() {
        return $(By.name(SCREEN_TITLE)).shouldBe(Condition.visible).getText();
    }

}