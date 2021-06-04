package com.business.Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGrid_Remotely_Outside_Framework {

    /**
     * 2 steps to run our tests in SauceLabs/Any ready Selenium Grid
     * > 1) Set ChromeOptions, DesiredCapabilities
     * > 2) Create RemoteWebDriver with HUB url and caps object;
     */

    // In framework level we just need to change browserName in properties file to chrome-remote
    @Test(enabled = false)
    public void buttonClicking() throws MalformedURLException {
        URL url = new URL("http://localhost:4444/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WINDOWS);
        WebDriver driver = new RemoteWebDriver(url, capabilities);
        driver.get("https://www.etsy.com");
        WebElement searchBox = driver.findElement(By.id("global-enhancements-search-query"));
        searchBox.sendKeys("Wooden spoon" + Keys.ENTER);
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("wooden spoon"));
        driver.quit();
    }

}
