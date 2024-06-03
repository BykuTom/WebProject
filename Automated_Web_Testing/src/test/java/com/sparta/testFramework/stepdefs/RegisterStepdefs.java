package com.sparta.testFramework.stepdefs;

import com.sparta.testFramework.lib.pages.RegisterPage;
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

//public class RegisterStepdefs extends abstractStepdef{
//    private RegisterPage registerPage;
//    private static ChromeDriverService service;
//    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
//    private WebDriver webDriver;
//
//    public ChromeOptions getChromeOptions(){
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");
//        options.addArguments("--headless");
//        options.addArguments("--remote-allow-origins=*");
//        return options;
//    }
//
//    @BeforeAll
//    public static void beforeAll() throws IOException {
//        service = new ChromeDriverService.Builder()
//                .usingDriverExecutable(new File(DRIVER_LOCATION))
//                .usingAnyFreePort()
//                .build();
//        service.start();
//    }
//
//    @Before
//    public void setUp(){
//        webDriver = new RemoteWebDriver(service.getUrl(), getChromeOptions());
//    }
//
//    @After
//    public void afterEach(){
//        webDriver.quit();
//    }
//
//    @AfterAll
//    public static void afterAll(){
//        service.stop();
//    }
//
//    @Given("I am on the sign-up page")
//    public void iAmOnTheSignUpPage() {
//        webDriver.get("https://magento.softwaretestingboard.com/customer/account/create/");
//        registerPage = new RegisterPage(webDriver);
//    }
//
//    @And("I have entered my first name {string}")
//    public void iHaveEnteredMyFirstName(String firstName) {
//        registerPage.enterFirstName(firstName);
//    }
//
//    @And("I have entered my last name {string}")
//    public void iHaveEnteredMyLastName(String lastName) {
//        registerPage.enterLastName(lastName);
//    }
//
//    @And("I have entered an email {string}")
//    public void iHaveEnteredAnEmail(String email) {
//        registerPage.enterEmail(email);
//    }
//
//    @And("I have entered a password {string}")
//    public void iHaveEnteredAPassword(String password) {
//        registerPage.enterPassword(password);
//    }
//
//    @When("I click Create an Account")
//    public void iClickCreateAnAccount() {
//        registerPage.clickCreateAccount();
//    }
//
//    @Then("I should be redirected to my profile dashboard and see an alert Thank you for registering with Main Website Store")
//    public void iShouldBeRedirectedToMyDashboard() {
//        registerPage.checkAccountSuccessMessage();
//    }
//
//    @Then("I should see an alert containing the error This is a required field")
//    public void thisIsARequiredField() {
//        registerPage.checkEmptyFields();
//    }
//
//    @Then("I should see an alert containing the error message Minimum length of this field must be equal or greater than 8 symbols")
//    public void minimumLengthOfThisField8Symbols() {
//        registerPage.checkPasswordError();
//    }
//
//    @Then("I should see an alert containing the error message")
//    public void pleaseEnterAValidEmailAddress() {
//        registerPage.checkEmailError();
//    }
//
//
//}
