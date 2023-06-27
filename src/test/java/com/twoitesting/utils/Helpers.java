package com.twoitesting.utils;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Helpers {
    WebDriver driver;

    public Helpers(WebDriver driver) {
        this.driver = driver; //Initiate driver after being called itself
    }

    public WebElement waitForElementAndText(int timeOutInSec, By locator, String text) {
        //Function waits for element and checks if the text matches before continuing, otherwise throw error
        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSec));
        mywait.until(drv -> {
            try {
                WebElement heading = drv.findElement(locator);
                if (heading.getText().equals(text)) {
                    return heading;
                } else {
                    return null;
                }
            } catch (NoSuchElementException e) {
                return null;
            }
        });
        return null;
    }

    public Helpers scroll(WebDriver driver, WebElement element) {
        //Scroll down the page till the element is found
        JavascriptExecutor js = (JavascriptExecutor) driver; //initiate js driver for js script
        js.executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public void waitForClickableButton(By locator, Duration timeOutInSec){
        //Wait for button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void TakePageScreenshot(String fileName) {
        //Take page screenshot
        TakesScreenshot ssdriver = (TakesScreenshot) driver;
        File pageScreenshot = ssdriver.getScreenshotAs(OutputType.FILE); //Store screenshot as file in memory

        try {
            //Commit the file to disk
            FileHandler.copy(pageScreenshot, new File("D:\\Screenshots\\" + fileName + ".png"));
        } catch (IOException e) {
            System.out.printf("Failed to write screenshot"); //Output error message
        }
    }

    public void TakeWebElementScreenshot(String fileName, By locator) {
        //Take screenshot of Web element
        TakesScreenshot ssdriver = (TakesScreenshot) driver.findElement(locator);
        File pageScreenshot = ssdriver.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(pageScreenshot, new File("D:\\Screenshots\\" + fileName + ".png"));
        } catch (IOException e) {
            System.out.printf("Failed to write screenshot");
        }
    }
}


//    public Helpers clickAction(WebElement element){ //Click on element
//        element.click();
//        return this;
//    }
