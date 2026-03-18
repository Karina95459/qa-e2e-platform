package com.karina.tests.cart;

import com.karina.core.AuthHelper;
import com.karina.pages.CartPage;
import com.karina.pages.ProductsPage;
import com.karina.tests.base.BaseTest;
import org.testng.annotations.Test;
import org.testng.Assert;

public class CartSmokeTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void userCanOpenCart() {
        ProductsPage products = AuthHelper.loginAsStandardUser(driver);
        Assert.assertTrue(products.isOpened());
        products.addBackpackToCart();
        CartPage cart = products.goToCart();
        Assert.assertTrue(cart.isOpened());
        Assert.assertEquals(cart.getFirstItemName(),"Sauce Labs Backpack","Wrong item in cart");
    }
}
