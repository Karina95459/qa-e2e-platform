package com.karina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zip = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By errorMessage = By.cssSelector("[data-test='error']");
    private By cancelButton = By.id("cancel");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return isVisible(title);
    }

    public void fillForm(String name, String surname, String code) {
        type(firstName, name);
        type(lastName, surname);
        type(zip, code);
    }

    public String getErrorMessage() {
        return  getText(errorMessage);
    }

    public CheckoutOverviewPage continueCheckout() {
        click(continueButton);
        return new CheckoutOverviewPage(driver);
    }

    public CartPage cancelCheckout() {
        click(cancelButton);
        return new CartPage(driver);
    }
}
