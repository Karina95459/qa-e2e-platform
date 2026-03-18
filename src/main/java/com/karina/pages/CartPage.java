package com.karina.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private By itemName = By.cssSelector(".inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By cartItems = By.cssSelector(".cart_item");
    private By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        return isVisible(title);
    }

    public String getFirstItemName() {
        return getText(itemName);
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

    public boolean isCartEmpty() {
        return getItemNames().isEmpty();
    }

    public void removeProduct(String productName) {
        List<WebElement> items = driver.findElements(cartItems);
        for (WebElement item : items) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText();
            if (name.equals(productName)) {
                item.findElement(By.cssSelector("button")).click();
                break;
            }
        }
    }

    public ProductsPage continueShopping() {
        click(continueShoppingButton);
        return new ProductsPage(driver);
    }

    public CheckoutPage goToCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }
}
