package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderSuccessPage extends WebPage{
    public OrderSuccessPage(WebDriver webDriver) {
        super(webDriver);

        if (!webDriver.getCurrentUrl().equals("https://magento.softwaretestingboard.com/checkout/onepage/success/")) {
            throw new IllegalStateException(
                    "This is not the order success page, current page is: " + webDriver.getCurrentUrl()
            );
        }
    }

    public String getSuccessMessage() {
        return webDriver.findElement(By.className("checkout-success")).getText();
    }
}
