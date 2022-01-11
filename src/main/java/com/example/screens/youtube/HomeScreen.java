package com.example.screens.youtube;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.example.basepackage.BaseScreen;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.example.utils.ScreenAction.pressEnter;

public class HomeScreen extends BaseScreen<HomeScreen> {

    private final By LOGO_FIELD = By.id("YouTube");
    private final By SEARCH_BUTTON = new MobileBy.ByAccessibilityId("Search");
    private final By SEARCH_TEXTBOX = By.className("android.widget.EditText");


    @Step
    public SearchScreen searchVideo(String videoName){
        $(SEARCH_BUTTON).shouldBe(Condition.visible).click();
        $(SEARCH_TEXTBOX).shouldBe(Condition.visible).sendKeys(videoName);
        pressEnter();
        Selenide.sleep(4000);
        return screen.getScreen(SearchScreen.class);
    }

}
