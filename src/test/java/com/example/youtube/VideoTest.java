package com.example.youtube;

import com.example.BaseTestCase;
import com.example.screens.youtube.HomeScreen;
import com.example.screens.youtube.SearchScreen;
import com.example.screens.youtube.VideoPlayerScreen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static com.example.utils.PropertiesLoader.getProperty;
import static java.lang.Integer.parseInt;

public class VideoTest extends BaseTestCase {

    private final String VIDEO_SEARCH_STRING = getProperty("video.search.string");
    private final String VIDEO_CONTENT_DESCRIPTION = getProperty("video.content.description");
    private final String PLAYBACK_PLAYING_STATE = getProperty("video.play.state");
    private final String PLAYBACK_PAUSE_STATE = getProperty("video.pause.state");
    private final int SCREEN_LAYOUT_PORTRAIT = parseInt(getProperty("screen.layout.portrait"));
    private final int SCREEN_LAYOUT_LANDSCAPE = parseInt(getProperty("screen.layout.landscape"));

    @Test
    public void validateVideoSearchAndScroll() {
        String videoDescription = screenRegistry.getScreen(HomeScreen.class)
                .searchVideo(VIDEO_SEARCH_STRING)
                .getVideoDescription(VIDEO_CONTENT_DESCRIPTION);
        Assert.assertTrue(videoDescription.contains(VIDEO_CONTENT_DESCRIPTION));
    }

    @Test(dependsOnMethods = {"validateVideoSearchAndScroll"})
    public void validateVideoPlay() {
        String playbackState = screenRegistry.getScreen(SearchScreen.class)
                .playVideo(VIDEO_CONTENT_DESCRIPTION)
                .getVideoPlaybackState("state");
        Assert.assertEquals(playbackState, PLAYBACK_PLAYING_STATE);
    }

    @Test(dependsOnMethods = {"validateVideoPlay"})
    public void validateVideoPause() {
        String playbackState = screenRegistry.getScreen(VideoPlayerScreen.class)
                .pauseOrPlayVideo()
                .getVideoPlaybackState("state");
        Assert.assertEquals(playbackState, PLAYBACK_PAUSE_STATE);
    }

    @Test(dependsOnMethods = {"validateVideoPause"})
    public void validateForwardVideoWithSlider() {
        String initialPos = screenRegistry.getScreen(VideoPlayerScreen.class)
                .getVideoPlaybackState("position");

        String finalPos = screenRegistry.getScreen(VideoPlayerScreen.class)
                .swipeVideoSliderToPosition(38, 701, 442, 701)
                .getVideoPlaybackState("position");

        Assert.assertNotEquals(finalPos, initialPos);
    }

    @Test(dependsOnMethods = {"validateForwardVideoWithSlider"})
    public void validateMaximizeAndMinimizeVideo() {
        int screenLayoutMax = screenRegistry.getScreen(VideoPlayerScreen.class)
                .tapOnScreenToShowPlayerOptions()
                .maximizeVideo()
                .getScreenLayout();
        Assert.assertEquals(screenLayoutMax, SCREEN_LAYOUT_LANDSCAPE);

        int screenLayoutMin = screenRegistry.getScreen(VideoPlayerScreen.class)
                .tapOnScreenToShowPlayerOptions()
                .minimizeVideo()
                .getScreenLayout();
        Assert.assertEquals(screenLayoutMin, SCREEN_LAYOUT_PORTRAIT);
    }

    @Test(dependsOnMethods = {"validateMaximizeAndMinimizeVideo"})
    public void validateMaximizeAndMinimizeVideoByScreenRotation() {
        int screenLayoutMax = screenRegistry.getScreen(VideoPlayerScreen.class)
                .rotateScreen("1")
                .getScreenLayout();
        Assert.assertEquals(screenLayoutMax, SCREEN_LAYOUT_LANDSCAPE);

        int screenLayoutMin = screenRegistry.getScreen(VideoPlayerScreen.class)
                .rotateScreen("0")
                .getScreenLayout();
        Assert.assertEquals(screenLayoutMin, SCREEN_LAYOUT_PORTRAIT);
    }

    @AfterClass
    public void tearDown() {
        screenRegistry.quitSession();
    }
}
