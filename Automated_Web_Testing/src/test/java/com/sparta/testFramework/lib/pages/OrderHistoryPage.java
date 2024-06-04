package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderHistoryPage extends WebPage{

    private By messageInfoEmpty = By.className("empty");

    public OrderHistoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getMessageText(){
        return webDriver.findElement(messageInfoEmpty).findElement(By.tagName("span")).getText();
    }
}
