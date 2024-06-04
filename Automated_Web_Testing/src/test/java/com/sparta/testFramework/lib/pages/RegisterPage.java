package com.sparta.testFramework.lib.pages;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class RegisterPage extends WebPage {
    private final By firstNameBy = new By.ById("firstname");
    private final By lastNameBy = new By.ById("lastname");
    private final By emailBy = new By.ById("email_address");
    private final By passwordBy = new By.ById("password");
    private final By passwordConfirmationBy = new By.ById("password-confirmation");
    private final By createAccount = new By.ByCssSelector(".action.submit.primary");
    private final By passwordError = new By.ById("password-error");
    private final By emailError = new By.ById("email_address-error");
    private final By firstNameRequired = new By.ById("firstname-error");
//    private final By lastNameRequired = new By.ById("lastname-error");
//    private final By emailRequired = new By.ById("email_address-error");
//    private final By passwordRequired = new By.ById("password-error");
//    private final By passwordConfirmRequired = new By.ById("password-confirmation-error");

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
        List<WebElement> emptyFieldMessages = webDriver.findElements(firstNameRequired);
        for (WebElement message : emptyFieldMessages) {
            if (message.getText() != null) {
                MatcherAssert.assertThat(message.getText(), Is.is(errorMessage));
            }
        }
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
        String customerUrl = "https://magento.softwaretestingboard.com/customer/account/";
        MatcherAssert.assertThat(customerUrl, Is.is(webDriver.getCurrentUrl()));
    }
}
