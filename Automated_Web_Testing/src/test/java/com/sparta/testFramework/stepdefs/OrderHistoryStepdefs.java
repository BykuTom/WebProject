package com.sparta.testFramework.stepdefs;

import com.sparta.testFramework.lib.pages.AccountDetailsPage;
import com.sparta.testFramework.lib.pages.OrderHistoryPage;
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
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.containsString;

public class OrderHistoryStepdefs extends abstractStepdef{

    private final String orderPageLink = "https://magento.softwaretestingboard.com/sales/order/history/";
    private final String accountPageLink = "https://magento.softwaretestingboard.com/customer/account/";
    private final String signInLink = "https://magento.softwaretestingboard.com/customer/account/login/referer/";
    private AccountDetailsPage accountPage;
    private OrderHistoryPage orderHistoryPage;
    private SignInPage signInPage;

    private String noOrderUserEmail = "Gigi@dee.kfc.Mcdonalds";
    private String noOrderUserPassword = "Gigi@dee.kfc.McdonaldsGigi@dee.kfc.Mcdonalds";

    private String orderUserEmail = "abra@cadabra.com";
    private String orderUserPassword = "InvalidPassword2.";

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

    @Given("I am a user who never ordered any items")
    public void iAmAUserWhoHasntOrderedItems(){
        webDriver.get(signInLink);
        signInPage = new SignInPage(webDriver);
        accountPage = signInPage.goToSignedIn(noOrderUserEmail, noOrderUserPassword);
    }

    @And("I am on My Account page")
    public void iAmOnMyAccountPage(){
        MatcherAssert.assertThat(accountPage.getUrl(), Is.is("https://magento.softwaretestingboard.com/customer/account/"));
    }

    @When("I navigate to my orders page")
    public void iGoToOrdersPage(){
        orderHistoryPage = accountPage.goToOrderHistory();
    }

    @Then("I see a message informing me that 'You have placed no orders")
    public void iSeeAMessageEmpty() throws InterruptedException {
        orderHistoryPage.waitFor(2);
        MatcherAssert.assertThat(orderHistoryPage.getMessageText(), Is.is("You have placed no orders."));
    }


    @When("I try to access the page directly")
    public void iAccessPageDirectly(){
        webDriver.get(orderPageLink);
      orderHistoryPage = new OrderHistoryPage(webDriver);
    }

    @Then("I am being redirected to the sign in page")
    public void iAmOnTheSignInPage(){
        MatcherAssert.assertThat(orderHistoryPage.getUrl(), containsString("https://magento.softwaretestingboard.com/customer/account/login/referer/"));
    }

}
