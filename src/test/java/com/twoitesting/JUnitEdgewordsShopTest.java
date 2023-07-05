package com.twoitesting;

import com.twoitesting.pompages.placeorder.BillingPOM;
import com.twoitesting.pompages.placeorder.CartPOM;
import com.twoitesting.pompages.placeorder.PlacedOrderPOM;
import com.twoitesting.pompages.shoppingmenus.LoginPOM;
import com.twoitesting.pompages.shoppingmenus.ShopPOM;
import com.twoitesting.pompages.shoppingmenus.TaskBarMenuPOM;
import com.twoitesting.utils.JUnitHooks;
import org.junit.jupiter.api.*;


public class JUnitEdgewordsShopTest extends JUnitHooks {

    @Test
    void placeOrderBeanie() {

        //Login
        TaskBarMenuPOM home = new TaskBarMenuPOM(driver);
        home.myAccountClick(); //Access login page

        //Login with details
        LoginPOM login = new LoginPOM(driver);
        login.setUsername("victor.song@2itesting.com")
                .setPassword("Edgewords123456?")
                .submitForm();

        //Accessing shop
        TaskBarMenuPOM topMenu = new TaskBarMenuPOM(driver);
        topMenu.shopClick();

        //Adding beanie to cart
        ShopPOM shop = new ShopPOM(driver);
        shop.beanie_cl().viewCart();

        //Apply coupon and checkout
        CartPOM applyCoupon = new CartPOM(driver);
        applyCoupon.addCoupon("edgewords").couponCostCheck();
        applyCoupon.checkout();

        //Send user info and place order
        BillingPOM placeBillingOrder = new BillingPOM(driver);
        placeBillingOrder.sendBillingDetails("Victor", "Song", " 51 Little France Cres",
                "Edinburgh", "EH16 4SA", "07829348271","victor.song@2itesting.com")
                .placeOrder();

        //Check order number is same from summary page and orders page
        PlacedOrderPOM checkOrder = new PlacedOrderPOM(driver);
        checkOrder.screenshotOrder("Beanie"); //Take screenshot of order placed
        checkOrder.checkOrderNum();

        //logOut
        TaskBarMenuPOM logOutPage = new TaskBarMenuPOM(driver);
        logOutPage.logOut();
    }
}
