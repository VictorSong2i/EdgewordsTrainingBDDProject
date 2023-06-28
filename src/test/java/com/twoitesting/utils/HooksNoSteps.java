package com.twoitesting.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HooksNoSteps {

    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver(); //Initiate chrome
        driver.get("https://www.edgewordstraining.co.uk/demo-site/"); //Access shop webpage
        driver.manage().window().maximize();
    }
    @AfterEach //Quits driver after each test
    void tearDown () throws InterruptedException{
        Thread.sleep(4000); //Sleep 4 seconds for tester visibility
        driver.quit(); //Close Chrome and ChromeDriver
    }
}
