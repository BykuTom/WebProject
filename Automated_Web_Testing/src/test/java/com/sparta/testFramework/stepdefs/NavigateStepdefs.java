package com.sparta.testFramework.stepdefs;

import com.sparta.testFramework.lib.pages.CreateAccountPage;
import com.sparta.testFramework.lib.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class NavigateStepdefs extends abstractStepdef {

    private HomePage homePage;
    private CreateAccountPage createAccount;


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

    @Given("I am on the home page")
    public void iAmOnTheHomePage(){
        webDriver.get("https://magento.softwaretestingboard.com/");
        homePage = new HomePage(webDriver);
    }
    @When("I click on 'Create an Account' link")
    public void iClickOnCreateLink(){
        createAccount = homePage.goToCreateAccountPage();
    }
    @Then("I am taken to an account creation page")
    public void iAmOnTheCreationPage(){
        MatcherAssert.assertThat(createAccount.getUrl(), Is.is("https://magento.softwaretestingboard.com/customer/account/create/"));
    }
}
