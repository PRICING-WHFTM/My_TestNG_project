package com.business.Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class First_pure_class {

    @Test
    public void verifyGoogle() {
        // WebDriverManager.chromedriver().setup();
        // In project use / slash in comp use \\ slashes  , path from repository root
        System.setProperty("webdriver.chrome.driver", "src/test/resources/browser/chromedriver");
        WebDriver driver = new ChromeDriver();
        // WebDriver driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        if (title.equals("Google")) {
            System.out.println("Title is as expected Google");
        } else {
            System.out.println("Title of Google is wrong : " + title);
        }
        driver.get("https://www.milli.az/");
        System.out.println(driver.getTitle());
        driver.navigate().refresh();
        driver.navigate().back();
        if (driver.getTitle().contains("Google")) {
            System.out.println("Google title successfully verified");
        }
        driver.close();
        driver.quit();


    }
}
