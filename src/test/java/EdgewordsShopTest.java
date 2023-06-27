import com.twoitesting.pompages.placegorder.BillingPOM;
import com.twoitesting.pompages.placegorder.CartPOM;
import com.twoitesting.pompages.placegorder.PlacedOrderPOM;
import com.twoitesting.pompages.shoppingmenus.ShopPOM;
import com.twoitesting.pompages.shoppingmenus.TaskBarMenuPOM;
import com.twoitesting.utils.TestBase;
import org.junit.jupiter.api.*;


public class EdgewordsShopTest extends TestBase {

    @Test
    void placeOrderBeanie() throws InterruptedException {

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

        //Take screenshot of order placed
        placeBillingOrder.screenshotOrder();

        //Check order number is same from summary page and orders page
        PlacedOrderPOM checkOrder = new PlacedOrderPOM(driver);
        checkOrder.checkOrderNum();

        //logOut
        TaskBarMenuPOM logOutPage = new TaskBarMenuPOM(driver);
        logOutPage.logOut();
    }
}
