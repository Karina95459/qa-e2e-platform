package com.karina.pages;

import com.karina.config.Config;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public void open(){
        driver.get(Config.BASE_URL);
    }

    @Step("Login with username: '{user}' ")
    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginButton);
    }

    public boolean isLoginFormVisible() {
            return isVisible(username)
                && isVisible(password)
                && isVisible(loginButton);
    }

    public boolean isErrorVisible() {
        return isVisible(errorMessage);
    }

    public String getErrorMessage() {
        return  getText(errorMessage);
    }
}
