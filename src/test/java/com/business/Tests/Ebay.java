package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Ebay extends Base {
    @Test
    public void ebay() {
        driver.get("https://www.ebay.com/");
        driver.findElement(By.id("gh-ac")).sendKeys("iphone" + Keys.ENTER);
        BrowserUtil.wait(3);
        String price = driver.findElement(By.xpath("//ul[@class='srp-results srp-list clearfix']/li[1]/div/div[2]//div[@class='s-item__details clearfix']/div[1]/span")).getText();
        driver.findElement(By.xpath("//ul[@class='srp-results srp-list clearfix']/li[1]/div/div[2]/a/h3")).click();
        // css->.srp-results.srp-list.clearfix>:nth-child(3)>div>:nth-child(2)>a>h3>span
        BrowserUtil.wait(3);
        Select select = new Select(driver.findElement(By.xpath("//select[@name='Color']")));
        select.selectByVisibleText("SILVER");
        BrowserUtil.wait(2);
        driver.findElement(By.id("isCartBtn_btn")).click();
        BrowserUtil.wait(3);
        driver.findElement(By.xpath("//button[contains( text(), 'No thanks')]")).click();
        String cartPrice = driver.findElement(By.xpath("//div[@data-test-id='SUBTOTAL']/span")).getText();
        Assert.assertEquals(price, cartPrice);
    }
}
