package com.twoitesting.utils;

import org.openqa.selenium.WebDriver;

public class WebDriverWrapper {
    //This class validates object of type driver, if any other object type is passed then it will fail
    private WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
