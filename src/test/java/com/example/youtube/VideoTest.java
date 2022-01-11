package com.example.youtube;

import com.codeborne.selenide.Selenide;
import com.example.BaseTestCase;
import com.example.screens.youtube.HomeScreen;
import com.example.screens.youtube.SearchScreen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class VideoTest extends BaseTestCase {

    @Test
    public void validateVideoSearchAndScroll(){
        String videoDescription = screenRegistry.getScreen(HomeScreen.class)
                .searchVideo("ekam testvagrant")
                .getVideoDescription("Create First IOS Mobile Test");

        Assert.assertTrue(videoDescription.contains("IOS Mobile Test"));
    }

    @Test(dependsOnMethods = {"validateVideoSearchAndScroll"})
    public void validateVideoPlayAndPause(){
        screenRegistry.getScreen(SearchScreen.class)
                .playVideo("Create First IOS Mobile Test")
                .pauseAndPlayVideo()
                .swipeVideoSliderToPosition("38","701","442","701");
    }

    @AfterClass
    public void tearDown(){
        screenRegistry.quitSession();
    }
}
