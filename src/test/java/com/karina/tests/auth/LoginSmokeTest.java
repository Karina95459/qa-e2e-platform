package com.karina.tests.auth;

import com.karina.pages.LoginPage;
import com.karina.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSmokeTest extends BaseTest {

    @Test(groups = {"smoke"})
    public void shouldOpenLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginFormVisible(),"Login should be visible");
    }
}
