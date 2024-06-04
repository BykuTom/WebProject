package com.sparta.testFramework.stepdefs;

import com.sparta.testFramework.lib.pages.CartPage;
import com.sparta.testFramework.lib.pages.MyAccountPage;
import com.sparta.testFramework.lib.pages.ProductPage;
import com.sparta.testFramework.lib.pages.SignInPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UpdateProfileStepdefs {
    private static ChromeDriverService service;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";
    private MyAccountPage myAccountPage;
    private SignInPage signInPage;

    private WebDriver webDriver;

    private By accountLink = By.cssSelector("css=.header:nth-child(2) > .customer-welcome .action");
    private By accountLink2 = By.linkText("My Account");
    private By editButton = By.linkText("Edit Address");
    private By streetField = By.id("street_1");
    private By saveButton = By.cssSelector(".action.save.primary");
    private By streetText = By.tagName("address");

    public ChromeOptions getChromeOptions() {
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
    public void setUp() {
        webDriver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
    }

    @After
    public void afterEach() {
        webDriver.quit();
    }

    @AfterAll
    public static void afterAll() {
        service.stop();
    }

//    private void goToProductPage(String productQuery) {
//        webDriver.get(BASE_URL + productQuery + ".html");
//        myAccountPage = new MyAccountPage(webDriver);
//    }

    public MyAccountPage goToMyAccountPage(){
        webDriver.findElement(accountLink).click();
        webDriver.findElement(accountLink2).click();
        return new MyAccountPage(webDriver);
    }

    public MyAccountPage editAddress(){
        webDriver.findElement(editButton).click();
        return new MyAccountPage(webDriver);
    }

    public MyAccountPage editStreetName(){
        webDriver.findElement(editButton).click();
        return new MyAccountPage(webDriver);
    }

    @Given("I am on the my account page")
    public void iAmOnTheMyAccountPage() {
        webDriver.get("https://magento.softwaretestingboard.com/customer/account/login/referer/");
        signInPage = new SignInPage(webDriver);
        signInPage.enterEmail("bigchungus@nish.com");
        signInPage.enterPassword("BigChungusAmongUs23.");
        signInPage.clickSignIn();
        //
        myAccountPage = new MyAccountPage(webDriver);;
    }

    @When("I click Edit Address")
    public void iClickEditAddress() {
        myAccountPage = editAddress();
    }

    @And("I enter a new address {string}")
    public void iEnterANewAddress(String street) {
        CharSequence cs = street;
        webDriver.findElement(streetField).sendKeys(cs);
        webDriver.findElement(saveButton).click();
   }


    @Then("The address is updated to {string}")
    public void theAddressIsUpdatedTo(String street) {
        assertThat(webDriver.findElement(streetText).getText(), containsString(street));
    }



//    @And("the item {string} appears on the cart page")
//    public void theItemAppearsOnTheCartPage(String product) {
//        cartPage = productPage.goToCartPage();
//        assertThat(cartPage.productIsPresent(product), is(true));
//    }
}
