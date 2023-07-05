package com.twoitesting.pompages.placeorder;

import com.twoitesting.pompages.shoppingmenus.TaskBarMenuPOM;
import com.twoitesting.utils.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PlacedOrderPOM {

    WebDriver driver;

    //Web elements in placed order page
    @FindBy(css = ".woocommerce-order-overview__order > strong")
    WebElement orderNum;

    public PlacedOrderPOM(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkOrderNum() {
        //Assert order num from initial order summary and order num from orders page in accounts

        String orderSummary = orderNum.getText(); //Store order number from order summary

        //Navigate to orders page
        TaskBarMenuPOM orderPage = new TaskBarMenuPOM(driver);
        orderPage.myAccountClick().orderClick();

        //Store order number from orders page and compare with order number from summary
        String orderNumCompare = orderPage.getOrderNum();
        assertThat(orderSummary, equalTo(orderNumCompare));
        System.out.println("Order numbers match");
    }

    public void screenshotOrder(String item) {
        //Screenshot order placed after waiting for order to appear
        Helpers screenshot = new Helpers(driver);
        screenshot.waitForVisibleElement(By.cssSelector("[class=\"woocommerce-order\"]"), Duration.ofSeconds(4));
        screenshot.TakeWebElementScreenshot("Order-Received-"+item, By.id("main"));
    }
}
