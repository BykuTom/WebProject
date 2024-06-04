package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage extends WebPage {
    private WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    public PaymentPage(WebDriver webDriver) {
        super(webDriver);

        if (!webDriver.getCurrentUrl().equals("https://magento.softwaretestingboard.com/checkout/#payment")) {
            throw new IllegalStateException(
                    "This is not the payment page, current page is: " + webDriver.getCurrentUrl()
            );
        }
    }

    public String getDetails() {
        return webDriver.findElement(By.className("shipping-information-content")).getText();
    }

    public OrderSuccessPage placeOrder() {
        WebElement checkout = webDriver.findElement(By.className("checkout"));
        checkout.click();
        wait.until(ExpectedConditions.stalenessOf(checkout));
        return new OrderSuccessPage(webDriver);
    }
}
