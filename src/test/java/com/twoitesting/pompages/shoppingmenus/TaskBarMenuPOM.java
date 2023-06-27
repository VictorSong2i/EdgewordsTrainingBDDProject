package com.twoitesting.pompages.shoppingmenus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public TaskBarMenuPOM myAccountClick() {
        myAccount.click();
        return this;
    } //Click login

    public void shopClick() {
        shop.click();
    } //Access shop

    public void orderClick() {
        orders.click();
    } //Access orders page

    public String getOrderNum() {
        //Get latest order number as string and delete # symbol
        return latestOrderNum.getText().substring(1);
    }

    public void logOut() {
        //Click logout button
        myAccount.click();
        logOutButton.click();
    }
}
