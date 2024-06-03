package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class ProductPage extends WebPage{
    private final By pageTypeMeta = By.cssSelector("meta[property=\"og:type\"]");
    private final By addToCartButton = By.id("product-addtocart-button");
    private final By qtyInput = By.id("qty");
    private final By sizeSwatch = By.cssSelector(".swatch-attribute.size");
    private final By colourSwatch = By.cssSelector(".swatch-attribute.color");

    private WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    public ProductPage(WebDriver webDriver) {
        super(webDriver);

        String pageType = webDriver.findElement(pageTypeMeta).getAttribute("content");
        if (pageType == null || !pageType.equals("product")) {
            throw new IllegalStateException(
                    "This is not a product page, current page is: " + webDriver.getCurrentUrl()
            );
        }
    }

    public CartPage goToCartPage() {
        WebElement cartButton = webDriver.findElement(By.className("showcart"));
        cartButton.click();
        WebElement cartLink = webDriver.findElement(By.className("viewcart"));
        cartLink.click();
        return new CartPage(webDriver);
    }

    public void addToCart() {
        wait.until(not(attributeContains(addToCartButton, "class", "disabled")));
        webDriver.findElement(addToCartButton).click();
    }

    public void setQuantity(int qty) {
        WebElement input = webDriver.findElement(qtyInput);
        input.sendKeys(Keys.DELETE, "" + qty);
    }

    private void setVariantAttribute(By swatchLocator, String value) {
        wait.until(presenceOfElementLocated(swatchLocator));
        WebElement swatch = webDriver.findElement(swatchLocator);
        WebElement button = swatch.findElement(By.cssSelector("div[option-label=\"" + value + "\"]"));
        button.click();
    }

    public void setSize(String size) {
        setVariantAttribute(sizeSwatch, size);
    }

    public void setColour(String colour) {
        setVariantAttribute(colourSwatch, colour);
    }

    public int getCartCounter() {
        wait.until(not(attributeContains(addToCartButton, "class", "disabled")));
        String counterText = webDriver.findElement(By.className("counter-number")).getText();
        return Integer.parseInt(counterText);
    }
}
