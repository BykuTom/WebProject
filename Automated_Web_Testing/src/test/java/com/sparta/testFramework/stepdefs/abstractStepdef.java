package com.sparta.testFramework.stepdefs;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class abstractStepdef {

    protected static ChromeDriverService service;
    protected static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";

    protected WebDriver webDriver;

    public ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        //V issues with compatibility with actions due to headless option V
        //options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080","--ignore-certificate-errors","--no-sandbox", "--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.setBinary("C:\\Program Files (x86)\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
        return options;
    }

}
