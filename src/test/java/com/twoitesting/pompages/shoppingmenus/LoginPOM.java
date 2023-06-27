package com.twoitesting.pompages.shoppingmenus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOM {

    WebDriver driver; //Field to hold driver for methods in this class

    public LoginPOM(WebDriver driver) { //Constructor to accept the driver from the test
        this.driver = driver;
        PageFactory.initElements(driver, this);
        //assertThat(driver.getTitle()=="Login", is(true)); //As an exception to "dont assert in the POM" you may choose to "sanity" check you are on the right page and crash out if not.
    }

    //Web elements in login page
    @FindBy(css = "#username")
    public WebElement usernameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(css = "button[name='login']")
    WebElement submitButton;

    //Service Methods
    public LoginPOM setUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
        return this; //By returning *this* instance of the class, your test can chain method calls.
    }

    public LoginPOM setPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public void submitForm(){
        submitButton.click();
    } //Submit username and password

}
