package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Dropdown_AutoSuggestive extends Base {

    private static final Logger log = LogManager.getLogger(Dropdown_AutoSuggestive.class.getName());

    @Test
    public void autoSuggestiveDropdown(){
        driver.get("https://www.azal.az/en/");
        WebElement clearTextSign = driver.findElement(By.xpath("//a[@class='btn-clear']//i[@class='fa fa-times']"));
        clearTextSign.click();
        WebElement from = driver.findElement(By.id("departure1"));
        from.sendKeys("Mi");
        BrowserUtil.wait(2);
        from.sendKeys(Keys.DOWN); // will point to 2nd option, because default points to 1st
        from.sendKeys(Keys.DOWN); // will point to 3rd option
        from.sendKeys(Keys.ENTER);
        BrowserUtil.wait(2);
        Assert.assertTrue(from.getAttribute("value").contains("Minsk, Belarus"));
        WebElement to = driver.findElement(By.id("arrival1"));
        to.sendKeys("G");
        BrowserUtil.wait(2);
        for (int i = 0; i < 3; i++) {
            to.sendKeys(Keys.DOWN); // will point to 4th option, loops 3 times
        }
        to.sendKeys(Keys.ENTER);
        BrowserUtil.wait(2);
        Assert.assertTrue(to.getAttribute("value").contains("Baku, Heydar Aliyev, Azerbaijan"));
        BrowserUtil.wait(2);
    }


}
