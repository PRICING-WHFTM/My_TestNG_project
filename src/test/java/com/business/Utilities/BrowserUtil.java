package com.business.Utilities;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Function;

public class BrowserUtil {


    private static final Logger log = LogManager.getLogger(BrowserUtil.class.getName());


    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waits for element to be not stale
     */
//    public static void waitForStaleElement(WebElement element) {
//        int y = 0;
//        while (y <= 15) {
//            try {
//                element.isDisplayed();
//                break;
//            } catch (StaleElementReferenceException st) {
//                y++;
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            break;
//        }
//    }

    /**
     * Waits for the provided element to be visible on the page
     */
    public static WebElement waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), 30);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibilityByXpath(String xpath) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), 15);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        return element;
    }

    public static WebElement waitForVisibilityBuId(String id) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), 15);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        return element;
    }

    /**
     * Waits for provided element to be clickable
     */
    public static WebElement waitForClickablility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), 30);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for provided element to be invisible
     */
    public static void waitForInVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), 15);
        wait.until(ExpectedConditions.invisibilityOf(element));

    }

    /**
     * This method verifies Element not displayed in the page
     */

    public static void verifyElementNotDisplayed(String xpath) {
        List<WebElement> elem = DriverUtil.getDriver().findElements(By.xpath(xpath));
        if (elem.size() > 0) {
            Assert.fail();
            log.error("Element still displayed in the page");
        } else {
            Assert.assertTrue(true);
            log.info("Element not displayed ");
        }
    }

    /*
     * takes screenshot
     * whenever you call this method
     * it takes screenshot and returns location of the screenshot
     * @param name of test or whatever your like
     * take a name of a test and returns a path to screenshot takes
     */
    public static String getScreenshotPath(String testMethodName) {
        SimpleDateFormat df = new SimpleDateFormat("-yyyy-MM-dd-HH-mm");
        String date = df.format(new Date());
        TakesScreenshot ts = (TakesScreenshot) DriverUtil.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String target = System.getProperty("user.dir") + "/test-output/screenshots/" + testMethodName + date + ".png";
        File finalDestination = new File(target);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * Wait 15 seconds with polling interval of 200 milliseconds element to be wisible
     */
    public static WebElement fluentWait(WebElement webElement, WebDriver driver) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if (webElement.isDisplayed()) {
                    return webElement;
                } else {
                    return null;
                }
            }
        });
        return element;
    }

    /**
     * waits for backgrounds processes on the browser to complete
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    /**
     * Wait for proper page title
     */
    public static void waitForPageTitle(String pageTitle) {
        WebDriverWait wait = new WebDriverWait(DriverUtil.getDriver(), 10);
        wait.until(ExpectedConditions.titleIs(pageTitle));
    }

    /**
     * Gets current moth, returns current Month name as String
     */
    public static String get_Current_Month() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMMMMMMM");
        return simpleDateFormat.format(date);
    }

    /**
     * Gets current day, returns current  Month name as String
     */
    public static String get_Current_Day() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int day = calendar.get(Calendar.DATE);
        return String.valueOf(day);
    }
}
