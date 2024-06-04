package com.sparta.testFramework.stepdefs;

import com.sparta.testFramework.lib.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.is;

public class CompareListStepdefs {
    public CompareListStepdefs() {};

        WebDriver webDriver;
        HomePage homePage;
        private static ChromeDriverService service;
        private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";

        protected Actions actions;

    private By addToCompareButton = By.cssSelector(".product-item:nth-child(1) .tocompare");
    private By radiantTeeSizeL = By.id("option-label-size-143-item-169");
    private By radiantTeeColourOrange = By.id("option-label-color-93-item-56");
    private By productDivs = By.className("product-item-info");
    private By getCompareList = By.linkText("comparison list");
    private By compareListItem1 = By.cssSelector(".product-item-name > a");

    public ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=560,440");
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

    private void moveToAndSelectRadiantTeeSizeL(){
        WebElement radiantTeeSizeSelectL = webDriver.findElement(radiantTeeSizeL);
        actions.moveToElement(radiantTeeSizeSelectL).click().perform();
    }
    private void moveToAndSelectRadiantTeeColourOrange(){
        WebElement radiantTeeColourSelectOrange = webDriver.findElement(radiantTeeColourOrange);
        actions.moveToElement(radiantTeeColourSelectOrange).click().perform();
    }
    private void addRadiantTeeToCompare(){
        WebElement radiantTeeDiv = webDriver.findElements(productDivs).getFirst();
        actions.moveToElement(radiantTeeDiv);
        webDriver.findElement(addToCompareButton).click();
    }

        @Given("the customer is on homepage")
        public void theCustomerIsOnHomepage() {
            webDriver.get("https://magento.softwaretestingboard.com/");
            homePage = new HomePage(webDriver);
            actions = new Actions(webDriver);
        }

        @When("I click add to comparison list")
        public void iClickAddToComparisonList() {
            //moveToAndSelectRadiantTeeSizeL();
            //moveToAndSelectRadiantTeeColourOrange();
            addRadiantTeeToCompare();
        }

        @And("I navigate to comparison list")
        public void iNavigateToComparisonList() {
            webDriver.findElement(getCompareList).click();
    }


    @Then("I should see the item in compare list <{string}>")
    public void iShouldSeeTheItemInCompareList(String itemName) {
        MatcherAssert.assertThat(webDriver.findElement(compareListItem1).getText(), is(itemName));
    }
}
