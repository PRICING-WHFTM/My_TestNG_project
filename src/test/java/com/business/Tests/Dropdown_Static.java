package com.business.Tests;

import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Dropdown_Static extends Base {

    /**
     * Static dropdown is a dropdown which has select tag.
     * We just locate the element itself, and using Select class
     * we can make choice by index, value, and visibleText
     * .getFirstSelectedOption().getText - works with visibleText
     */

    @Test
    public void checkDropdowns() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdown);
        // How many elements in dropdown list
        System.out.println("Dropdown elements size is -> " + select.getOptions().size());
        // Get options by name
        List<WebElement> optionsList = select.getOptions();
        System.out.println("All options are below :");
        for (WebElement option : optionsList) {
            System.out.println(option.getText());
        }
        // Get default selected option by default
        System.out.println("By default selected in dropdown - > " + select.getFirstSelectedOption().getText());
        // Select another option
        select.selectByVisibleText("Option 1");
        // Verify Option 1 selected
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option 1");
    }
}
