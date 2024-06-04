package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public abstract class WebPage {
    protected WebDriver webDriver;
    protected Actions actions;

    protected By basketLink = By.className("showcart");
    protected By viewCartLink = By.className("viewcart");

    public WebPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.actions = new Actions(this.webDriver);
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void refresh(){
        webDriver.navigate().refresh();
    }

    public void waitFor(int timeoutInSeconds) throws InterruptedException {
        Thread.sleep(timeoutInSeconds * 1000);
    }

    public void clickBasketIcon(){
        webDriver.findElement(basketLink).click();
    }

    public CartPage goToViewAndEditCart() {
        actions.moveToElement(webDriver.findElement(viewCartLink)).click().perform();
        return new CartPage(webDriver);
    }
}
