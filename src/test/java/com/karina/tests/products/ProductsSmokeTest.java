package com.karina.tests.products;

import com.karina.core.AuthHelper;
import com.karina.pages.ProductsPage;
import com.karina.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsSmokeTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void addToCartUpdatesBadge() {
        ProductsPage products = AuthHelper.loginAsStandardUser(driver);
        products.addBackpackToCart();
        Assert.assertEquals(products.getCartItemsCount(),"1");
    }
}
