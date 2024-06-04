package com.sparta.testFramework.stepdefs;

import com.sparta.testFramework.lib.pages.CartPage;
import com.sparta.testFramework.lib.pages.ProductPage;
import com.sparta.testFramework.lib.pages.WebPage;
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

import static com.sparta.testFramework.lib.pages.ProductPage.isOnProductPage;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CartStepDefs extends abstractStepdef {
    private static ChromeDriverService service;

    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";
    private ProductPage productPage;
    private CartPage cartPage;

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

    @Then("the cart count is set to {int}")
    public void theCartCountIsIncrementedTo(int count) {
        int cartCount = isOnProductPage(webDriver) ? productPage.getCartCounter() : cartPage.getCartCounter();
        assertThat(cartCount, is(count));
    }

    @And("the item {string} appears on the cart page")
    public void theItemAppearsOnTheCartPage(String product) {
        if (isOnProductPage(webDriver)) {
            cartPage = productPage.goToCartPage();
        }
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
        if (isOnProductPage(webDriver)) {
            cartPage = productPage.goToCartPage();
        }
        assertThat(cartPage.variantIsPresent(product, size, colour), is(true));
    }

    @Given("There is a single item {string} in the cart")
    public void thereIsASingleItemInTheCart(String productQuery) {
        goToProductPage(productQuery);
        productPage.addToCart();
    }

    @And("I am on the cart page")
    public void iAmOnTheCartPage() {
        cartPage = productPage.goToCartPage();
    }

    @When("I click the delete item button for {string}")
    public void iClickTheDeleteItemButtonFor(String product) {
        cartPage.deleteProduct(product);
    }

    @Then("You have no items in your shopping cart is displayed")
    public void youHaveNoItemsInYourShoppingCartIsDisplayed() {
        assertThat(cartPage.getEmptyCartMsg(), containsString("You have no items in your shopping cart."));
    }

    @Given("There are multiple items in the cart {string}")
    public void thereAreMultipleItemsInTheCart(String productQueries) {
        String[] queries = productQueries.split(", *");
        for (String query : queries) {
            goToProductPage(query);
            productPage.addToCart();
        }
    }

    @And("the item {string} is removed")
    public void theItemIsRemoved(String product) {
        assertThat(cartPage.productIsPresent(product), is(false));
    }

    @Given("There is an item {string} in the cart with quantity {int}")
    public void thereIsAnItemInTheCartWithQuantity(String productQuery, int qty) {
        goToProductPage(productQuery);
        productPage.setQuantity(qty);
        productPage.addToCart();
    }

    @When("the quantity value of {string} is overwritten with {int}")
    public void theQuantityValueOfIsOverwrittenWith(String product, int qty) {
        cartPage.setProductQty(product, qty);
    }

    @And("the Update Shopping Cart button is clicked")
    public void theUpdateShoppingCartButtonIsClicked() {
        cartPage.clickUpdate();
    }
}
