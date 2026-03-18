package com.karina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {
    private By finishButton = By.id("finish");
    private By itemName = By.cssSelector(".inventory_item_name");
    private By cancelButton = By.id("cancel");
    private By itemTotal = By.cssSelector(".summary_subtotal_label");
    private By tax = By.cssSelector(".summary_tax_label");
    private By total = By.cssSelector(".summary_total_label");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return isVisible(title);
    }

    public List<String> getItemNames() {
        List<WebElement> elements = driver.findElements(itemName);
        List<String> names = new ArrayList<>();

        for (WebElement el : elements) {
            names.add(el.getText());
        }
        return names;
    }

    public boolean isProductPresent(String productName) {
        return getItemNames().contains(productName);
    }

    public CheckoutCompletePage finish() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public double getItemTotal() {
        String text = getText(itemTotal);
        text = text.replace("Item total: $", "");
        return Double.parseDouble(text);
    }

    public double getTax() {
        String text = getText(tax);
        text = text.replace("Tax: $", "");
        return Double.parseDouble(text);
    }

    public double getTotal() {
        String text = getText(total);
        text = text.replace("Total: $", "");
        return Double.parseDouble(text);
    }

    public ProductsPage cancelCheckoutOverview() {
        click(cancelButton);
        return new ProductsPage(driver);
    }
}
