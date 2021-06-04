package com.business.Tests;


import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortAlphabetical extends Base {


    @Test(groups = {"regression"})
    public void verifyOptionsAreAlphaBethical() {
        driver.get("https://www.amazon.com/");
        WebElement dropdown = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select selectDropdown = new Select(dropdown);
        List<WebElement> optionsList = selectDropdown.getOptions();
        ArrayList<String> originalList = new ArrayList<>();
        for (WebElement option : optionsList) {
            originalList.add(option.getText());
        }
        ArrayList<String> copiedList = new ArrayList<>(originalList);
        Collections.sort(copiedList);
        Assert.assertEquals(originalList, copiedList); //-> Logic is correct, but the list in website is not
        // in ascending order
    }

    @Test
    public void verifyCellsInTableAreSorted() {
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        WebElement header = driver.findElement(By.xpath("//b[contains(text(),'Veg/fruit name')]"));
        header.click();
        header.click();
        List<WebElement> column_1st = driver.findElements(By.xpath("//table[@id='sortableTable']//tbody//tr//td[2]"));
        ArrayList<String> originalList = new ArrayList<>();
        for (WebElement webElement : column_1st) {
            originalList.add(webElement.getText());
        }
        ArrayList<String> copiedList = new ArrayList<>(originalList);
        Collections.sort(copiedList);
        Assert.assertEquals(originalList, copiedList);
    }

}

