package com.karina.tests.auth;

import com.karina.pages.LoginPage;
import com.karina.tests.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Authentication")
public class LoginSmokeTest extends BaseTest {

    @Test(groups = {"smoke"})
    @Description("Login should work with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldOpenLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginFormVisible(),"Login should be visible");
    }
}
