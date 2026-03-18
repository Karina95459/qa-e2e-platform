package com.karina.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BasePage {

    private By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartBadge = By.cssSelector(".shopping_cart_badge");
    private By cartLink = By.cssSelector(".shopping_cart_link");
    private By burgerMenu = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    private By addBikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    private By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    private By removeBikeLight = By.id("remove-sauce-labs-bike-light");
    private By sortDropdown = By.cssSelector(".product_sort_container");
    private By itemNames = By.cssSelector(".inventory_item_name");
    private By itemPrices = By.cssSelector(".inventory_item_price");
    private By productCards = By.cssSelector(".inventory_item");


    public ProductsPage(WebDriver driver)
    {
        super(driver);
    }

    public boolean isOpened()
    {
       return isVisible(title);
    }

    public boolean isAddBackpackButtonVisible() {
        return isVisible(addBackpackButton);
    }

    public boolean isAddBikeLightButtonVisible() {
        return isVisible(addBikeLightButton);
    }

    public void addBackpackToCart()
    {
        click(addBackpackButton);
    }

    public void addBikeLightToCart() {
        click(addBikeLightButton);
    }

    public void removeBackpackFromCart() {
        click(removeBackpackButton);
    }

    public void removeBikeLightFromCart() {
        click(removeBikeLight);
    }

    public boolean isBackpackRemoveVisible() {
        return isVisible(removeBackpackButton);
    }

    public boolean isBikeLightRemoveVisible() {
        return isVisible(removeBikeLight);
    }

    public void selectSort(String value) {
        Select sort = new Select(find(sortDropdown));
        sort.selectByValue(value);
    }

    public List<String> getItemNames() {
        List<WebElement> elements = driver.findElements(itemNames);
        List<String> names = new ArrayList<>();

        for (WebElement el : elements) {
            names.add(el.getText());
        }

        return names;
    }

    public List<Double> getItemPrices() {
        List<WebElement> elements = driver.findElements(itemPrices);
        List<Double> prices = new ArrayList<>();

        for (WebElement el : elements) {
            String priceText = el.getText().replace("$","");
            prices.add(Double.parseDouble(priceText));
        }

        return prices;
    }

    public void addProductToCart(String productName) {
        List <WebElement> items = driver.findElements(productCards);
        for (WebElement item : items) {
            String name = item.findElement(By.cssSelector(".inventory_item_name")).getText();

            if (name.equals(productName)) {
                item.findElement(By.tagName("button")).click();
                break;
            }
        }
    }

    public String getCartItemsCount() {
        if (isVisible(cartBadge))
        {
            return getText(cartBadge);
        }
        return "";
    }

    public ProductDetailsPage openProductDetails(String productName) {

        List<WebElement> products = driver.findElements(itemNames);

        for(WebElement product : products) {

            if (product.getText().equals(productName)){
                product.click();
                return new ProductDetailsPage(driver);
            }
        }

        throw new RuntimeException("Product not found" + productName);
    }

    public LoginPage logout() {
        click(burgerMenu);
        waitVisible(logoutLink);
        click(logoutLink);
        return new LoginPage(driver);
    }

    public CartPage goToCart() {
        click(cartLink);
        return new CartPage(driver);
    }
}
