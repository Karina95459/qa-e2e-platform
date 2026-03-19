package com.karina.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {
    private By productName = By.cssSelector(".inventory_details_name");
    private By productPrice = By.cssSelector(".inventory_details_price");
    private By description = By.cssSelector(".inventory_details_desc");
    private By addToCartButton = By.cssSelector("[data-test^='add-to-cart']");
    private By removeFromCartButton = By.cssSelector("[data-test^='remove']");
    private By backToProductsButton = By.id("back-to-products");
    private By productBadge = By.cssSelector(".shopping_cart_badge");

    public ProductDetailsPage(WebDriver driver){
        super(driver);
    }

    public boolean isOpened() {
        return isVisible(productName);
    }

    public boolean isRemoveVisible(){
        return isVisible(removeFromCartButton);
    }

    public boolean isAddToCartVisible(){
        return isVisible(addToCartButton);
    }

    public String getCartItemsNumber() {
        return getText(productBadge);
    }
    public String getProductName() {
        return getText(productName);
    }

    public  String getProductPrice() {
        return getText(productPrice);
    }

    @Step("Add product to cart")
    public void addToCart() {
        click(addToCartButton);
    }

    @Step("Remove product from cart")
    public void removeFromCart() {
        click(removeFromCartButton);
    }

    @Step("Back to products page")
    public ProductsPage backToProductsPage() {
        click(backToProductsButton);
        return new ProductsPage(driver);
    }
}
