package com.twoitesting.pompages.placeorder;

import com.twoitesting.utils.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BillingPOM {

    WebDriver driver;

    public BillingPOM(WebDriver driver) {
        this.driver = driver; //initiate driver after being called
        PageFactory.initElements(driver, this);
    }

    //Web elements in billing page
    @FindBy(id = "billing_first_name")
    WebElement firstNameIn;
    @FindBy(id = "billing_last_name")
    WebElement lastNameIn;
    @FindBy(id = "billing_address_1")
    WebElement streetAddress1In;
    @FindBy(id = "billing_city")
    WebElement cityIn;
    @FindBy(id = "billing_postcode")
    WebElement postcodeIn;
    @FindBy(id="billing_phone")
    WebElement phoneIn;
    @FindBy(id="billing_email")
    WebElement emailIn;
    @FindBy(css="[for=\"payment_method_cheque\"]")
    WebElement checkPaymentsIn;
    @FindBy(id="place_order")
    WebElement placeOrderIn;

    public BillingPOM sendBillingDetails(String firstName, String lastName, String streetAddress,
                                         String city, String postcode, String phone,String email){
        //input billing details in text boxes after clearing

        firstNameIn.clear(); firstNameIn.sendKeys(firstName);
        lastNameIn.clear(); lastNameIn.sendKeys(lastName);
        streetAddress1In.clear(); streetAddress1In.sendKeys(streetAddress);
        cityIn.clear();cityIn.sendKeys(city);
        postcodeIn.clear(); postcodeIn.sendKeys(postcode);
        phoneIn.clear(); phoneIn.sendKeys(phone);
        emailIn.clear(); emailIn.sendKeys(email);
        return this;
    }

    public void placeOrder(){
        //Place order
        Helpers scrollDown = new Helpers(driver);
        scrollDown.scroll(driver, placeOrderIn);
        scrollDown.waitForClickableButton(By.id("place_order"), Duration.ofSeconds(5)); //Waits for button to be clickable
        checkPaymentsIn.click();
        placeOrderIn.click();
    }

    public void screenshotOrder(){
        //Screenshot order placed after waiting for order to appear on screen
        Helpers screenshot = new Helpers(driver);
        screenshot.waitForVisibleElement(By.cssSelector("[class=\"woocommerce-order\"]"),Duration.ofSeconds(4));
        screenshot.TakeWebElementScreenshot("Order-Received", By.id("main"));
    }
}
