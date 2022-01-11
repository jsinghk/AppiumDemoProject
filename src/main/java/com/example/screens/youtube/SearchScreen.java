package com.example.screens.youtube;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.example.basepackage.BaseScreen;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.example.utils.AdbCommandExecutor.pauseOrResumeVideo;
import static com.example.utils.AdbCommandExecutor.swipeToPosition;
import static com.example.utils.ScreenAction.scrollToElement;
import static com.example.utils.ScreenAction.tapElement;
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
    public SearchScreen playVideo(String text) {
        tapElement(format(VIDEO_DESCRIPTION, text));
        Selenide.sleep(5000);
        return screen.getScreen(SearchScreen.class);
    }

    @Step
    public SearchScreen pauseAndPlayVideo() {
        pauseOrResumeVideo();
        Selenide.sleep(5000);
        pauseOrResumeVideo();
        Selenide.sleep(5000);
        return screen.getScreen(SearchScreen.class);
    }

    @Step
    public SearchScreen swipeVideoSliderToPosition(String startX, String startY, String endX, String endY) {
        swipeToPosition(startX, startY, endX, endY);
        sleep(5000);
        return screen.getScreen(SearchScreen.class);
    }
}
