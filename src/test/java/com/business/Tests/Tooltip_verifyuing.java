package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tooltip_verifyuing extends Base {

    @Test
    public void verifyTooltipMessage (){
        driver.get("http://omayo.blogspot.com/");
        WebElement elem = driver.findElement(By.xpath("//input[@class='gsc-search-button']"));
        actions.moveToElement(elem).build().perform();
        BrowserUtil.wait(3);
        Assert.assertEquals(elem.getAttribute("title"),"search");

    }
}
