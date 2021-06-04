package com.business.Tests;

import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class Links_In_Given_Areas extends Base {

    /**
     * Note:
     * As a main element always locate div element, not table.
     * Then using findElements find a tag elements inside main element.
     * It will not include the headers, will take only links.
     */

    @Test
    public void find_All_Links_In_Footer() {

        driver.get("https://www.spicejet.com/");
        WebElement footerArea = driver.findElement(By.cssSelector("div[class='wrapper_footer wrapper-footer-new']"));
        List<WebElement> allLinks = footerArea.findElements(By.tagName("a"));
        System.out.println(allLinks.size());
        for (WebElement link : allLinks) {
            System.out.println(link.getText());
        }
    }
}
