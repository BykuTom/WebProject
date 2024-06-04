package com.sparta.testFramework.stepdefs;

import com.sparta.testFramework.lib.pages.PasswordRecoveryPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class PasswordRecoveryStepdefs {
    private PasswordRecoveryPage passwordRecoveryPage;
    private static ChromeDriverService service;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    private WebDriver webDriver;

    public ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        //options.addArguments("--headless");
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

    @Given("I am on the forgot password page")
    public void iAmOnTheSignUpPage() {
        webDriver.get("https://magento.softwaretestingboard.com/customer/account/forgotpassword/");
        passwordRecoveryPage = new PasswordRecoveryPage(webDriver);
    }

    @And("I have entered an email to reset password {string}")
    public void iHaveEnteredAnEmail(String email) {
        passwordRecoveryPage.enterEmail(email);
    }
    @When("I click Reset My Password")
    public void whenIClickResetMyPassword() {
        passwordRecoveryPage.clickResetMyPassword();
    }

    @Then("I should see an alert If there is an account associated with email")
    public void emailAlert() {
        String redirectUrl = "https://magento.softwaretestingboard.com/customer/account/login/referer/";
        MatcherAssert.assertThat(passwordRecoveryPage.getUrl(), Matchers.containsString(redirectUrl));
    }
}
