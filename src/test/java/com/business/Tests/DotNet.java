package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import com.business.Utilities.JSUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


public class DotNet extends Base {
    private static final Logger log = LogManager.getLogger(DotNet.class.getName());

    @Test(enabled = false)
    public void validateOutput() {
        driver.get("https://dotnetfiddle.net/");
        driver.findElement(By.id("run-button")).click();
        BrowserUtil.wait(2);
        String output = driver.findElement(By.id("output")).getText();
        Assert.assertEquals("Hello World", output);
    }

    /**
     * (If your first name starts with letter “A-B-C-D-E”):
     * Select NuGet Packages: nUnit (3.12.0)
     * Check that nUnit package is added
     */

    @Test
    public void validateTest() {
        driver.get("https://dotnetfiddle.net/");
        String name = "A";
        char begin = name.charAt(0);
        if (begin == 'A' || begin == 'B' || begin == 'C' || begin == 'D' || begin == 'E') {
            driver.findElement(By.cssSelector("input[placeholder='Package name...']")).sendKeys("nUnit");
            BrowserUtil.wait(5);
            WebElement nUnit = driver.findElement(By.cssSelector("a[package-id='NUnit']"));
            actions.moveToElement(nUnit).build().perform();
            BrowserUtil.wait(5);
            WebElement version312 = driver.findElement(By.xpath("//ul[@class='ui-menu ui-widget ui-widget-content ui-corner-all']//li[1]/a[contains(text(),'3.12.0')]"));
            JSUtil.clickElementByJS(version312);
            BrowserUtil.wait(15);
            driver.findElement(By.cssSelector(".ui-icon.ui-icon-closethick")).click();
            BrowserUtil.wait(1);
            driver.findElement(By.cssSelector("input[placeholder='Package name...']")).sendKeys("nUnit");
            BrowserUtil.wait(5);
            nUnit = driver.findElement(By.cssSelector("a[package-id='NUnit']"));
            actions.moveToElement(nUnit).build().perform();
            BrowserUtil.wait(5);
            version312 = driver.findElement(By.xpath("//ul[@class='ui-menu ui-widget ui-widget-content ui-corner-all']//li[1]/a[contains(text(),'3.12.0')]"));
            Assert.assertTrue(version312.findElements(By.tagName("i")).size() > 0);
            log.info(" \"3.12.0 version\" is added and check sign is displayed");
        } else if (begin == 'F' || begin == 'G' || begin == 'H' || begin == 'I' || begin == 'J' || begin == 'K') {
            driver.findElement(By.id("Share")).click();
            BrowserUtil.wait(2);
            Assert.assertTrue(driver.findElement(By.id("ShareLink")).getAttribute("value").startsWith("https://dotnetfiddle.net/"));
            log.info("Shared link starts with \"https://dotnetfiddle.net/ \" ");

        } else if (begin == 'L' || begin == 'M' || begin == 'N' || begin == 'O' || begin == 'P') {
            driver.findElement(By.xpath("//button[@class='btn btn-default btn-xs btn-sidebar-toggle']")).click();
            BrowserUtil.wait(2);
            WebElement options = driver.findElement(By.xpath("//div[@class='text-center']/strong[contains(text(),'Options')]"));
            Assert.assertFalse(options.isDisplayed());
            log.info(" \"Options\" is hidden and not displayed in the page");
        } else if (begin == 'Q' || begin == 'R' || begin == 'S' || begin == 'T' || begin == 'U') {
            driver.findElement(By.id("save-button")).click();
            BrowserUtil.wait(2);
            WebElement email = driver.findElement(By.id("Email"));
            Assert.assertTrue(email.isDisplayed());
            log.info(" \"Login page\" is displayed");
        } else {
            driver.findElement(By.xpath("//div[@class='navbar-center-container']/div[5]/a")).click();
            BrowserUtil.wait(2);
            WebElement backEditor = driver.findElement(By.xpath("//div[@class='navbar-center-container']/div[1]/a"));
            Assert.assertTrue(backEditor.isDisplayed());
            log.info(" \"Back editor button\" is displayed");
        }
    }


}
