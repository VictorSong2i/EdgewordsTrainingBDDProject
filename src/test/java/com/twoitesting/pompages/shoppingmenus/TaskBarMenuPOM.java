package com.twoitesting.pompages.shoppingmenus;

import com.twoitesting.utils.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class TaskBarMenuPOM {

    WebDriver driver;

    //Web elements in top task bar
    @FindBy(css = "#menu-item-43")
    public WebElement shop;
    @FindBy(css = "#menu-item-46>a")
    WebElement myAccount;
    @FindBy(css = ".woocommerce-MyAccount-navigation-link:nth-child(2) > a")
    WebElement orders;
    @FindBy(css = ".woocommerce-orders-table > tbody>tr:nth-child(1)>td:nth-child(1)>a")
    WebElement latestOrderNum;
    @FindBy(linkText = "Log out")
    WebElement logOutButton;

    public TaskBarMenuPOM(WebDriver driver) {
        //Call driver every time its called
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyLogIn(){
        Helpers verify = new Helpers(driver);
        verify.waitForClickableButton(By.linkText("Log out"), Duration.ofSeconds(3));
    }

    public TaskBarMenuPOM myAccountClick() {
        //Click login
        myAccount.click();
        return this;
    }

    public void shopClick() {
        //Access shop
//        Helpers waitClick = new Helpers(driver);
//        waitClick.waitForClickableButton(By.cssSelector(".menu-item-43"),Duration.ofSeconds(3));
        shop.click();
    }

    public void orderClick() {
        orders.click();
    } //Access orders page

    public String getOrderNum() {
        //Get latest order number as string and delete # symbol
        return latestOrderNum.getText().substring(1);
    }

    public void logOut() {
        //Click logout button through my accounts page
        myAccount.click();
        Helpers waitClick = new Helpers(driver);
        waitClick.waitForClickableButton(By.linkText("Log out"),Duration.ofSeconds(3));
        logOutButton.click();
    }
}
