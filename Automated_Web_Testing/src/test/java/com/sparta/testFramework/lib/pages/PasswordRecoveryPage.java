package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PasswordRecoveryPage extends WebPage{

    private final By emailBy = new By.ById("email_address");
    private final By forgotPassword = new By.ByCssSelector(".action.submit.primary");
    public PasswordRecoveryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterEmail(String email) {
        WebElement emailInput = webDriver.findElement(emailBy);
        emailInput.sendKeys(email);
    }

    public void clickResetMyPassword() {
        WebElement resetPasswordButton = webDriver.findElement(forgotPassword);
        resetPasswordButton.click();
    }
}
