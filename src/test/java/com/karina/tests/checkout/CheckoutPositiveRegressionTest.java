package com.karina.tests.checkout;

import com.karina.pages.*;
import com.karina.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.Assert;

@Feature("Checkout")
public class CheckoutPositiveRegressionTest extends BaseTest {

    private CheckoutPage openCheckoutPage(String... productNames) {
        ProductsPage products = openProductsPage();
        for (String productName : productNames) {
            products.addProductToCart(productName);
        }
        CartPage cart = products.goToCart();
        return cart.goToCheckout();
    }

    private CheckoutOverviewPage openCheckoutOverviewPage() {
        CheckoutPage checkout = openCheckoutPage("Sauce Labs Backpack");
        checkout.fillForm("John", "Doe","12345");
        return checkout.continueCheckout();
    }


    @Test(groups = "regression")
    @Description("Checkout with valid information should open Overview")
    @Severity(SeverityLevel.CRITICAL)
    public void checkoutWithValidInformationShouldOpenOverview() {
        CheckoutPage checkout = openCheckoutPage("Sauce Labs Backpack");
        Assert.assertTrue(checkout.isOpened(),"checkout page should be opened");

        checkout.fillForm("John", "Doe","12345");

        CheckoutOverviewPage overview = checkout.continueCheckout();
        Assert.assertTrue(overview.isOpened(), "overview should be opened");
    }

    @Test(groups = "regression")
    @Description("Checkout Overview should display selected Products")
    @Severity(SeverityLevel.CRITICAL)
    public void checkoutOverviewShouldDisplaySelectedProducts() {
        CheckoutPage checkout = openCheckoutPage("Sauce Labs Backpack", "Sauce Labs Bike Light");
        Assert.assertTrue(checkout.isOpened(),"checkout page should be opened");

        checkout.fillForm("John", "Doe", "12345");
        CheckoutOverviewPage overview = checkout.continueCheckout();
        Assert.assertTrue(overview.isOpened(), "overview should be opened");

        Assert.assertTrue(overview.isProductPresent("Sauce Labs Backpack"),"Sauce Labs Backpack should be present on overview");
        Assert.assertTrue(overview.isProductPresent("Sauce Labs Bike Light"),"Sauce Labs Bike Light should be present on overview");
    }

    @Test(groups = "regression")
    @Description("finish Checkout should complete Order")
    @Severity(SeverityLevel.CRITICAL)
    public void finishCheckoutShouldCompleteOrder() {
        CheckoutOverviewPage overview = openCheckoutOverviewPage();
        Assert.assertTrue(overview.isOpened(),"overview page should be opened");

        CheckoutCompletePage complete = overview.finish();
        Assert.assertTrue(complete.isOpened(),"complete should be opened");
    }

    @Test(groups = "regression")
    @Description("cancel from Checkout should return to Cart")
    @Severity(SeverityLevel.NORMAL)
    public void cancelFromCheckoutShouldReturnToCart() {
        CheckoutPage checkout = openCheckoutPage("Sauce Labs Backpack");
        Assert.assertTrue(checkout.isOpened(),"checkout page should be opened");

        CartPage cart = checkout.cancelCheckout();
        Assert.assertTrue(cart.isOpened(),"cart page should be opened");
    }

    @Test(groups = "regression")
    @Description("cancel from Overview should return to Products Page")
    @Severity(SeverityLevel.NORMAL)
    public void cancelFromOverviewShouldReturnToProductsPage() {
        CheckoutOverviewPage overview = openCheckoutOverviewPage();
        Assert.assertTrue(overview.isOpened(),"overview page should be opened");

        ProductsPage products = overview.cancelCheckoutOverview();
        Assert.assertTrue(products.isOpened(),"products page should be opened");
    }

    @Test(groups = "regression")
    @Description("checkout Overview should display correct Total")
    @Severity(SeverityLevel.CRITICAL)
    public void checkoutOverviewShouldDisplayCorrectTotal() {
        CheckoutPage checkout = openCheckoutPage("Sauce Labs Backpack", "Sauce Labs Bike Light");
        Assert.assertTrue(checkout.isOpened(),"checkout page should be opened");

        checkout.fillForm("John", "Doe", "12345");
        CheckoutOverviewPage overview = checkout.continueCheckout();
        Assert.assertTrue(overview.isOpened(),"overview should be opened");

        double itemTotal = overview.getItemTotal();
        double tax = overview.getTax();
        double total = overview.getTotal();

        double expectedTotal = itemTotal + tax;

        Assert.assertEquals(total, expectedTotal, 0.01,"Total price is calculated incorrectly");
    }
}
