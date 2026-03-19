package com.karina.tests.checkout;

import com.karina.core.AuthHelper;
import com.karina.pages.*;
import com.karina.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Checkout")
public class CheckoutSmokeTest extends BaseTest {

    @Test(groups={"smoke"})
    @Description("checkout  should work")
    @Severity(SeverityLevel.CRITICAL)
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
