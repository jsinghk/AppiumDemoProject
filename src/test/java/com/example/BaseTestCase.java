package com.example;

import com.example.basepackage.ScreenRegistry;
import org.testng.annotations.AfterSuite;

public abstract class BaseTestCase {

    protected ScreenRegistry screenRegistry;

    protected BaseTestCase() {
        screenRegistry = ScreenRegistry.getInstance();
    }

}
