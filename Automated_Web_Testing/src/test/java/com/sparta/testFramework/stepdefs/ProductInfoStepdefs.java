package com.sparta.testFramework.stepdefs;

public class ProductInfoStepdefs extends abstractStepdef {
    @Given("the customer is on the homepage")
    public void theCustomerIsOnTheHomepage() {
        getWebDriver().get("https://magento.softwaretestingboard.com/");
    }

    @When("the customer searches for a {string}")
    public void theCustomerSearchesForA(String specificItem) {
        WebElement searchBox = getWebDriver().findElement(By.id("search"));
        searchBox.sendKeys(specificItem);
        searchBox.submit();
    }

    @When("the customer clicks on the product link for a {string}")
    public void theCustomerClicksOnTheProductLinkForA(String specificItem) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.product-item-link")));
        productLink.click();
    }

    @Then("the customer should be redirected to the product page for {string}")
    public void theCustomerShouldBeRedirectedToTheProductPageFor(String specificItem) {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 10);
        wait.until(ExpectedConditions.urlContains(specificItem));
    }

    @Then("the product page should display the item details, price, and add to cart button")
    public void theProductPageShouldDisplayTheItemDetailsPriceAndAddToCartButton() {
        WebElement productTitle = getWebDriver().findElement(By.cssSelector("h1.page-title span"));
        WebElement productPrice = getWebDriver().findElement(By.cssSelector(".price"));
        WebElement addToCartButton = getWebDriver().findElement(By.id("product-addtocart-button"));

        assertTrue(productTitle.isDisplayed());
        assertTrue(productPrice.isDisplayed());
        assertTrue(addToCartButton.isDisplayed());
    }

    private WebDriver getWebDriver() {
        return super.webDriver;
    }
}