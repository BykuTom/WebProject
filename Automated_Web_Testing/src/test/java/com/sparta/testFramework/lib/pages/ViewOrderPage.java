package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ViewOrderPage extends WebPage{

    private By orderNumberWrapper = By.className("page-title-wrapper");
    private By orderNumber = By.className("base");

    public ViewOrderPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean validOrderNumber(){
        String orderNumberString = webDriver.findElement(orderNumberWrapper).findElement(orderNumber).getText();
        int orderNB = Integer.parseInt(orderNumberString.replace("Order # ", ""));

        return (orderNB > 1) ? true : false;
    }
}
