package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends WebPage{

    private By createAccountLink = By.linkText("Create an Account");
    private By signInLink = By.linkText("Sign In");
    private By saleLink = By.linkText("Sale");
    private By menLink = By.linkText("Men");
    private By topsLink = By.linkText("Tops");
    private By jacketsLink = By.linkText("Jackets");

    public HomePage(WebDriver webDriver){
        super(webDriver);
    }

    public CreateAccountPage goToCreateAccountPage(){
        webDriver.findElement(createAccountLink).click();
        return new CreateAccountPage(webDriver);
    }

    public SignInPage goToSignInPage(){
        webDriver.findElement(signInLink).click();
        return new SignInPage(webDriver);
    }

    public SalePage goToSalePage(){
        webDriver.findElement(saleLink).click();
        return new SalePage(webDriver);
    }
}
