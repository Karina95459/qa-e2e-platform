package com.karina.tests.products;

import com.karina.core.AuthHelper;
import com.karina.pages.ProductsPage;
import com.karina.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Products")
public class ProductsSmokeTest extends BaseTest {

    @Test(groups = {"smoke"})
    @Description("Add to cart updates badge")
    @Severity(SeverityLevel.CRITICAL)
    public void addToCartUpdatesBadge() {
        ProductsPage products = AuthHelper.loginAsStandardUser(driver);
        products.addBackpackToCart();
        Assert.assertEquals(products.getCartItemsCount(),"1");
    }
}
