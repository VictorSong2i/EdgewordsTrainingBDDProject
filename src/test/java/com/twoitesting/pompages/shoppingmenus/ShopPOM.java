package com.twoitesting.pompages.shoppingmenus;

import com.twoitesting.utils.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ShopPOM {
    WebDriver driver;

    //Web elements in shop page
    @FindBy(css = "[aria-label='Add “Beanie” to your cart']") //Beanie
    WebElement beanie;
    @FindBy(css = "[title='View cart']")
    WebElement viewCart;

    public ShopPOM(WebDriver driver) { //Constructor to accept the driver from the test
        this.driver = driver;
        PageFactory.initElements(driver, this); //Page factory will scan page for annotated locators whenever they are accessed
    }

    public ShopPOM beanie_cl() {
        //add beanie to cart
        Helpers scrollDown = new Helpers(driver);
        scrollDown.scroll(driver, beanie); //Scroll to element
        beanie.click();
        return this;
    }

    public ShopPOM findItem_cl(String item){
        //Add any item depending on user input
        Helpers scrollDown = new Helpers(driver);
        WebElement addItem = driver.findElement(By.cssSelector("[aria-label='Add “"+ item +"” to your cart']")); //Finds item
        scrollDown.scroll(driver, addItem);
        addItem.click();
        return this;
    }

    public void viewCart() {
        //Click on view cart
        Helpers scrollDown = new Helpers(driver);
        scrollDown.waitForElementAndText(3, By.cssSelector("[title='View cart']"), "View cart");
        viewCart.click();
    }
}
