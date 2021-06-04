package com.business.Tests;

import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Broken_Links extends Base {

    @Test
    public void brokenLinksFinding() throws IOException {
        driver.get("https://www.day.az/");
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        //Combine all a and img tags in one List
        allLinks.addAll(driver.findElements(By.tagName("img")));
        //Get only links which have href value from entire page
        List<WebElement> validLinks = new ArrayList<>();
        for (int i = 0; i < allLinks.size(); i++) {
            if (allLinks.get(i).getAttribute("href") != null && (!allLinks.get(i).getAttribute("href").contains("javascript"))) {
                validLinks.add(allLinks.get(i));
            }
        }
        System.out.println("allLinks :" + allLinks.size());
        System.out.println("validLinks :" + validLinks.size());
        //Check the href with HttpConnection Api
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < validLinks.size(); i++) {
            HttpURLConnection connection = (HttpURLConnection) new URL(validLinks.get(i).getAttribute("href")).openConnection();
            connection.connect();
            String response = connection.getResponseMessage();//ok
            connection.disconnect();
            map.put(validLinks.get(i).getAttribute("href"), response);
        }
        System.out.println(map.toString());
    }

}
