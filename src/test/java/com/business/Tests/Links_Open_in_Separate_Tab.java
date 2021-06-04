package com.business.Tests;

import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Links_Open_in_Separate_Tab extends Base {
    /**
     * Find all links in footer area
     * Open each of them in separate tab --> elem.sendKeys(Keys.chord (Keys.CONTROL,Keys.ENTER));
     * Handle all open windows
     * Grab title for each link
     */

    @Test
    public void openLinksInSeparateTab() {

        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        String parentTitle = driver.getTitle();
        WebElement footerArea = driver.findElement(By.id("gf-BIG"));
        List<WebElement> allLinksInFooter = footerArea.findElements(By.tagName("a"));
        // hold control and click enter, will open each link in separate tab , IMPORTANT
        String clickInNewTab = Keys.chord(Keys.COMMAND, Keys.ENTER);
        for (WebElement link : allLinksInFooter) {

            link.sendKeys(clickInNewTab);
        }
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentWindow = it.next();
        while (it.hasNext()) {
            driver.switchTo().window(it.next());
            System.out.println(driver.getTitle());
        }
        driver.switchTo().window(parentWindow);
        Assert.assertEquals(driver.getTitle(), parentTitle);
    }
}
