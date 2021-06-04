package com.business.Tests;

import com.business.Utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class scenarios_in_JavaScript extends Base {

    @Test
    public void sendingText_to_editBox(){
        driver.get("https://www.etsy.com/");

        // 1st way in case of element can be located by id
        WebElement element = driver.findElement(By.id("global-enhancements-search-query"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("document.getElementById('global-enhancements-search-query').value='I entered value via Java Script'");
        BrowserUtil.wait(2);

        element.clear();
        BrowserUtil.wait(2);

        // 2nd way in case of element no having unique id and located by xpath
        WebElement element1 = driver.findElement(By.xpath("//input[@class='wt-input wt-input-btn-group__input global-enhancements-search-input-btn-group__input wt-pr-xs-7']"));
        JavascriptExecutor jse1 = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].value='I entered value via Java Script second time'", element1);

        BrowserUtil.wait(3);
    }

    @Test
    public void generatingAlertPopUp(){
        driver.get("https://www.etsy.com/");
        JSUtil.generateJSAlert("Assalamu Aleykum");
        BrowserUtil.wait(3);
        driver.switchTo().alert().accept();
    }

    @Test
    public void handling_check_Box(){
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        BrowserUtil.wait(3);
        WebElement checkBox = driver.findElement(By.id("gwt-debug-cwCheckBox-Monday-input"));
        BrowserUtil.wait(3);
        System.out.println(checkBox.isSelected());    // false
        JSUtil.checkingBox("gwt-debug-cwCheckBox-Monday-input");
        BrowserUtil.wait(3);
        System.out.println(checkBox.isSelected());     // true
        JSUtil.un_checkingBox("gwt-debug-cwCheckBox-Monday-input");
        BrowserUtil.wait(3);
        System.out.println(checkBox.isSelected());     // false
    }


    @Test
    public void refreshingPage(){
        driver.get("https://www.etsy.com/");
        BrowserUtil.wait(3);
        JSUtil.refreshPageByJS();
        BrowserUtil.wait(3);
    }


    @Test
    public void getting_title_of_webPage(){
        driver.get("https://www.etsy.com/");
        BrowserUtil.wait(3);
        System.out.println(JSUtil.getTitleOfPageByJS());
        BrowserUtil.wait(3);
    }

    @Test
    public void getting_innerText_of_webPage(){
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        BrowserUtil.wait(3);
        System.out.println(JSUtil.getting_innerText());
        BrowserUtil.wait(3);
    }

    @Test
    public void getting_domain_of_webPage(){
        driver.get("https://www.youtube.com/");
        BrowserUtil.wait(3);
        System.out.println(JSUtil.getting_domain());
        BrowserUtil.wait(3);
    }

    @Test
    public void getting_URL_of_webPage(){
        driver.get("https://www.youtube.com/");
        BrowserUtil.wait(3);
        System.out.println(JSUtil.getting_URL());
        BrowserUtil.wait(3);
    }

    @Test
    public void navigating_to_dif_URL(){

        BrowserUtil.wait(3);
        JSUtil.navigate_to_different_URL("https://www.facebook.com/");
        BrowserUtil.wait(3);
    }



    @Test
    public void scrolling(){
        BrowserUtil.wait(3);
        JSUtil.navigate_to_different_URL("https://www.amazon.com/");
        BrowserUtil.wait(3);
        JSUtil.scrollingUp(1500);        // scrolling down
        BrowserUtil.wait(3);
        JSUtil.scrollingUp(-1500);      // scrolling up
        BrowserUtil.wait(3);
    }


    // scrolling up
    @Test
    public void scrolling_bottom(){
        BrowserUtil.wait(3);
        JSUtil.navigate_to_different_URL("https://www.amazon.com/");
        BrowserUtil.wait(3);
       JSUtil.scroll_down_untill_bottom();     // scrolling up
        BrowserUtil.wait(3);
    }


    @Test
    public void scrollToElement(){
        BrowserUtil.wait(3);
        JSUtil.navigate_to_different_URL("https://www.amazon.com/");
        BrowserUtil.wait(3);
        JSUtil.scrollToElement(driver.findElement(By.xpath("//*[contains(text(),'Amazon Payment Products')]")));
        BrowserUtil.wait(3);
    }


}
