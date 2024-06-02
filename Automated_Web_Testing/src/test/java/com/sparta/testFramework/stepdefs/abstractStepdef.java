package com.sparta.testFramework.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class abstractStepdef {

    private static ChromeDriverService service;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";

    WebDriver webDriver;

    public ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        return options;
    }


    public abstractStepdef() {
        if (service == null) {
            try {
                startService();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setupWebDriver();
    }

    private void startService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(DRIVER_LOCATION))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    private void setupWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");
        webDriver = new RemoteWebDriver(service.getUrl(), options);
    }

    protected void quitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    protected static void stopService() {
        if (service != null) {
            service.stop();
        }
    }

}
