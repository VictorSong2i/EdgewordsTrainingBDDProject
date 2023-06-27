package com.twoitesting.pompages.shoppingmenus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenuPOM {

    WebDriver driver; //Field to hold driver for methods in this class

    //Web elements in main menu page
    @FindBy(id="#menu-item-46")
    WebElement loginLink;

    public MainMenuPOM(WebDriver driver) { //Constructor to accept the driver from the test
        this.driver = driver;
        PageFactory.initElements(driver, this); //Page factory will scan page for annotated locators whenever they are accessed
    }

    public void goLogin(){
        //Click login
        loginLink.click();
    }
}
