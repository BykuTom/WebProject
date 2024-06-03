package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

public class CartPage extends WebPage {
    private static final By PRODUCTS = By.cssSelector(".cart.item");
    private static final By TITLE = By.className("product-item-name");
    private static final By QUANTITY = By.cssSelector(".input-text.qty");
    private static final By COUNTER = By.className("counter-number");

    private WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    public CartPage(WebDriver webDriver) {
        super(webDriver);

        if (!webDriver.getCurrentUrl().equals("https://magento.softwaretestingboard.com/checkout/cart/")) {
            throw new IllegalStateException(
                    "This is not the cart page, current page is: " + webDriver.getCurrentUrl()
            );
        }
    }

    private WebElement getProductEntry(String productName) {
        for (WebElement product : webDriver.findElements(PRODUCTS)) {
            String name = product.findElement(TITLE).getText();
            if (name.equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public boolean productIsPresent(String productName) {
        return getProductEntry(productName) != null;
    }

    public boolean variantIsPresent(String productName, String size, String colour) {
        var products = webDriver.findElements(PRODUCTS);
        for (WebElement product : products) {
            String name = product.findElement(TITLE).getText();
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
        WebElement product = getProductEntry(productName);
        String qty = product.findElement(QUANTITY).getAttribute("value");
        return Integer.parseInt(qty);
    }

    public void setProductQty(String productName, int qty) {
        WebElement product = getProductEntry(productName);
        product.findElement(QUANTITY).sendKeys(Keys.DELETE, "" + qty);
    }

    public void clickUpdate() {
        WebElement update = webDriver.findElement(By.className("update"));
        update.click();
        wait.until(ExpectedConditions.stalenessOf(update));
    }

    public void deleteProduct(String productName) {
        WebElement product = getProductEntry(productName);
        product.findElement(By.className("action-delete")).click();
    }

    public String getEmptyCartMsg() {
        return webDriver.findElement(By.className("cart-empty")).getText();
    }

    public int getCartCounter() {
        wait.until(ExpectedConditions.textMatches(COUNTER, Pattern.compile("\\d+")));
        String counterText = webDriver.findElement(COUNTER).getText();
        return Integer.parseInt(counterText);
    }
}
