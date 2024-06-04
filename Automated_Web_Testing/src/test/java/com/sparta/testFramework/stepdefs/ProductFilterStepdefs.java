package com.sparta.testFramework.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductFilterStepdefs extends abstractStepdef{
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
    @When("the customer navigates to the {string} category")
    public void theCustomerNavigatesToTheCategory(String category) {
        String categoryUrl = constructCategoryUrl(category);
        System.out.println("Navigating to URL: " + categoryUrl);
        webDriver.get(categoryUrl);
    }

    @When("the customer selects the {string} subcategory under {string}")
    public void theCustomerSelectsTheSubcategory(String subcategory, String category) {
        String subcategoryUrl =  constructSubcategoryUrl(category, subcategory);
        System.out.println("Navigating to URL: " + subcategoryUrl);
        webDriver.get(subcategoryUrl);
    }

    @When("the customer selects the {string} category")
    public void theCustomerSelectsTheCategory(String category) {
        WebElement categoryCheckbox = webDriver.findElement(By.cssSelector("input[type='checkbox'][value='" + category + "']"));
        if (!categoryCheckbox.isSelected()) {
            categoryCheckbox.click();
        }
    }
    
    @Then("the products in the {string} category should be displayed")
    public void theProductsInTheCategoryShouldBeDisplayed(String category) {
        WebElement categoryHeader = webDriver.findElement(By.cssSelector(".page-title span"));
        assertTrue(categoryHeader.getText().contains(category));
    }

    @Then("the products in the {string} subcategory under {string} should be displayed")
    public void theProductsInTheSubcategoryUnderCategoryShouldBeDisplayed(String subcategory, String category) {
        WebElement linkElement = webDriver.findElement(By.linkText("Cassius Sparring Tank"));
        assertTrue(linkElement.getText().equals("Cassius Sparring Tank"));

    }

    @Then("the products in both {string} and {string} categories should be displayed")
    public void theProductsInBothCategoriesShouldBeDisplayed(String category1, String category2) {
        List<WebElement> products = webDriver.findElements(By.cssSelector(".product-item"));
        assertFalse("No products found for the selected categories", products.isEmpty());
    }

    public String constructCategoryUrl(String category) {
        return "https://magento.softwaretestingboard.com/" + category.toLowerCase().replace(" ", "-") + ".html";
    }

    public String constructSubcategoryUrl(String category, String subcategory) {
        return "https://magento.softwaretestingboard.com/" + category.toLowerCase().replace(" ", "-") + "/" + subcategory.toLowerCase().replace(" ", "-") + "-" + category.toLowerCase() + ".html";
    }

    @Then("a message indicating {string} should be displayed")
    public void aMessageIndicatingShouldBeDisplayed(String expectedErrorMessage) {
        WebElement errorMessageElement = webDriver.findElement(By.cssSelector(".error-message")); // Adjust selector as needed
        String actualErrorMessage = errorMessageElement.getText();
        assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }
}
