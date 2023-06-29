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

    //Web elements in task bar menus
    @FindBy(css = "#menu-item-43")
    WebElement shop;
    @FindBy(css = "#menu-item-46>a")
    WebElement myAccount;
    @FindBy(css = ".woocommerce-MyAccount-navigation-link:nth-child(2) > a")
    WebElement orders;
    @FindBy(css = "[data-title=\"Order\"]") //This grabs first item which would be latest order
    WebElement latestOrderNum;
    @FindBy(linkText = "Log out")
    WebElement logOutButton;

    public TaskBarMenuPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyLogIn() {
        //Check login through checking if log out button is present
        Helpers verify = new Helpers(driver);
        verify.waitForClickableButton(By.linkText("Log out"), Duration.ofSeconds(3));
        System.out.println("Victor is logged in");
    }

    public TaskBarMenuPOM myAccountClick() {
        myAccount.click(); //Click my accounts page
        return this;
    }

    public void shopClick() {
        shop.click(); //Access shop oage
    }

    public void orderClick() {
        orders.click(); //Access orders page
    }

    public String getOrderNum() {
        //Get latest order number as string and delete # symbol
        return latestOrderNum.getText().substring(1);
    }

    public void logOut() {
        //Click logout button through my accounts page
        myAccount.click();

        Helpers waitClick = new Helpers(driver);
        waitClick.waitForClickableButton(By.linkText("Log out"), Duration.ofSeconds(3));
        logOutButton.click();
    }
}
