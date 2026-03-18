package com.karina.tests.checkout;

import com.karina.core.AuthHelper;
import com.karina.pages.*;
import com.karina.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutSmokeTest extends BaseTest {

    @Test(groups={"smoke"})
    public void checkoutShouldWork() {
        ProductsPage products = AuthHelper.loginAsStandardUser(driver);
        Assert.assertTrue(products.isOpened());
        products.addBackpackToCart();

        CartPage cart = products.goToCart();

        CheckoutPage checkout = cart.goToCheckout();
        Assert.assertTrue(checkout.isOpened());
        checkout.fillForm("Karina","Test","12345");

        CheckoutOverviewPage overview = checkout.continueCheckout();

        CheckoutCompletePage complete = overview.finish();
        Assert.assertTrue(complete.isOpened());
        Assert.assertEquals(complete.getSuccessMessage(),"Thank you for your order!","Order was not completed successfully");
    }
}
