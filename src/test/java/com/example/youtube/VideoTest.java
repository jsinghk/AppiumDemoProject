package com.example.youtube;

import com.example.BaseTestCase;
import com.example.screens.youtube.HomeScreen;
import com.example.screens.youtube.SearchScreen;
import com.example.screens.youtube.VideoPlayerScreen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static com.example.utils.PropertiesLoader.getProperty;

public class VideoTest extends BaseTestCase {

    private final String VIDEO_SEARCH_STRING = getProperty("video.search.string");
    private final String VIDEO_CONTENT_DESCRIPTION = getProperty("video.content.description");

    @Test
    public void validateVideoSearchAndScroll() {
        String videoDescription = screenRegistry.getScreen(HomeScreen.class)
                .searchVideo(VIDEO_SEARCH_STRING)
                .getVideoDescription(VIDEO_CONTENT_DESCRIPTION);

        Assert.assertTrue(videoDescription.contains(VIDEO_CONTENT_DESCRIPTION));
    }

    @Test(dependsOnMethods = {"validateVideoSearchAndScroll"})
    public void validateVideoPlayAndPause() {
        screenRegistry.getScreen(SearchScreen.class)
                .playVideo(VIDEO_CONTENT_DESCRIPTION)
                .pauseOrPlayVideo()
                .pauseOrPlayVideo();
    }

    @Test(dependsOnMethods = {"validateVideoPlayAndPause"})
    public void validateForwardVideoWithSlider() {
        screenRegistry.getScreen(VideoPlayerScreen.class)
                .swipeVideoSliderToPosition(38, 701, 442, 701);
    }

    @Test(dependsOnMethods = {"validateForwardVideoWithSlider"})
    public void validateMaximizeVideoOptions() {
        screenRegistry.getScreen(VideoPlayerScreen.class)
                .tapOnScreenToShowPlayerOptions()
                .maximizeVideo()
                .pauseOrPlayVideo()
                .minimizeVideo();
    }

    @Test(dependsOnMethods = {"validateMaximizeVideoOptions"})
    public void validateMaximizeAndMinimizeVideoByScreenRotation() {
        screenRegistry.getScreen(VideoPlayerScreen.class)
                .rotateScreen("1")
                .rotateScreen("0");
    }

    @AfterClass
    public void tearDown() {
        screenRegistry.quitSession();
    }
}
