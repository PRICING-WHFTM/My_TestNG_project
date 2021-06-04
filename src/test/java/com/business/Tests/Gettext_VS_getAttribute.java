package com.business.Tests;

import com.business.Utilities.Base;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Gettext_VS_getAttribute extends Base {

    /**
     * Task:
     * Go to openxcell.com
     * Locate case studies link
     * Printout class attribute
     * Expected : Mega-menu-link
     */
    @Test
    public void verifyAttributeValue() {

        driver.get("https://www.openxcell.com/");
        WebElement caseStudy = driver.findElement(By.linkText("Case Studies"));
        //getting value of attribute
        String classValue = caseStudy.getAttribute("class");
        if (classValue.equals("mega-menu-link")) {
            System.out.println("Value of class attribute succesfully verified");
            System.out.println("Value of class atribute is " + classValue);
        } else {
            System.out.println("Class attribute is wrong");
        }

    }


    @Test
    public void searcFunnction() {
        driver.get("https://www.google.com/");
        WebElement editbox = driver.findElement(By.name("q"));
        editbox.sendKeys("Audi Q7" + Keys.ENTER);  //after test clicks enter.
        if (driver.findElement(By.name("q")).getAttribute("value").equals("Audi Q7")) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test fail");
        }
    }


}
