package com.sparta.testFramework.stepdefs;

import com.sparta.testFramework.lib.pages.CheckoutPage;
import com.sparta.testFramework.lib.pages.PaymentPage;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class CheckoutStepDefs {
    private static ChromeDriverService service;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";
    private WebDriver webDriver;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private PaymentPage paymentPage;

    public ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
//        options.addArguments("--headless");
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

    @Given("I have item\\(s) in my cart {string}")
    public void iHaveItemSInMyCart(String productQueries) {
        String[] queries = productQueries.split(", *");
        for (String query : queries) {
            goToProductPage(query);
            productPage.addToCart();
        }
    }

    @And("I am on the checkout page")
    public void iAmOnTheCheckoutPage() {
        checkoutPage = productPage.goToCheckoutPage();
    }

    @When("I enter valid UK credentials {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void iEnterValidUKCredentials(
            String email, String fName, String lName, String street, String city, String mobile, String delivery
    ) {
        checkoutPage.enterEmail(email);
        checkoutPage.enterFirstName(fName);
        checkoutPage.enterLastName(lName);
        checkoutPage.enterAddressLine1(street);
        checkoutPage.enterCity(city);
        checkoutPage.enterCountry("United Kingdom");
        checkoutPage.enterMobile(mobile);
        checkoutPage.selectDelivery(delivery);
    }

    @And("I click the next button")
    public void iClickTheNextButton() {
         checkoutPage.clickNext();
    }

    @Then("the payment page is displayed")
    public void thePaymentPageIsDisplayed() {
        paymentPage = new PaymentPage(webDriver);
    }

    @And("the entered details are displayed {string}, {string}, {string}, {string}")
    public void theEnteredDetailsAreDisplayed(String name, String street, String city, String mobile) {
        String details = paymentPage.getDetails();
        assertThat(details, containsString(name));
        assertThat(details, containsString(street));
        assertThat(details, containsString(city));
        assertThat(details, containsString(mobile));
    }
}
