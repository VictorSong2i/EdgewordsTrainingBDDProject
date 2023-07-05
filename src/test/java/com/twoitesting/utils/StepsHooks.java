package com.twoitesting.utils;

import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepsHooks {

    private WebDriver driver; //Scope of driver
    private final SharedDictionary dict;
    private final WebDriverWrapper wdWrapper; //Initiate null wrapper variable

    public StepsHooks(SharedDictionary dict, WebDriverWrapper wdWrapper) { //Pico-container will init the SharedDictionary and web driver wrapper
        this.dict = dict; //take the Pico-container supplied dictionary and put it in this class's private field
        //this.driver = driver; //Driver used without wrapper
        this.driver = wdWrapper.getDriver(); //Uses wrapper to get driver
        this.wdWrapper = wdWrapper; //Initiate wrapper for this instance of hooks
    }

    //Cucumber Step tests
    @Before //Runs before every scenario
    public void setUp() {
        driver = new ChromeDriver(); //Initiate chrome
        wdWrapper.setDriver(driver); //Sets driver for use
        driver.get("https://www.edgewordstraining.co.uk/demo-site/"); //Access shop webpage
        dict.addDict("mydriver", driver);
        driver.manage().window().maximize(); //Maximise window
    }

    @After //Quits driver after each scenario
    public void tearDown() throws InterruptedException {
//        Thread.sleep(4000); //Sleep 4 seconds for tester visibility
        driver.quit();
    }
}