package com.sparta.testFramework.stepdefs;

import com.sparta.testFramework.lib.pages.CartPage;
import com.sparta.testFramework.lib.pages.ProductPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BasketStepDefs {
    private static ChromeDriverService service;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";
    private ProductPage productPage;
    private CartPage cartPage;

    private WebDriver webDriver;

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

    private void goToProductPage(String productQuery) {
        webDriver.get(BASE_URL + productQuery + ".html");
        productPage = new ProductPage(webDriver);
    }

    @Given("I am on the {string} product page")
    public void iAmOnTheProductPage(String productQuery) {
        goToProductPage(productQuery);
    }

    @When("I click add to cart")
    public void iClickAddToCart() {
        productPage.addToCart();
    }

    @Then("the cart count is incremented to {int}")
    public void theCartCountIsIncrementedTo(int count) {
        assertThat(productPage.getCartCounter(), is(count));
    }

    @And("the item {string} appears on the cart page")
    public void theItemAppearsOnTheCartPage(String product) {
        cartPage = productPage.goToCartPage();
        assertThat(cartPage.productIsPresent(product), is(true));
    }

    @When("I set the products quantity to {int}")
    public void iSetTheProductsQuantityTo(int qty) {
        productPage.setQuantity(qty);
    }

    @And("the item {string}'s quantity attribute is {int}")
    public void theItemSQuantityAttributeIs(String product, int qty) {
        assertThat(cartPage.getProductQty(product), is(qty));
    }

    @When("I click add to cart {int} times")
    public void iClickAddToCartTimes(int times) {
        for (int i = 0; i < times; i++) {
            productPage.addToCart();
        }
    }

    @And("I go the product page {string}")
    public void iGoTheProductPage(String productQuery) {
        goToProductPage(productQuery);
    }

    @When("I set size to {string}")
    public void iSetSizeTo(String size) {
        productPage.setSize(size);
    }

    @And("I set colour to {string}")
    public void iSetColourTo(String colour) {
        productPage.setColour(colour);
    }

    @And("the variant {string} {string} {string} appears on the cart page")
    public void theVariantAppearsOnTheCartPage(String product, String size, String colour) {
        cartPage = productPage.goToCartPage();
        assertThat(cartPage.variantIsPresent(product, size, colour), is(true));
    }
}
