package com.twoitesting.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void scroll(WebDriver driver, WebElement element) {
        //Scroll down the page till the element is found
        JavascriptExecutor js = (JavascriptExecutor) driver; //initiate js driver for js script
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void waitForClickableButton(By locator, Duration timeOutInSec) {
        //Wait for button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForVisibleElement(By locator, Duration timeOutInSec) {
        //Wait for element to be visible
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

//    public void waitForAutoScroll(){ //Wait for autoscroll function, NOT IMPLEMENTED
//        WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.CSS_SELECTOR, 'main:not([style*="margin-top"])')))
//    }

//    public void TakePageScreenshot(String fileName) {
//        //Take page screenshot
//        TakesScreenshot ssdriver = (TakesScreenshot) driver;
//        File pageScreenshot = ssdriver.getScreenshotAs(OutputType.FILE); //Store screenshot as file in memory
//
//        try {
//            //Commit the file to disk
//            FileHandler.copy(pageScreenshot, new File("D:\\Screenshots\\" + fileName + ".png"));
//        } catch (IOException e) {
//            System.out.print("Failed to write screenshot"); //Output error message
//        }
//    }

    public void TakeWebElementScreenshot(String fileName, By locator) {
        //Take screenshot of Web element
        TakesScreenshot ssdriver = driver.findElement(locator);
        File pageScreenshot = ssdriver.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(pageScreenshot, new File("C:\\Users\\VictorSong\\Documents\\IntelliJ Project" +
                    "\\twoitesting\\target\\screenshots " + fileName + ".png"));
        } catch (IOException e) {
            System.out.println("Failed to write screenshot");
        }
    }
}


//    public Helpers clickAction(WebElement element){ //Click on element
//        element.click();
//        return this;
//    }
