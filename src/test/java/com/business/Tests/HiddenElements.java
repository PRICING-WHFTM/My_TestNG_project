package com.business.Tests;

import com.business.Utilities.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HiddenElements extends Base {

    private static final Logger log = LogManager.getLogger(HiddenElements.class.getName());

    @Test
    public void verifyTextDisappears() {
        driver.get("https://www.w3schools.com/howto/howto_js_toggle_hide_show.asp");
        WebElement textFrame = driver.findElement(By.id("myDIV"));
        Assert.assertTrue(textFrame.isDisplayed());
        driver.findElement(By.xpath("//button[contains(text(),'Toggle Hide and Show')]")).click();
        Assert.assertFalse(textFrame.isDisplayed());
//        if (textFrame.isDisplayed()) {
//            Assert.fail();
//        } else {
//            log.info("Element does not exist in the page");
//        }

    }
}
