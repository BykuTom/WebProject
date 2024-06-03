package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ProductPage extends WebPage{
    private static final By PAGE_TYPE = By.cssSelector("meta[property=\"og:type\"]");
    private static final By ADD_TO_CART = By.id("product-addtocart-button");
    private static final By QUANTITY = By.id("qty");
    private static final By SIZE_SWATCH = By.cssSelector(".swatch-attribute.size");
    private static final By COLOUR_SWATCH = By.cssSelector(".swatch-attribute.color");

    private WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    public ProductPage(WebDriver webDriver) {
        super(webDriver);

        if (!isOnProductPage(webDriver)) {
            throw new IllegalStateException(
                    "This is not a product page, current page is: " + webDriver.getCurrentUrl()
            );
        }
    }

    public static boolean isOnProductPage(WebDriver webDriver) {
        try {
            return webDriver.findElement(PAGE_TYPE).getAttribute("content").equals("product");
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public CartPage goToCartPage() {
        wait.until(not(attributeContains(ADD_TO_CART, "class", "disabled")));
        WebElement cartButton = webDriver.findElement(By.className("showcart"));
        cartButton.click();
        WebElement cartLink = webDriver.findElement(By.className("viewcart"));
        cartLink.click();
        return new CartPage(webDriver);
    }

    public void addToCart() {
        wait.until(not(attributeContains(ADD_TO_CART, "class", "disabled")));
        webDriver.findElement(ADD_TO_CART).click();
    }

    public void setQuantity(int qty) {
        WebElement input = webDriver.findElement(QUANTITY);
        input.sendKeys(Keys.DELETE, "" + qty);
    }

    private void setVariantAttribute(By swatchLocator, String value) {
        wait.until(presenceOfElementLocated(swatchLocator));
        WebElement swatch = webDriver.findElement(swatchLocator);
        WebElement button = swatch.findElement(By.cssSelector("div[option-label=\"" + value + "\"]"));
        button.click();
    }

    public void setSize(String size) {
        setVariantAttribute(SIZE_SWATCH, size);
    }

    public void setColour(String colour) {
        setVariantAttribute(COLOUR_SWATCH, colour);
    }

    public int getCartCounter() {
        wait.until(not(attributeContains(ADD_TO_CART, "class", "disabled")));
        String counterText = webDriver.findElement(By.className("counter-number")).getText();
        return Integer.parseInt(counterText);
    }
}
