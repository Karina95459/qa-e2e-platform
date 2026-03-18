package com.karina.pages;

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

    public String getSuccessMessage() {
        return getText(successMessage);
    }

}
