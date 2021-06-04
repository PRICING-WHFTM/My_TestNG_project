package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import com.business.Utilities.JSUtil;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Shadow_DOM extends Base {

    @Test
    public void shadow_DOM_elements() {
        driver.get("https://books-pwakit.appspot.com/");
//Note: traditional xpath and css are correct but we can't locate it because it is inside shadow DOM
        //  xpath-> "//input[@id='input']"    css-> "#input" or "input#input"

        //1 .Locate Shadow Host
        WebElement shadowHost = driver.findElement(By.tagName("book-app"));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        WebElement shadowDOM = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
        WebElement shadowRoot = JSUtil.getShadowRoot(shadowHost);
        WebElement appHeader = shadowRoot.findElement(By.tagName("app-header"));
        //Note: After entering to Shadow DOM we can locate elements only with CSS.
        WebElement appToolBar = appHeader.findElement(By.cssSelector("app-toolbar.toolbar-bottom"));
        WebElement book_input_decorator = appToolBar.findElement(By.tagName("book-input-decorator"));
        WebElement input = book_input_decorator.findElement(By.cssSelector("input#input"));
        input.sendKeys("Superman" + Keys.ENTER);
        BrowserUtil.wait(3);
        Assert.assertTrue(input.getAttribute("value").contains("Superman"));
    }

}
