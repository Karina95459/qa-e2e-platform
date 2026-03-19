package com.karina.tests.base;

import com.karina.config.Config;
import com.karina.core.AuthHelper;
import com.karina.core.DriverFactory;
import com.karina.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.get(Config.BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected ProductsPage openProductsPage() {
        return AuthHelper.loginAsStandardUser(driver);
    }

}
