package com.example.screens.youtube;

import com.example.basepackage.BaseScreen;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.sleep;
import static com.example.utils.AdbCommandExecutor.changeOrientation;
import static com.example.utils.AdbCommandExecutor.executeKeyevent;
import static com.example.utils.ScreenAction.swipeToLocation;
import static com.example.utils.ScreenAction.tapAtLocation;

public class VideoPlayerScreen extends BaseScreen<VideoPlayerScreen> {

    @Step
    public VideoPlayerScreen pauseOrPlayVideo() {
        executeKeyevent("85");
        sleep(5000);
        return screen.getScreen(VideoPlayerScreen.class);
    }

    @Step
    public VideoPlayerScreen swipeVideoSliderToPosition(int startX, int startY, int endX, int endY) {
        //swipeToPosition(startX, startY, endX, endY);         //with adb command
        swipeToLocation(startX, startY, endX, endY);        //with Touch Action command
        sleep(5000);
        return screen.getScreen(VideoPlayerScreen.class);
    }

    @Step
    public VideoPlayerScreen tapOnScreenToShowPlayerOptions() {
        //tapAtPosition("830", "510");        //with adb command
        tapAtLocation(830, 510);           //with Touch Action command
        sleep(300);
        return screen.getScreen(VideoPlayerScreen.class);
    }

    @Step
    public VideoPlayerScreen maximizeVideo() {
        //tapAtPosition("1014", "630");  //with adb command
        tapAtLocation(1014, 630);    //with Touch Action command
        sleep(5000);
        return screen.getScreen(VideoPlayerScreen.class);
    }

    @Step
    public VideoPlayerScreen minimizeVideo() {
        //tapAtPosition("2272", "887");      //with adb command
        tapAtLocation(2272, 887);        //with Touch Action command
        sleep(5000);
        return screen.getScreen(VideoPlayerScreen.class);
    }

    @Step
    public VideoPlayerScreen rotateScreen(String orientation) {
        changeOrientation(orientation);
        sleep(5000);
        return screen.getScreen(VideoPlayerScreen.class);
    }
}
