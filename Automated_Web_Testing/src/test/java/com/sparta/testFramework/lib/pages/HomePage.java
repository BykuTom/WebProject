package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends WebPage{

    private By createAccountLink = By.linkText("Create an Account");
    private By signInLink = By.linkText("Sign In");
    private By saleLink = By.linkText("Sale");
    private By menLink = By.linkText("Men");
    private By topsLink = By.linkText("Tops");
    private By jacketsLink = By.linkText("Jackets");


    private By productDivs = By.className("product-item-info");
    private By addToCartButton = By.className("tocart");
    private By radiantTeeSizeL = By.id("option-label-size-143-item-169");
    private By radiantTeeColourOrange = By.id("option-label-color-93-item-56");


    public HomePage(WebDriver webDriver){
        super(webDriver);
    }

    public CreateAccountPage goToCreateAccountPage(){
        webDriver.findElement(createAccountLink).click();
        return new CreateAccountPage(webDriver);
    }

    public SignInPage goToSignInPage(){
        webDriver.findElement(signInLink).click();
        return new SignInPage(webDriver);
    }

    public SalePage goToSalePage(){
        webDriver.findElement(saleLink).click();
        return new SalePage(webDriver);
    }

    public void hoverOverMensLink() {
        WebElement menElement = webDriver.findElement(menLink);
        actions.moveToElement(menElement).perform();
    }

    public void hoverOverTopsLink() {
        WebElement topsElement = webDriver.findElement(topsLink);
        actions.moveToElement(topsElement).perform();
    }

    public JacketsPage goToJacketsPage() {
        WebElement jacketsElement = webDriver.findElement(jacketsLink);
        actions.moveToElement(jacketsElement).click().perform();
        return new JacketsPage(webDriver);
    }

    private void moveToAndSelectRadiantTeeSizeL(){
        WebElement radiantTeeSizeSelectL = webDriver.findElement(radiantTeeSizeL);
        actions.moveToElement(radiantTeeSizeSelectL).click().perform();
    }
    private void moveToAndSelectRadiantTeeColourOrange(){
        WebElement radiantTeeColourSelectOrange = webDriver.findElement(radiantTeeColourOrange);
        actions.moveToElement(radiantTeeColourSelectOrange).click().perform();
    }
    private void addRadiantTeeToBasket(){
        WebElement radiantTeeDiv = webDriver.findElements(productDivs).getFirst();
        actions.moveToElement(radiantTeeDiv.findElement(addToCartButton)).click().perform();
    }

    public void selectAndAddRadiantTeeToBasket(){
        moveToAndSelectRadiantTeeSizeL();
        moveToAndSelectRadiantTeeColourOrange();
        addRadiantTeeToBasket();
    }


}
