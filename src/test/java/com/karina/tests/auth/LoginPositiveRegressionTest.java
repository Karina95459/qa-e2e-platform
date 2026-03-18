package com.karina.tests.auth;

import com.karina.core.AuthHelper;
import com.karina.pages.ProductsPage;
import com.karina.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPositiveRegressionTest extends BaseTest {

    @Test(groups = {"regression"})
    public void loginShouldWork(){
        ProductsPage products = AuthHelper.loginAsStandardUser(driver);
        Assert.assertTrue(products.isOpened());
    }
}
