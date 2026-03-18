package com.karina.tests.checkout;

import com.karina.pages.CartPage;
import com.karina.pages.CheckoutPage;
import com.karina.pages.ProductsPage;
import com.karina.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class CheckoutNegativeRegressionTest extends BaseTest {

    @Test(dataProvider ="emptyFields",groups = {"regression"})
    public void checkoutShouldRequireMandatoryFields(String firstName, String lastName, String postalCode, String expectedError) {
        ProductsPage products = openProductsPage();
        products.addBackpackToCart();
        CartPage cart = products.goToCart();
        CheckoutPage checkout = cart.goToCheckout();

        Assert.assertTrue(checkout.isOpened());
        checkout.fillForm(firstName,lastName,postalCode);
        checkout.continueCheckout();

        String error = checkout.getErrorMessage();
        Assert.assertTrue(error.contains(expectedError),"Expected error to contain: "  + expectedError + " but was: " + error);
    }

    @DataProvider(name = "emptyFields")
    public Object[][] emptyFields() {
        return new Object[][]{
                {"","Doe", "12345", "First Name is required"},
                {"John", "", "12345", "Last Name is required"},
                {"John","Doe","","Postal Code is required"},
        };
    }
}
