package com.sparta.testFramework.stepdefs;

import com.sparta.testFramework.lib.pages.SignInPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class SignInStepdefs extends abstractStepdef{
    private SignInPage signInPage;
    private static ChromeDriverService service;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
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

    @Given("I am on the sign-in page")
    public void iAmOnTheSignInPage() {
        webDriver.get("https://magento.softwaretestingboard.com/customer/account/login/referer/");
        signInPage = new SignInPage(webDriver);
    };

    @And("I entered my email {string}")
    public void signInEmail(String email) {
        signInPage.enterEmail(email);
    }

    @And("I entered my password {string}")
    public void signInPassword(String password) {
        signInPage.enterPassword(password);
    }

    @When("I click Sign in")
    public void iClickSignIn() {
        signInPage.clickSignIn();
    }

    @Then("I should see an alert containing an invalid account error message")
    public void errorMessage() {
        String errorMessage = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        String actualErrorMessage = webDriver.findElement(new By.ByXPath("//*[contains(text(), 'Please wait and try again later.')]")).getText();
        MatcherAssert.assertThat(errorMessage, Is.is(actualErrorMessage));
    }

    @Then("I should be redirected to my dashboard")
    public void goToDashboard() {
        MatcherAssert.assertThat(signInPage.getUrl(), Is.is("https://magento.softwaretestingboard.com/customer/account/"));
    }
}
