package com.karina.tests.productDetails;

import com.karina.pages.ProductDetailsPage;
import com.karina.pages.ProductsPage;
import com.karina.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

@Feature("Product Details")
public class ProductDetailsRegressionTest extends BaseTest {

    @Test(dataProvider = "products", groups = {"regression"})
    @Description("Open correct product details")
    @Severity(SeverityLevel.NORMAL)
    public void openCorrectProductDetails(String productName) {
        ProductsPage products = openProductsPage();
        ProductDetailsPage productDetails = products.openProductDetails(productName);
        Assert.assertTrue(productDetails.isOpened());
        Assert.assertEquals(productDetails.getProductName(),productName,"Invalid product name in details");
    }

    @Test(groups = {"regression"})
    @Description("add to cart from Details should update button and badge")
    @Severity(SeverityLevel.NORMAL)
    public void addToCartFromDetailsShouldUpdateButtonAndBadge() {
        ProductsPage products = openProductsPage();
        ProductDetailsPage productDetails = products.openProductDetails("Sauce Labs Backpack");
        productDetails.addToCart();
        Assert.assertTrue(productDetails.isRemoveVisible());
        Assert.assertEquals(productDetails.getCartItemsNumber(), "1","unexpected count of items in the cart");
    }

    @Test(groups = {"regression"})
    @Description("remove from cart from Details should update button and badge")
    @Severity(SeverityLevel.NORMAL)
    public void removeFromCartFromDetailsShouldUpdateStates() {
        ProductsPage products = openProductsPage();
        ProductDetailsPage productDetails = products.openProductDetails("Sauce Labs Backpack");
        productDetails.addToCart();
        Assert.assertTrue(productDetails.isRemoveVisible());
        Assert.assertEquals(productDetails.getCartItemsNumber(), "1","unexpected count of items in the cart");
        productDetails.removeFromCart();
        Assert.assertTrue(productDetails.isAddToCartVisible());
    }

    @Test(groups = {"regression"})
    @Description("back to Products should return to Products Page")
    @Severity(SeverityLevel.NORMAL)
    public void backToProductsShouldReturnToProductsPage() {
        ProductsPage products = openProductsPage();
        ProductDetailsPage productDetails = products.openProductDetails("Sauce Labs Backpack");
        Assert.assertTrue(productDetails.isOpened());
        ProductsPage product = productDetails.backToProductsPage();
        Assert.assertTrue(product.isOpened());
    }

    @DataProvider(name = "products")
    public Object[][] products() {
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"}
        };
    }
}
