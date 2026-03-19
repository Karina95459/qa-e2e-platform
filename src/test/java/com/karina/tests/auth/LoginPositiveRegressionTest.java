package com.karina.tests.auth;

import com.karina.core.AuthHelper;
import com.karina.pages.ProductsPage;
import com.karina.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Authentication")
public class LoginPositiveRegressionTest extends BaseTest {

    @Test(groups = {"regression"})
    @Description("Login should work with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void loginShouldWork(){
        ProductsPage products = AuthHelper.loginAsStandardUser(driver);
        Assert.assertTrue(products.isOpened());
    }
}
