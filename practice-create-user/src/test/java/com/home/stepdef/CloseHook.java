package com.home.stepdef;

import io.cucumber.java.After;

public class CloseHook {

    @After("@close")
    public void closeWebDriver() {
        TShirtsStepdefs.webDriver.quit();
    }
}
