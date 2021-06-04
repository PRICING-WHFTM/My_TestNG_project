package com.business.Tests;

import com.business.Utilities.BrowserUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class SauceLabs_Testing {
    /**
     * 2 steps to run our tests in SauceLabs/Any ready Selenium Grid
     * --> 1) Create DesiredCapabilities object and specify what type of OS and Browser you want to run in.
     * --> 2) Create RemoteWebDriver with HUB url and caps object;
     */


    @Test
    public void buttonClicking() throws MalformedURLException {
        // sauceLabs account url
        URL url = new URL("https://Xose1:af4c088f-4075-4f97-b1bd-6d2fd7210e19@ondemand.us-west-1.saucelabs.com:443/wd/hub");
        //1 step -Setting DesiredCapabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.MAC);
        caps.setBrowserName("Safari");
        caps.setVersion("latest");
        // 2. Initialize driver
        WebDriver driver = new RemoteWebDriver(url, caps);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.etsy.com");
        WebElement searchBox = driver.findElement(By.id("global-enhancements-search-query"));
        searchBox.sendKeys("Wooden spoon" + Keys.ENTER);
        BrowserUtil.wait(2);
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("wooden spoon"));
        driver.close();
        driver.quit();
    }
}
