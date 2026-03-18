package com.karina.core;

import com.karina.pages.HomePage;
import com.karina.pages.LoginPage;
import com.karina.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import com.karina.config.Config;

public class AuthHelper {

    public static ProductsPage loginAsStandardUser(WebDriver driver) {
        HomePage home = new HomePage(driver);
        home.open(Config.BASE_URL);

        LoginPage login = new LoginPage(driver);
        login.login(Config.USER, Config.PASS);

        return new ProductsPage(driver);
    }
}
