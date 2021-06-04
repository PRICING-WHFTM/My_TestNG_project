package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Dropdown_Dynamic extends Base {

    private static final Logger log = LogManager.getLogger(Dropdown_Dynamic.class.getName());

    /**
     * Note : If we work with dropdowns which one appears only after
     * selecting value in previous dropdowns (as airline dropdowns (not dates) )
     * from and to tables then we have to understand the second tab is exact copy of first table,
     * to locate second element we have to use below syntax:
     * (//a[@value='DEL'])[2]  -> (first xpath inside parentheses)[2] ,index 2 means in second table
     */

    @Test // not run with headless
    public void dynamicDropdownCalendarType() {
        log.info("Dynamic Dropdown testing");
        driver.get("https://www.spicejet.com");
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
        BrowserUtil.wait(1);
        WebElement from = driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
        from.click();
        WebElement fromCity = driver.findElement(By.xpath("//a[@value='MAA']"));
        fromCity.click();
        BrowserUtil.wait(1);
        Assert.assertTrue(from.getAttribute("value").equalsIgnoreCase("Chennai (MAA)"));
        log.info(from.getAttribute("value"));
        WebElement to = driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT"));
        to.click();                // pay attention !
        WebElement toCity = driver.findElement(By.xpath("(//a[@value='DEL'])[2]"));
        toCity.click();
        BrowserUtil.wait(1);
        log.info(to.getAttribute("value"));
        Assert.assertTrue(to.getAttribute("value").equalsIgnoreCase("Delhi (DEL)"));
        log.info("test passed");

        // -------- or for each table we can use //parent //child xpath (space in the middle)------
        //driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();

    }
}



