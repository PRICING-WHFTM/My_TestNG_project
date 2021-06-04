package com.business.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class appleCom_testing {

    public static void main(String[] args) throws InterruptedException  {
        //1.Open Chrome browser
        //2.Go to https://www.apple.com
        System.setProperty("webdriver.chrome.driver", "//Users//respect//Downloads//chromedriver");
        WebDriver driver = new ChromeDriver();
        String url = "https://www.apple.com";
        driver.get(url);

        List<WebElement> allLinks = driver.findElements(By.xpath("//ul[@class='ac-gn-list']//a"));
        List<String> list = new ArrayList<>();
        for(int i = 0;i< allLinks.size();i++) {
            if(!allLinks.get(i).getText().isEmpty()) {
                String linkName = allLinks.get(i).getText().toLowerCase();
                list.add(linkName);
            }
        }
        System.out.println(list);

        //For loop will click given links
        for(int i = 1;i < list.size()-1;i++) {

            int linkWithText = 0;
            int linkNoText = 0;

            //Below lines is clicking on each link
            String linkName = list.get(i).toLowerCase();
            WebElement clickMe = driver.findElement(By.xpath("//a[contains(@class,'" + linkName + "')]"));
            clickMe.click();

            //get the current title
            String title = driver.getTitle();

            //get all links of each clicked link
            List<WebElement> linksAfterClick = driver.findElements(By.xpath("//body//a"));

            //this loop will find of each clicked link:
            //links with text
            //links with no text
            for(int j = 0;j < linksAfterClick.size();j++) {

                if(!linksAfterClick.get(j).getText().isEmpty()) {
                    //1 - how many total links with text of each page has
                    linkWithText++;
                }else {
                    //2 - how many total links without text of each page has
                    linkNoText++;
                }
            }
            System.out.println("Current title  is " + title + "\n" + linkName + " has links with text " +
                    linkWithText + "\n" + linkName + " has links with no text " + linkNoText);

            linksAfterClick.clear();
            System.out.println("---------------------------------------------------------------");
        }

    }

}
