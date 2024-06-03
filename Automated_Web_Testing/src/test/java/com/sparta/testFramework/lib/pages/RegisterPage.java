package com.sparta.testFramework.lib.pages;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class RegisterPage extends WebPage {
    protected WebDriver webDriver;
    private By firstNameBy = new By.ById("firstname");
    private By lastNameBy = new By.ById("firstname");
    private By emailBy = new By.ById("firstname");
    private By passwordBy = new By.ById("password");
    private By passwordConfirmationBy = new By.ById("password-confirmation");
    private By createAccount = new By.ByClassName("action submit primary");
    private By passwordError = new By.ById("password-error");
    private By emailError = new By.ById("email_address-error");

    public RegisterPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterFirstName(String firstName) {
        WebElement firstNameInput = webDriver.findElement(firstNameBy);
        firstNameInput.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameInput = webDriver.findElement(lastNameBy);
        lastNameInput.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement emailInput = webDriver.findElement(emailBy);
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = webDriver.findElement(passwordBy);
        passwordInput.sendKeys(password);
        WebElement passwordConfirmationInput = webDriver.findElement(passwordConfirmationBy);
        passwordConfirmationInput.sendKeys(password);
    }

    public void clickCreateAccount() {
        WebElement createAccountButton = webDriver.findElement(createAccount);
        createAccountButton.click();
    }

    public void checkEmptyFields() {
        String errorMessage = "This is a required field.";
        String emptyFieldMessage = webDriver.findElement(new By.ByXPath("//*[contains(text(), 'This is a required field.')]")).getText();
        MatcherAssert.assertThat(emptyFieldMessage, Is.is(errorMessage));
    }

    public void checkPasswordError() {
        String errorMessage = "Minimum length of this field must be equal or greater than 8 symbols. Leading and trailing spaces will be ignored.";
        String actualErrorMessage = webDriver.findElement(passwordError).getText();
        MatcherAssert.assertThat(errorMessage, Is.is(actualErrorMessage));
    }

    public void checkEmailError() {
        String errorMessage = "Please enter a valid email address (Ex: johndoe@domain.com).";
        String actualErrorMessage = webDriver.findElement(emailError).getText();
        MatcherAssert.assertThat(errorMessage, Is.is(actualErrorMessage));
    }

    public void checkAccountSuccessMessage() {
        String message = "Thank you for registering with Main Website Store.";
        String actualMessage = webDriver.findElement(new By.ByXPath("//*[contains(text(), 'Thank you for registering with Main Website Store.')]")).getText();

        

        MatcherAssert.assertThat(message, Is.is(actualMessage));
    }
}
