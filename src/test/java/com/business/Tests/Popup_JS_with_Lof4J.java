package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Popup_JS_with_Lof4J extends Base {

    private static final Logger log = LogManager.getLogger(Popup_JS_with_Lof4J.class.getName());

    @Test
    public void JS_popupTesting() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        log.info("navigated the website");
        driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
        log.info("clicked text generating JS popup");
        BrowserUtil.wait(1);
        String alertText = driver.switchTo().alert().getText();
        log.info("alert presents,alert text is: " + alertText);
        BrowserUtil.wait(2);
        driver.switchTo().alert().dismiss();
        log.info("alert presents,alert dismissed");

    }
}
