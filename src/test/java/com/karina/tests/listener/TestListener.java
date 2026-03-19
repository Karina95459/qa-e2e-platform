package com.karina.tests.listener;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

import static com.karina.tests.base.BaseTest.driver;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        if (driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(
                    "Screenshot on failure",
                    new ByteArrayInputStream(screenshot)
            );
        }
    }
}
