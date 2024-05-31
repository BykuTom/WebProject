package com.sparta.testFramework.lib.pages;

import org.openqa.selenium.WebDriver;

public abstract class WebPage {
    protected WebDriver webDriver;

    public WebPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void refresh(){
        webDriver.navigate().refresh();
    }

    public void waitFor(int timeoutInSeconds) throws InterruptedException {
        Thread.sleep(timeoutInSeconds * 1000); // Wait in milliseconds
    }

//    public <T> void waitForExpectedCondition(int timeoutInSeconds, ExpectedConditions<T> expectedCondition)
//            throws InterruptedException, TimeoutException {
//        new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds)).until(expectedCondition);
//    }
}
