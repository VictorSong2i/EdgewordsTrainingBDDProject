package com.twoitesting.pompages.shoppingmenus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOM {

    WebDriver driver;

    public LoginPOM(WebDriver driver) { //Constructor to accept the driver from the test
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Web elements in login page
    @FindBy(css = "#username")
    public WebElement usernameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(css = "button[name='login']")
    WebElement submitButton;

    public LoginPOM setUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username); //Enter username details
        return this;
    }

    public LoginPOM setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password); //Enter password details
        return this;
    }

    public void submitForm() {
        submitButton.click();
    } //Submit username and password
}
