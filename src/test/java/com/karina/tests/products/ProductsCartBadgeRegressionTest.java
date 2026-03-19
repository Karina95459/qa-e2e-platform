package com.karina.tests.products;

import com.karina.core.AuthHelper;
import com.karina.pages.ProductsPage;
import com.karina.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.Assert;

@Feature("Products")
public class ProductsCartBadgeRegressionTest extends BaseTest {

    private void addTwoItems(ProductsPage products){
        products.addBackpackToCart();
        products.addBikeLightToCart();
    }

    @Test(groups = {"regression"})
    @Description("add single product updates badge and button state")
    @Severity(SeverityLevel.NORMAL)
    public void addSingleProductUpdatesBadgeAndButtonState() {

        ProductsPage products = openProductsPage();
        products.addBackpackToCart();
        String count = products.getCartItemsCount();
        Assert.assertEquals(count,"1");
        Assert.assertTrue(products.isBackpackRemoveVisible());
    }

    @Test(groups = {"regression"})
    @Description("add two products should update badge to 2")
    @Severity(SeverityLevel.NORMAL)
    public void addTwoProductsUpdatesBadgeTo2() {

        ProductsPage products = openProductsPage();

        addTwoItems(products);
        Assert.assertEquals(products.getCartItemsCount(), "2", "Cart badge should be 2 after adding 2 items");

        Assert.assertTrue(products.isBackpackRemoveVisible());
        Assert.assertTrue(products.isBikeLightRemoveVisible());
    }

    @Test(groups = {"regression"})
    @Description("remove  products should update cart badge")
    @Severity(SeverityLevel.NORMAL)
    public void removeProductsShouldUpdateCartBadge() {
        ProductsPage products = openProductsPage();

        addTwoItems(products);

        Assert.assertEquals(products.getCartItemsCount(), "2", "Cart badge should be 2 after adding 2 items");

        products.removeBackpackFromCart();
        Assert.assertEquals(products.getCartItemsCount(),"1","Cart badge should be 1 after deleting 1 item from cart");

        products.removeBikeLightFromCart();

        Assert.assertTrue(products.isAddBackpackButtonVisible());
        Assert.assertTrue(products.isAddBikeLightButtonVisible());
    }
}
