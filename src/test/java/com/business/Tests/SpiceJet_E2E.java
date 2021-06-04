package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import com.business.Utilities.DriverUtil;
import com.business.Utilities.JSUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SpiceJet_E2E extends Base {

    @Test(groups = {"regression"})
    public void spiceJetE2E()  {
        driver.get("https://www.spicejet.com");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement familyCheckBox = driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily"));
        Assert.assertFalse(familyCheckBox.isSelected());
        familyCheckBox.click();
        Assert.assertTrue(familyCheckBox.isSelected());
        WebElement roundTrip = driver.findElement(By.xpath("//table[@id='ctl00_mainContent_rbtnl_Trip']//tbody//tr//td[2]/input"));
        roundTrip.click();
        Assert.assertTrue(roundTrip.isSelected());
        BrowserUtil.wait(2);
        WebElement from = driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
        JSUtil.clickElementByJS(from);
        //BrowserUtil.waitForVisibilityBuId("citydropdown");
        driver.findElement(By.xpath("//a[contains(text(),'Kolkata (CCU)')]")).click();
        driver.findElement(By.xpath("(//a[@value='PAT'])[2]")).click();
        BrowserUtil.wait(2);
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
        BrowserUtil.wait(1);
        driver.findElement(By.xpath("//input[@name='ctl00$mainContent$view_date2']")).click();
        WebElement returnDateTable = driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//table"));
        List<WebElement> days = returnDateTable.findElements(By.xpath("//tbody//tr//td"));
        for (WebElement day : days) {
            if (day.getText().contains("13")) {
                day.click();
                break;
            }
        }
        WebElement totalPassengers = driver.findElement(By.id("divpaxinfo"));
        JSUtil.clickElementByJS(totalPassengers);
        BrowserUtil.wait(2);
        WebElement adultsDropdown = driver.findElement(By.id("ctl00_mainContent_ddl_Adult"));
        Select selectAdults = new Select(adultsDropdown);
        selectAdults.selectByVisibleText("2");
        Assert.assertEquals(selectAdults.getFirstSelectedOption().getText(), "2");
        WebElement childDropdown = driver.findElement(By.id("ctl00_mainContent_ddl_Child"));
        Select selectChild = new Select(childDropdown);
        selectChild.selectByVisibleText("2");
        Assert.assertEquals(selectChild.getFirstSelectedOption().getText(), "2");
        Assert.assertEquals(totalPassengers.getText(), "2 Adult, 2 Child");
    }
}
