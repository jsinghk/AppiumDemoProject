package com.example.screens.youtube;

import com.example.basepackage.BaseScreen;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.sleep;
import static com.example.utils.AdbCommandExecutor.*;
import static com.example.utils.ScreenAction.swipeToLocation;
import static com.example.utils.ScreenAction.tapAtLocation;
import static java.lang.Integer.parseInt;

public class VideoPlayerScreen extends BaseScreen<VideoPlayerScreen> {

    @Step
    public VideoPlayerScreen pauseOrPlayVideo() {
        executeKeyevent("85");
        sleep(5000);
        return screen.getScreen(VideoPlayerScreen.class);
    }

    @Step
    public String getVideoPlaybackState(String playbackInfo) {
        String playbackStateInfo = getMediaSessionInfo("PlaybackState").split("PlaybackState")[1];
        String value = playbackStateInfo.split(playbackInfo + "=")[1].split(",")[0];
        return value;
    }

    @Step
    public VideoPlayerScreen increaseVolume(int count) {
        for (int i = 0; i < count; i++) {
            executeKeyevent("24");
        }
        return screen.getScreen(VideoPlayerScreen.class);
    }

    @Step
    public VideoPlayerScreen decreaseVolume(int count) {
        for (int i = 0; i < count; i++) {
            executeKeyevent("25");
        }
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

    @Step
    public int getScreenLayout() {
        String screenOrientationInfo = getScreenOrientation("SurfaceOrientation");
        int screenOrientation = parseInt(screenOrientationInfo.split(":")[1].trim());
        return screenOrientation;
    }
}
