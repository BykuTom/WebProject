package com.sparta.testFramework.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ProductInfoStepdefs {
    private static ChromeDriverService service;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";

    WebDriver webDriver;

    public ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

    @BeforeAll
    public static void beforeAll() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(DRIVER_LOCATION))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @Before
    public void setUp(){
        webDriver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
    }

    @After
    public void afterEach(){
        webDriver.quit();
    }

    @AfterAll
    public static void afterAll(){
        service.stop();
    }

    @Given("the customer is on the homepage")
    public void theCustomerIsOnTheHomepage() {
        webDriver.get("https://magento.softwaretestingboard.com/");
    }

    @When("the customer searches for a {string}")
    public void theCustomerSearchesForA(String specificItem) {
        WebElement searchBox = webDriver.findElement(By.id("search"));
        searchBox.sendKeys(specificItem);
        searchBox.submit();
    }

    @When("the customer clicks on the product link for a {string}")
    public void theCustomerClicksOnTheProductLinkForA(String specificItem) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.product-item-link")));
        productLink.click();
    }

    @Then("the customer should be redirected to the product page for {string}")
    public void theCustomerShouldBeRedirectedToTheProductPageFor(String specificItem) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(specificItem));
    }

    @Then("the product page should display the item details, price, and add to cart button")
    public void theProductPageShouldDisplayTheItemDetailsPriceAndAddToCartButton() {
        WebElement productTitle = webDriver.findElement(By.cssSelector("h1.page-title span"));
        WebElement productPrice = webDriver.findElement(By.cssSelector(".price"));
        WebElement addToCartButton = webDriver.findElement(By.id("product-addtocart-button"));

        assertTrue(productTitle.isDisplayed());
        assertTrue(productPrice.isDisplayed());
        assertTrue(addToCartButton.isDisplayed());
    }

}