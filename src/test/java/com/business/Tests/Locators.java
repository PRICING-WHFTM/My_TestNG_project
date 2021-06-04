package com.business.Tests;

import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Locators extends Base {

    @Test
    public void usingLocators() throws InterruptedException {
        driver.get("https://www.cargurus.com/");
        WebElement carMakeDropdown = driver.findElement(By.id("carPickerUsed_makerSelect"));
        Select selectMake = new Select(carMakeDropdown);
        selectMake.selectByVisibleText("Audi");
        WebElement carModelDropdown = driver.findElement(By.id("carPickerUsed_modelSelect"));
        Select selectModel = new Select(carModelDropdown);
        selectModel.selectByVisibleText("Q7");
        driver.findElement(By.id("dealFinderZipUsedId_dealFinderForm")).sendKeys("72713");
        driver.findElement(By.id("dealFinderForm_0")).click();
        Thread.sleep(1000);

    }

}
