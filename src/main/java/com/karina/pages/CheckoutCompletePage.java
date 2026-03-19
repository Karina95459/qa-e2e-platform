package com.karina.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    private By successMessage = By.cssSelector(".complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return isVisible(title);
    }

    @Step("Verify order completion message is displayed")
    public String getSuccessMessage() {
        return getText(successMessage);
    }

}
