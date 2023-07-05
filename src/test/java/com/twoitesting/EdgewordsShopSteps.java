package com.twoitesting;

import com.twoitesting.pompages.placeorder.BillingPOM;
import com.twoitesting.pompages.placeorder.CartPOM;
import com.twoitesting.pompages.placeorder.PlacedOrderPOM;
import com.twoitesting.pompages.shoppingmenus.LoginPOM;
import com.twoitesting.pompages.shoppingmenus.ShopPOM;
import com.twoitesting.pompages.shoppingmenus.TaskBarMenuPOM;
import com.twoitesting.utils.SharedDictionary;
import com.twoitesting.utils.WebDriverWrapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class EdgewordsShopSteps {

    private WebDriver driver;
    private final SharedDictionary dict;
    public EdgewordsShopSteps(SharedDictionary dict,  WebDriverWrapper wdWrapper){
        this.dict = dict; //Uses this scenario's dictionary
//        this.driver = (WebDriver)dict.readDict("mydriver");
        this.driver = wdWrapper.getDriver(); //Uses web driver wrapper class to get driver
    }

    @Given("^(?:I|i) (?:navigate|go) to login page$")
    public void iNavigateToLoginPage() {
        //Login
        TaskBarMenuPOM home = new TaskBarMenuPOM(driver);
        home.myAccountClick(); //Access login page
    }

    //Regex expression accepts any name and only for showing off, it will always log into Victor's account
    @When("^(?:I|i) submit [a-zA-Z]{1,}(?:'s)? username and password$")
    @When("^(?:I|i) log into Victor's account$")
    public void iSubmitSUsernameAndPassword() {
        //Login with details
        LoginPOM login = new LoginPOM(driver);
        login.setUsername("victor.song@2itesting.com")
                .setPassword("Edgewords123456?")
                .submitForm();
    }

    @Then("^(?:I|i) should be logged in$")
    public void iShouldBeLoggedIn() {
        //Check user has logged in
        TaskBarMenuPOM checkLogin = new TaskBarMenuPOM(driver);
        checkLogin.verifyLogIn();
    }

    @Given("^(?:I|i) navigate to shop$")
    public void iNavigateToShop() {
        //Accessing shop
        TaskBarMenuPOM topMenu = new TaskBarMenuPOM(driver);
        topMenu.shopClick();
    }

    @When("^(?:I|i) add a beanie hat to cart$")
    public void iAddBeanieToCart() {
        //Adding beanie to cart
        ShopPOM shop = new ShopPOM(driver);
        shop.beanie_cl().viewCart();
    }

    @When("I add an {string} to cart from shop menu")
    public void iAddItemToCart(String item) {
        //Add item to cart depending on user input
        ShopPOM shop = new ShopPOM(driver);
        shop.findItem_cl(item).viewCart();
    }

    @And("^checkout with coupon code$")
    public void checkoutWithCouponCode() {
        //Apply coupon and navigate to checkout page
        CartPOM applyCoupon = new CartPOM(driver);
        applyCoupon.addCoupon("edgewords").couponCostCheck();
        applyCoupon.checkout();

        //Send user info and place order
        BillingPOM placeBillingOrder = new BillingPOM(driver);
        placeBillingOrder.sendBillingDetails("Victor", "Song", " 51 Little France Cres",
                        "Edinburgh", "EH16 4SA", "07829348271","victor.song@2itesting.com")
                .placeOrder();
    }


    @Then("^Order should be placed and screenshot$")
    public void orderShouldBePlacedWithDiscount() {
        //Check order number is same from summary page and orders page
        PlacedOrderPOM checkOrder = new PlacedOrderPOM(driver);
        checkOrder.screenshotOrder("Beanie"); //Take screenshot of order placed
        checkOrder.checkOrderNum();

        //logOut
        TaskBarMenuPOM logOutPage = new TaskBarMenuPOM(driver);
        logOutPage.logOut();
    }

    @Then("^(.*) order should be placed and screenshot$")
    public void itemPlacedWithDiscount(String item) {
        //Check order number is same from summary page and orders page
        PlacedOrderPOM checkOrder = new PlacedOrderPOM(driver);
        checkOrder.screenshotOrder(item);
        checkOrder.checkOrderNum();

        //logOut
        TaskBarMenuPOM logOutPage = new TaskBarMenuPOM(driver);
        logOutPage.logOut();
    }
}
