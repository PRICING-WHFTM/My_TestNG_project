package com.business.Tests;

import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditBoxes extends Base {


    @Test(invocationCount = 3)
    public void editBoxTesting() {
        driver.get("https://the-internet.herokuapp.com/forgot_password");
        WebElement editBox = driver.findElement(By.id("email"));
        Assert.assertTrue(editBox.isDisplayed());
        editBox.sendKeys("anar@gmail.com" + Keys.ENTER);
        //editbox.submit(); --> Works only form elements inside form tag
        String notification = driver.findElement(By.id("content")).getText();
        Assert.assertEquals(notification, "Your e-mail's been sent!");
    }
}
