package com.business.Tests;


import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Button extends Base {

    @Test
    public void buttonClicking() throws InterruptedException {
        driver.get("https://www.etsy.com/");
        WebElement searchBox = driver.findElement(By.id("global-enhancements-search-query"));
        searchBox.sendKeys("Wooden spoon");
        WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
        submit.click();
        BrowserUtil.wait(2);
        //avoiding StaleElement exception
        searchBox=driver.findElement(By.id("global-enhancements-search-query"));
        Assert.assertEquals(searchBox.getAttribute("value"), "Wooden spoon");
    }
}
