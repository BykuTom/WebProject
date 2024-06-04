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

public class ProductSearchStepdefs extends abstractStepdef{
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
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.input-text")));
    }

    @When("the customer types {string} into the search bar")
    public void theCustomerTypesIntoTheSearchBar(String productName) {
        WebElement searchBox = webDriver.findElement(By.cssSelector("input.input-text"));
        searchBox.sendKeys(productName);
    }

    @When("clicks the search button")
    public void theCustomerClicksTheSearchButton() {
        WebElement searchBox = webDriver.findElement(By.cssSelector("input.input-text"));
        searchBox.submit();
    }

    @Then("the product {string} should be displayed in the search results")
    public void theProductShouldBeDisplayedInTheSearchResults(String productName) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(productName)));
        assertTrue(productLink.isDisplayed());
    }

    @Then("a message {string} should be displayed")
    public void aMessageShouldBeDisplayed(String message) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + message + "')]")));
        assertTrue(messageElement.isDisplayed());
    }
}
