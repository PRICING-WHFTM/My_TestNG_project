package com.business.Tests;

import com.business.Utilities.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BrushUp extends Base {

    private static Logger log = LogManager.getLogger(BrushUp.class.getName());

    @Test(enabled = true) // website is not stable
    public void orangeLeave() throws InterruptedException {
        
        driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123" + Keys.ENTER);
        Actions actions = new Actions(driver);
        WebElement leave = driver.findElement(By.xpath("//b[text()='Leave']"));
        actions.moveToElement(leave).build().perform();
        WebElement leaveList = driver.findElement(By.linkText("Leave List"));
        leaveList.click();
        WebElement fromDateEditbox = driver.findElement(By.id("calFromDate"));
        fromDateEditbox.click();
        WebElement fromMonthDropdown = driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
        Select selectFromMonth = new Select(fromMonthDropdown);
        selectFromMonth.selectByVisibleText("Sep");
        Thread.sleep(1000);
        WebElement fromYearDropdown = driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
        Select selectYearDropdown = new Select(fromYearDropdown);
        selectYearDropdown.selectByValue("2022");
        Thread.sleep(1000);
        List<WebElement> days = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td//a"));
        for (WebElement day : days) {
            if (day.getText().contains("23")) {
                day.click();
                break;
            }
        }
        Thread.sleep(1000);
        Assert.assertTrue(fromDateEditbox.getAttribute("value").contains("2022-09-23"));
        log.info("verified editbox contains given value");
    }
}
