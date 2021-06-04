package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import com.business.Utilities.JSUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Date_Pick_Airlines extends Base {

    @Test
    public void path2US() {
        driver.get("https://www.path2usa.com/travel-companions");
        BrowserUtil.wait(8);
        WebElement from = driver.findElement(By.id("travel_from"));
        from.sendKeys("Arkan");
        BrowserUtil.wait(2);
        from.sendKeys(Keys.ENTER); // will point 1st option
        Assert.assertEquals(from.getAttribute("value"), "Northwest Arkansas Regional Airport (XNA) Fayetteville");
        WebElement to = driver.findElement(By.id("travel_to"));
        to.sendKeys("JFK");
        BrowserUtil.wait(1);
        to.sendKeys(Keys.ENTER); // will point 1st option
        Assert.assertTrue(to.getAttribute("value").contains("John F. Kennedy International Airport"));
        //Choose July 15
        WebElement travelDate = driver.findElement(By.id("travel_date"));
        travelDate.click();
        WebElement month = driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='datepicker-switch']"));
        WebElement next = driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'»')]"));
        // click next until getting July
        while (!month.getText().contains("July")) {
            next.click();
        }
        // store all days in List, iterate and click 16
        List<WebElement> days = driver.findElements(By.xpath("//td[@class='day']"));
        for (WebElement day : days) {
            if (day.getText().contains("16")) {
                day.click();
                break;
            }
        }
        BrowserUtil.wait(1);
        // Verify date selected correctly
        Assert.assertTrue(travelDate.getAttribute("value").contains("07-16"));
        //Select dropdown from static dropdown
        WebElement weeks = driver.findElement(By.id("datebetween"));
        Select selectWeek = new Select(weeks);
        selectWeek.selectByVisibleText("+ - 2 Weeks");
        Assert.assertEquals(selectWeek.getFirstSelectedOption().getText(), "+ - 2 Weeks");
        WebElement airLine = driver.findElement(By.id("travel_airline"));
        Select selectAirline = new Select(airLine);
        selectAirline.selectByVisibleText("Air Canada");
        Assert.assertEquals(selectAirline.getFirstSelectedOption().getText(), "Air Canada");
        WebElement language = driver.findElement(By.id("travel_language"));
        Select selectLanguage = new Select(language);
        selectLanguage.selectByVisibleText("English");
        Assert.assertEquals(selectLanguage.getFirstSelectedOption().getText(), "English");
        WebElement searchBtn = driver.findElement(By.xpath("//button[contains(text(),'SEARCH')]"));
        // searchBtn.click(); --> ElementClickInterceptedException:
        JSUtil.clickElementByJS(searchBtn);
        Assert.assertTrue(driver.getPageSource().contains("Travel Companions"));


    }
}

