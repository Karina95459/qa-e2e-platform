package com.karina.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.karina.config.Config;

public abstract class BasePage {
   protected WebDriver driver;
   protected By title = By.cssSelector(".title");

   protected BasePage(WebDriver driver) {
       this.driver = driver;
   }

   protected WebElement find(By locator) {
       return driver.findElement(locator);
   }

   protected void type(By locator, String text) {
       find(locator).clear();
       find(locator).sendKeys(text);
   }

   protected void click(By locator) {
       find(locator).click();
   }

   protected boolean isVisible(By locator) {
       waitVisible(locator);
       return find(locator).isDisplayed();
   }

   protected void waitVisible(By locator) {
       new WebDriverWait(driver, Duration.ofSeconds(Config.TIMEOUT))
               .until(ExpectedConditions.visibilityOfElementLocated(locator));
   }

   protected String getText(By locator) {
       return find(locator).getText();
   }
}
