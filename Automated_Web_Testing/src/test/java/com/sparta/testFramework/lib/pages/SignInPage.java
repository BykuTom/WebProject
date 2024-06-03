package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignInPage extends WebPage{
    protected WebDriver webDriver;
    private final By signIn = new By.ById("send2");
    public SignInPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickSignIn() {
        WebElement signInButton = webDriver.findElement(signIn);
        signInButton.click();
    }
}
