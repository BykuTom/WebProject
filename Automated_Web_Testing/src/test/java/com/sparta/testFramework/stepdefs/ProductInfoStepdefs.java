package com.sparta.testFramework.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
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

public class ProductInfoStepdefs extends abstractStepdef{
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
        specificItem = "jacket";
        WebElement searchBox = webDriver.findElement(By.cssSelector("input.input-text");
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
        String expectedUrlPart = "catalogsearch/result/?q=" + specificItem;
        wait.until(ExpectedConditions.urlContains(expectedUrlPart));
    }

    @Then("the customer should be redirected to the individual product page for {string}")
    public void theCustomerShouldBeRedirectedToTheIndividualProductPageFor(String specificItem) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement p_productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.page-title span")));
        WebElement p_productPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".price-box.price-final_price")));
        WebElement p_addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title='Add to Cart']")));

        assertTrue(p_productTitle.isDisplayed());
        assertTrue(p_productPrice.isDisplayed());
        assertTrue(p_addToCartButton.isDisplayed());

    }


    @Then("the product page should display the item details, price, and add to cart button")
    public void theProductPageShouldDisplayTheItemDetailsPriceAndAddToCartButton() {
            WebElement productInfo = webDriver.findElement(By.cssSelector(".product-item-info"));
            WebElement productPrice = webDriver.findElement(By.cssSelector(".price-box.price-final_price"));
            WebElement addToCartButton = webDriver.findElement(By.cssSelector("button[title='Add to Cart']"));

            assertTrue(productInfo.isDisplayed());
            assertTrue(productPrice.isDisplayed());
            assertTrue(addToCartButton.isDisplayed());
        }

    @When("the customer searches for an out-of-stock or non-existent item")
    public void theCustomerSearchesForAnOutOfStockOrNonExistentItem() {
        String specificItem = "football"; // Or another item you know is out-of-stock
        WebElement searchBox = webDriver.findElement(By.id("search"));
        searchBox.sendKeys(specificItem);
        searchBox.submit();
    }

    @When("the customer clicks search")
    public void theCustomerClicksSearch() {
        WebElement searchBox = webDriver.findElement(By.cssSelector("input.input-text"));
        searchBox.submit();
    }

    @Then("the customer should be redirected to the error page or receive an error message")
    public void theCustomerShouldBeRedirectedToTheErrorPageOrReceiveAnErrorMessage() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message.notice")));
        assertTrue(errorMessage.isDisplayed());

    }

}