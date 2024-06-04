package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDetailsPage extends WebPage{

    private By myOrdersLink = By.linkText("My Orders");

    public AccountDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public OrderHistoryPage goToOrderHistory(){
        webDriver.findElement(myOrdersLink).click();
        return new OrderHistoryPage(webDriver);
    }
}
