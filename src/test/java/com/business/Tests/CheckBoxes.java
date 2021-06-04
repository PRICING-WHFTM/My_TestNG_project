package com.business.Tests;

import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxes extends Base {
    /**
     * select on monday only if it is not select
     */
    @Test
    public void checkBoxTesting() {
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        int allCheckBoxesCount = driver.findElements(By.cssSelector("*[type='checkbox']")).size();
        int allCheckBoxesCount2 = driver.findElements(By.xpath("//*[@type='checkbox']")).size();
        System.out.println(allCheckBoxesCount);
        System.out.println(allCheckBoxesCount2);
        WebElement monday = driver.findElement(By.id("gwt-debug-cwCheckBox-Monday-input"));
        Assert.assertFalse(monday.isSelected());
        monday.click();
        Assert.assertTrue(monday.isSelected());
    }

}
