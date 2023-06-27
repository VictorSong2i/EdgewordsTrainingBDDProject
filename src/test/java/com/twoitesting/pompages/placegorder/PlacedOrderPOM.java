package com.twoitesting.pompages.placegorder;

import com.twoitesting.utils.Helpers;
import org.junit.jupiter.api.Test;
import com.twoitesting.pompages.shoppingmenus.TaskBarMenuPOM;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PlacedOrderPOM {

    WebDriver driver;

    @FindBy(css=".woocommerce-order-overview__order > strong")
    WebElement orderNum;

    public PlacedOrderPOM(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PlacedOrderPOM checkOrderNum(){
        //Assert order num from initial order summary and order num from orders page in accounts

        //Wait for ordersummary to appear on screen
        Helpers orderSummaryWait = new Helpers(driver);
        orderSummaryWait.waitForElementAndText(4, By.cssSelector("[class=\"entry-header\"]"),"Order received");
        String orderSummary = orderNum.getText(); //Store order number from order summary

        //Navigate to orders page
        TaskBarMenuPOM orderPage = new TaskBarMenuPOM(driver);
        orderPage.myAccountClick().orderClick();

        //Store order number from orders page and compare with order number from summary
        String orderNumCompare =  orderPage.getOrderNum();
        assertThat(orderSummary, equalTo(orderNumCompare));
        return this;
    }
}
