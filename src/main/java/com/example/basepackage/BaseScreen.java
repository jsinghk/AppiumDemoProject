package com.example.basepackage;

public abstract class BaseScreen<T extends BaseScreen<?>> {
    protected ScreenRegistry screen = ScreenRegistry.getInstance();
}
