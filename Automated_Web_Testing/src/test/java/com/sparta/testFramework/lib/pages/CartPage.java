package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends WebPage {
    private final By titles = By.cssSelector(".item-info .product-item-name");
    private final By productInfo = By.className("item-info");
    private final By productTitle = By.className("product-item-name");

    public CartPage(WebDriver webDriver) {
        super(webDriver);

        if (!webDriver.getCurrentUrl().equals("https://magento.softwaretestingboard.com/checkout/cart/")) {
            throw new IllegalStateException(
                    "This is not the cart page, current page is: " + webDriver.getCurrentUrl()
            );
        }
    }

    public boolean productIsPresent(String productName) {
        var titles = webDriver.findElements(this.titles);
        for (WebElement title : titles) {
            if (title.getText().equals(productName)) return true;
        }
        return false;
    }

    public boolean variantIsPresent(String productName, String size, String colour) {
        var products = webDriver.findElements(productInfo);
        for (WebElement product : products) {
            String name = product.findElement(productTitle).getText();
            if (name.equals(productName)) {
                var variantAttributes = product.findElements(By.tagName("dd")).stream().map(WebElement::getText).toList();
                if (variantAttributes.get(0).equals(size) && variantAttributes.get(1).equals(colour)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer getProductQty(String productName) {
        var products = webDriver.findElements(productInfo);
        for (WebElement product : products) {
            String name = product.findElement(productTitle).getText();
            if (name.equals(productName)) {
                String qty = product.findElement(By.cssSelector(".input-text.qty")).getAttribute("value");
                return Integer.parseInt(qty);
            }
        }
        return null;
    }
}
