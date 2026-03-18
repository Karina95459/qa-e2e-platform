package com.karina.tests.auth;

import com.karina.core.AuthHelper;
import com.karina.pages.LoginPage;
import com.karina.pages.ProductsPage;
import com.karina.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutRegressionTest extends BaseTest {

    @Test(groups = {"regression"})
    public void logoutShouldWorkAndPreventBackNavigation() {
        ProductsPage products = AuthHelper.loginAsStandardUser(driver);
        Assert.assertTrue(products.isOpened());

        LoginPage login = products.logout();
        Assert.assertTrue(login.isLoginFormVisible());

        driver.navigate().back();
        String urlAfterBack = driver.getCurrentUrl();
        Assert.assertFalse(urlAfterBack.contains("inventory"), "Back returned to inventory" + urlAfterBack);
        Assert.assertTrue(login.isLoginFormVisible());
    }
}
