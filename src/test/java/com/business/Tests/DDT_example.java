package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import com.business.Utilities.ExcelUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class DDT_example extends Base {




    @DataProvider(name = "loginData")
    public Object[][] testData() {
        ExcelUtil excelUtil = new ExcelUtil("src/test/resources/login.xlsx", "details");
        Object[][] detailsFromExcel = excelUtil.getDataArray();
        return detailsFromExcel;
    }

    @Test(dataProvider = "loginData")
    public void dataDrivenTest(String username, String password, String result) {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameBox = driver.findElement(By.id("username"));
        usernameBox.sendKeys(username);
        BrowserUtil.wait(1);
        WebElement passwordBox = driver.findElement(By.id("password"));
        passwordBox.sendKeys(password);
        BrowserUtil.wait(1);
        driver.findElement(By.xpath("//i[text()=' Login']")).click();
        BrowserUtil.wait(1);
        WebElement resultField = driver.findElement(By.xpath("//div[@id='flash']"));
        BrowserUtil.waitForVisibility(resultField);
        String resultText = resultField.getText();
        System.out.println(resultText);
        Assert.assertTrue(resultText.contains(result));

    }

    @Test
    public void dataDrivenTestWithMaps() {
        ExcelUtil excelUtil =new ExcelUtil("src/test/resources/login.xlsx", "details");
        List<Map<String, String>> dataList = excelUtil.getDataListOfMap();
        for (int i = 0; i < dataList.size(); i++) {
            driver.get("https://the-internet.herokuapp.com/login");
            WebElement usernameBox = driver.findElement(By.id("username"));
            usernameBox.sendKeys(dataList.get(i).get("username"));
            BrowserUtil.wait(1);
            WebElement passwordBox = driver.findElement(By.id("password"));
            passwordBox.sendKeys(dataList.get(i).get("password"));
            BrowserUtil.wait(1);
            driver.findElement(By.xpath("//i[text()=' Login']")).click();
            BrowserUtil.wait(1);
            WebElement resultField = driver.findElement(By.xpath("//div[@id='flash']"));
            BrowserUtil.waitForVisibility(resultField);
            String resultText = resultField.getText();
            System.out.println(resultText);
            Assert.assertTrue(resultText.contains(dataList.get(i).get("result")));
        }
    }

}
