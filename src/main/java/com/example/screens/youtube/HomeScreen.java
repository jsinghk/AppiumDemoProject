package com.example.screens.youtube;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.example.basepackage.BaseScreen;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.lang.model.element.Element;
import java.util.function.Function;

import static com.codeborne.selenide.Selenide.$;
import static com.example.utils.ScreenAction.pressEnter;
import static com.example.utils.ScreenAction.waitForScreenToLoad;

public class HomeScreen extends BaseScreen<HomeScreen> {

    private final By LOGO_FIELD = By.id("YouTube");
    private final By SEARCH_BUTTON = new MobileBy.ByAccessibilityId("Search");
    private final By SEARCH_TEXTBOX = By.className("android.widget.EditText");


    @Step
    public SearchScreen searchVideo(String videoName){
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(),30);
        //wait.until(ExpectedConditions.visibilityOf($(SEARCH_BUTTON)));
        $(SEARCH_BUTTON).shouldBe(Condition.visible).click();
        $(SEARCH_TEXTBOX).shouldBe(Condition.visible).sendKeys(videoName);
        pressEnter();
        Selenide.sleep(4000);
        //waitForScreenToLoad();
        return screen.getScreen(SearchScreen.class);
    }

}
