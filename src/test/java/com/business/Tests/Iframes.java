package com.business.Tests;


import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Iframes extends Base {

    @Test
    public void iFrameTesting() {
        driver.get("https://the-internet.herokuapp.com/tinymce");
        WebElement frameElem = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(frameElem);
        WebElement editBox = driver.findElement(By.xpath("//p[contains(text(),'Your content goes here.')]"));
        editBox.clear();
        BrowserUtil.wait(1);
        editBox.sendKeys("I love Java");
        BrowserUtil.wait(1);
        /*
              ---  FIND HOW MANY FRAMES IN THIS PAGE ---
         */
        driver.switchTo().defaultContent();
        System.out.println(driver.findElements(By.tagName("iframe")).size()); //1


    }


}
