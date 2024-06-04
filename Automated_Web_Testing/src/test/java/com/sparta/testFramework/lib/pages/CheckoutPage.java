package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends WebPage{
    private WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));

    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);

        if (!webDriver.getCurrentUrl().startsWith("https://magento.softwaretestingboard.com/checkout")) {
            throw new IllegalStateException(
                    "This is not the checkout page, current page is: " + webDriver.getCurrentUrl()
            );
        }
    }

    private void enterIntoField(By location, String value) {
        wait.until(ExpectedConditions.presenceOfElementLocated(location));
        webDriver.findElement(location).sendKeys(value);
    }

    public void enterEmail(String email) {
        enterIntoField(By.cssSelector("._with-tooltip [id=\"customer-email\"]"), email);
    }

    public void enterFirstName(String name) {
        enterIntoField(By.name("firstname"), name);
    }

    public void enterLastName(String name) {
        enterIntoField(By.name("lastname"), name);
    }

    public void enterAddressLine1(String address) {
        enterIntoField(By.name("street[0]"), address);
    }

    public void enterCity(String city) {
        enterIntoField(By.name("city"), city);
    }

    public void enterCountry(String country) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("country_id")));
        Select select = new Select(webDriver.findElement(By.name("country_id")));
        select.selectByVisibleText(country);
    }

    public void enterMobile(String mobile) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("telephone")));
        webDriver.findElement(By.name("telephone")).sendKeys(mobile);
    }

    public void selectDelivery(String delivery) {
        List<WebElement> deliveryOptions = webDriver.findElements(By.cssSelector("tbody .row"));
        for (WebElement deliveryOption : deliveryOptions) {
            String name = deliveryOption.findElement(By.cssSelector(".col-method[id]")).getText();
            if (name.equals(delivery)) {
                deliveryOption.findElement(By.tagName("input")).click();
                return;
            }
        }
        throw new NoSuchElementException("No delivery option with name: " + delivery);
    }

    public void clickNext() {
        WebElement button = webDriver.findElement(By.cssSelector("button[data-role=\"opc-continue\"]"));
        button.click();
        try {
            wait.until(ExpectedConditions.stalenessOf(button));
        } catch (Exception e) {
            // if the field validation fails button will not go stale
            // if it moves onto the next page it will be stale
        }
    }
}
