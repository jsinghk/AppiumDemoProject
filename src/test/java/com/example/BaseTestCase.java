package com.example;

import com.example.basepackage.ScreenRegistry;

public abstract class BaseTestCase {

    protected ScreenRegistry screenRegistry;

    protected BaseTestCase() {
        screenRegistry = ScreenRegistry.getInstance();
    }
}
