package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderHistoryPage extends WebPage{

    private By messageInfoEmpty = By.className("info");
    private By ordersTable = By.tagName("tbody");
    private By orderRow = By.tagName("tr");

    public OrderHistoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getMessageText(){
        return webDriver.findElement(messageInfoEmpty).findElement(By.tagName("span")).getText();
    }

    public List<WebElement> getListOfOrders(){
        return webDriver.findElement(ordersTable).findElements(orderRow);
    }

    public int getNumberOfOrders(){
        return getListOfOrders().size();
    }

    public WebElement getOrderByIndex(int index){
        return (index == 1) ? getListOfOrders().getFirst() : getListOfOrders().get(index - 1);
    }

    public ViewOrderPage viewOrder(int index){
        getOrderByIndex(index).findElement(By.className("view")).click();
        return new ViewOrderPage(webDriver);
    }


}
