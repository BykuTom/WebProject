package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShortsPage extends WebPage {

private By allProductsSelector = By.className("product-item-info");
    public ShortsPage(WebDriver webDriver) {
        super(webDriver);
    }

    

//    public ProductPage getProductByName(String name){
//
//    }
}
