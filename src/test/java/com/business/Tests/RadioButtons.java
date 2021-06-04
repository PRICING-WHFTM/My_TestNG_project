package com.business.Tests;


import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RadioButtons extends Base {

    /**
     * http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwRadioButton
     * check blue, verify blue checked but red not checked
     * then uncheck blue and check red, and verify blue unchecked and red checked
     */
    @Test

    public void verifyRadioButton() {

        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwRadioButton");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement blue = driver.findElement(By.id("gwt-debug-cwRadioButton-color-blue-input"));
        WebElement red = driver.findElement(By.id("gwt-debug-cwRadioButton-color-red-input"));
        blue.click();
        Assert.assertTrue(blue.isSelected());
        Assert.assertFalse(red.isSelected());
        blue.click();
        red.click();
        Assert.assertTrue(red.isSelected());
        Assert.assertFalse(blue.isSelected());

    }


}
