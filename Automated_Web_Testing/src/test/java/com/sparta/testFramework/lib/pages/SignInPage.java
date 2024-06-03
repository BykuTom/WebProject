package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage extends WebPage{
    private final By emailBy = new By.ByName("login[username]");
    private final By passwordBy = new By.ById("pass");
    private final By signIn = new By.ById("send2");
    public SignInPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickSignIn() {
        WebElement signInButton = webDriver.findElement(signIn);
        signInButton.click();
    }

    public void enterEmail(String email) {
        WebElement emailBox = webDriver.findElement(emailBy);
        emailBox.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordBox = webDriver.findElement(passwordBy);
        passwordBox.sendKeys(password);
    }
}
