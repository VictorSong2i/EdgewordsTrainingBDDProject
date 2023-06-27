package com.twoitesting.pompages.placegorder;

import com.twoitesting.utils.Helpers;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CartPOM {
    WebDriver driver;

    public CartPOM(WebDriver driver) {
        this.driver = driver; //initiate driver after being called
        PageFactory.initElements(driver, this);
    }

    //Web elements in cart page
    @FindBy(linkText = "Proceed to checkout")
    WebElement checkout;
    @FindBy(css = ".coupon-edgewords>td>span")
    WebElement discountNum;
    @FindBy(css = "[placeholder='Coupon code']")
    WebElement couponCode;
    @FindBy(css = "[name = 'apply_coupon']")
    WebElement couponApply;
    @FindBy(css = ".cart-subtotal>[data-title=\"Subtotal\"]>span")
    WebElement subtotal;
    @FindBy(css = "[data-title=\"Coupon: edgewords\"]>span")
    WebElement couponDiscount;
    @FindBy(css = ".woocommerce-shipping-methods>li>label>span")
    WebElement shipping;
    @FindBy(css = ".order-total > td>strong>span")
    WebElement total;
    @FindBy(css = ".checkout-button")
    WebElement checkoutButton;

    public CartPOM addCoupon(String code) {
        //Apply coupon discount code
        Helpers scrollDown = new Helpers(driver);
        scrollDown.scroll(driver, couponCode);

        couponCode.sendKeys(code);
        couponApply.click();
        scrollDown.waitForElementAndText(3, By.cssSelector("[class=\"cart-discount coupon-edgewords\"] >th"),"Coupon: edgewords");
        return this;
    }

    public void couponCostCheck() {
        //initiate BigDecimal for money calculations
        BigDecimal subtotalCost = new BigDecimal(subtotal.getText().substring(1));
        BigDecimal couponCost = new BigDecimal(couponDiscount.getText().substring(1));
        BigDecimal shippingCost = new BigDecimal(shipping.getText().substring(1));
        BigDecimal totalCost = new BigDecimal(total.getText().substring(1));

        //Assert coupon 15% discount
        BigDecimal discountNum = new BigDecimal("0.15");
        assertThat(subtotalCost.multiply(discountNum).setScale(2, RoundingMode.HALF_EVEN)
                , Matchers.comparesEqualTo(couponCost));

        //Assert Total is correct
        BigDecimal sum = new BigDecimal(0);
        sum = sum.add(subtotalCost).subtract(couponCost).add(shippingCost);
        assertThat(sum, Matchers.comparesEqualTo(totalCost));
    }

    public void checkout() throws InterruptedException {
        //Click checkout button
        Helpers scrollDown = new Helpers(driver);
        Thread.sleep(3000); //Sleep for 3 seconds, NEED TO FIND WAY TO IMPLEMENT IMPLICIT WAIT
        scrollDown.scroll(driver, checkoutButton); //Scroll to button
        checkoutButton.click();


//        scrollDown.waitForElementAndText(10, By.cssSelector(".cart-discount > th"), "Coupon: edgewords");
//        Duration timeOut = Duration.ofSeconds(10);
//        scrollDown.waitForClickableButton(By.cssSelector(".checkout-button"), timeOut);
    }
}
