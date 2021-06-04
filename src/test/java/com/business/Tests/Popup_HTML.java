package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import com.business.Utilities.JSUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class Popup_HTML extends Base {

    @Test
    public void htmlPopup() {
        driver.get("https://www.primefaces.org/showcase/ui/overlay/confirmDialog.xhtml");
        driver.findElement(By.cssSelector(".ui-button-text.ui-c")).click();
        WebElement alertBox = driver.findElement(By.xpath("//span[@class='ui-button-icon-left ui-icon ui-c pi pi-check']"));
        //alertBox.click();
        JSUtil.clickElementByJS(alertBox);
        BrowserUtil.wait(2);
    }

}
