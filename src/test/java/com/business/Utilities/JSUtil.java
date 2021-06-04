package com.business.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtil {

    public static void flashElement(WebElement element, WebDriver driver) {
        String bgColor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 8; i++) {
            changeColor("#000000", element, driver);
            changeColor(bgColor, element, driver);
        }
    }

    private static void changeColor(String color, WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        BrowserUtil.wait(1);

    }

    public static void drawBorder(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static String getTitleOfPageByJS() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        return js.executeScript("return document.title;").toString();
    }

    public static void clickElementByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
       js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public static void generateJSAlert(String message) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("alert('" + message + "')");
    }

    public static void refreshPageByJS() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("history.go(0)");
    }

    public static WebElement getShadowRoot(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        return (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
    }

    public static void checkingBox(String id) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("document.getElementById('"+id+"').checked=true;");
    }

    public static void un_checkingBox(String id) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("document.getElementById('"+id+"').checked=false;");
    }

    /*

    To get innertext of the entire webpage in Selenium

     */
    public static String getting_innerText() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        String innerText = js.executeScript(" return document.documentElement.innerText;").toString();
        return innerText;
    }


    // To get the domain

    public static String getting_domain() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        String domainName = js.executeScript("return document.domain;").toString();
        return domainName;
    }


    // To get the URL

    public static String getting_URL() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        String url=  js.executeScript("return document.URL;").toString();
        return url;
    }

    // To navigate to a different page using Javascript

    public static void navigate_to_different_URL(String url) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("window.location = '"+url+"'");

    }

    // scrolling up and down

    public static void scrollingUp(int x) {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("window.scrollBy(0,"+x+")");
    }

    // scrolling down untill bottom of the page

    public static void scroll_down_untill_bottom() {
        JavascriptExecutor js = (JavascriptExecutor) DriverUtil.getDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }


}
