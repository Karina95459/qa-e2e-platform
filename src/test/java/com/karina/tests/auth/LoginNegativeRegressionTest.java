package com.karina.tests.auth;

import com.karina.pages.LoginPage;
import com.karina.tests.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LoginNegativeRegressionTest extends BaseTest {

    @Test(dataProvider = "invalidLogins", groups = {"regression"})
    public void loginShouldShowErrorForInvalidCredentials(String username, String password, String expectedError) {
        LoginPage login = new LoginPage(driver);
        login.open();

        Assert.assertTrue(login.isLoginFormVisible());
        login.login(username,password);

        String error = login.getErrorMessage();
        Assert.assertTrue(error.contains(expectedError),"Expected error to contain: " + expectedError + "but was: " + error);
    }

    @DataProvider(name = "invalidLogins")
    public  Object[][] invalidLogins() {
        return new Object[][] {
                {"standard_user","wrong_pass","Username and password do not match"},
                {"","secret_sauce","Username is required"},
                {"standard_user","","Password is required"},
                {"locked_out_user","secret_sauce","locked out"}
        };
    }
}
