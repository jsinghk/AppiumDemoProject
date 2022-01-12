package com.example.screens.youtube;

import com.codeborne.selenide.Condition;
import com.example.basepackage.BaseScreen;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.example.utils.ScreenAction.*;
import static java.lang.String.format;

public class SearchScreen extends BaseScreen<SearchScreen> {

    private final String VIDEO_DESCRIPTION = "//android.view.ViewGroup[contains(@content-desc,'%s')]";

    @Step
    public String getVideoDescription(String text) {
        scrollToElement(text);
        String videoDescription = $(By.xpath(format(VIDEO_DESCRIPTION, text))).shouldBe(Condition.visible).getAttribute("content-desc");
        return videoDescription;
    }

    @Step
    public VideoPlayerScreen playVideo(String text) {
        tapElement(format(VIDEO_DESCRIPTION, text));
        sleep(5000);
        return screen.getScreen(VideoPlayerScreen.class);
    }

}
