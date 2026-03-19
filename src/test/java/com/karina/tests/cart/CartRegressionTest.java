package com.karina.tests.cart;

import com.karina.tests.base.BaseTest;
import com.karina.pages.CartPage;
import com.karina.pages.ProductsPage;
import com.karina.pages.CheckoutPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Cart")
public class CartRegressionTest extends BaseTest {

    private CartPage openCartWithOneItem() {
        ProductsPage products = openProductsPage();
        products.addBackpackToCart();
        return products.goToCart();
    }

    private CartPage openCartWithTwoItems() {
        ProductsPage products = openProductsPage();
        products.addBackpackToCart();
        products.addBikeLightToCart();
        return products.goToCart();
    }

    @Test(groups = {"regression"})
    @Description("Added product should be displayed in cart")
    @Severity(SeverityLevel.NORMAL)
    public void addedProductShouldBeDisplayedInCart() {
        CartPage cart = openCartWithTwoItems();
        Assert.assertTrue(cart.isOpened());
        Assert.assertTrue(cart.isProductPresent("Sauce Labs Backpack"),"Backpack should be displayed in cart");
        Assert.assertTrue(cart.isProductPresent("Sauce Labs Bike Light"),"Bike Light should be displayed in cart");
    }

    @Test(groups = {"regression"})
    @Description("Removed product should update cart content")
    @Severity(SeverityLevel.NORMAL)
    public void removeProductFromCartShouldUpdateCartContent() {
        CartPage cart = openCartWithTwoItems();
        Assert.assertTrue(cart.isOpened());

        Assert.assertTrue(cart.isProductPresent("Sauce Labs Backpack"),"Backpack should be displayed in cart");
        Assert.assertTrue(cart.isProductPresent("Sauce Labs Bike Light"),"Bike Light should be displayed in cart");

        cart.removeProduct("Sauce Labs Backpack");
        Assert.assertFalse(cart.isProductPresent("Sauce Labs Backpack"),"Backpack should be removed from cart");
        Assert.assertTrue(cart.isProductPresent("Sauce Labs Bike Light"),"Bike Light should be in cart");
    }

    @Test(groups = {"regression"})
    @Description("Continue Shopping should return to Products Page")
    @Severity(SeverityLevel.NORMAL)
    public void continueShoppingShouldReturnToProductsPage() {
        CartPage cart = openCartWithOneItem();
        ProductsPage products = cart.continueShopping();
        Assert.assertTrue(products.isOpened(),"Products should be opened after Continue Shopping");
    }

    @Test(groups = {"regression"})
    @Description("Checkout button should open Checkout Page")
    @Severity(SeverityLevel.CRITICAL)
    public void checkoutButtonShouldOpenCheckoutPage() {
        CartPage cart = openCartWithOneItem();
        CheckoutPage checkout = cart.goToCheckout();
        Assert.assertTrue(checkout.isOpened(),"Checkout page should be opened");
    }

    @Test(groups = {"regression"})
    @Description("Checkout button should open Checkout Page")
    @Severity(SeverityLevel.CRITICAL)
    public void removeLastItemShouldMakeCartEmpty() {
        CartPage cart = openCartWithOneItem();
        Assert.assertTrue(cart.isProductPresent("Sauce Labs Backpack"),"Backpack should be displayed in cart");

        cart.removeProduct("Sauce Labs Backpack");
        Assert.assertTrue(cart.isCartEmpty(),"After deleting all of products cart should be empty");
    }

    @Test(groups = {"regression"})
    @Description("Cart state should persist after Continue Button")
    @Severity(SeverityLevel.NORMAL)
    public void cartStateShouldPersistAfterContinueButton() {
        CartPage cart = openCartWithOneItem();
        ProductsPage products = cart.continueShopping();

        CartPage returnedCart = products.goToCart();
        Assert.assertTrue(returnedCart.isProductPresent("Sauce Labs Backpack"),"Backpack should remain in cart after continue shopping and return");
    }

}
