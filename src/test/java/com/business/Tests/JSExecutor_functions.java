package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import com.business.Utilities.JSUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class JSExecutor_functions extends Base {


    @Test
    public void flashingTheElement() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement dropDown = driver.findElement(By.linkText("Dropdown"));
        JSUtil.flashElement(dropDown, driver);
        BrowserUtil.wait(2);
        WebElement dynamicContent = driver.findElement(By.linkText("Dynamic Content"));
        JSUtil.drawBorder(dynamicContent);
        BrowserUtil.wait(2);
        String title = JSUtil.getTitleOfPageByJS();
        System.out.println(title);
        BrowserUtil.wait(2);
        JSUtil.clickElementByJS(dynamicContent);
        BrowserUtil.wait(2);
        JSUtil.generateJSAlert( "You have clicked the Dynamic Elements link");
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        BrowserUtil.wait(2);
        JSUtil.refreshPageByJS();
        BrowserUtil.wait(2);
        JSUtil.refreshPageByJS();
        BrowserUtil.wait(2);
    }


}
