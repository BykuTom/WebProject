package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShortsPage extends WebPage {

private By allProductsSelector = By.className("product-item-info");
    public ShortsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public ProductPage getProductByName(String name){
        List<WebElement> allElements = webDriver.findElements(allProductsSelector);
        WebElement product = null;
        for (WebElement webElement : allElements) {
                if (webElement.findElement(By.className("product-item-link")).getText().equals(name)) product = webElement;
        }
        product.click();
        return new ProductPage(webDriver);
    }
}
