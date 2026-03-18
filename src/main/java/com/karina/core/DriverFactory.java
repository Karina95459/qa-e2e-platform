package com.karina.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        try {
            Path profileDir = Files.createTempDirectory("selenium-profile-");
            options.addArguments("--user-data-dir=" + profileDir.toAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException("Cannot create temp profile dir", e);
        }

        // 2) выключаем менеджер паролей
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding,PasswordManagerRedesign");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--guest");
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-default-browser-check");

        return new ChromeDriver(options);
    }
}
