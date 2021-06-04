package com.business.Tests;

import com.business.Utilities.Base;
import com.business.Utilities.BrowserUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Links_FooterLinks extends Base {
    /*
     Use the logic, Note: if you click a link then driver object opens that and stays there
      That is why right the logic not move the driver there just click them by que that is it
      Use sendKeys (Keys.chord(Keys.Control, Keys.Enter);
      And get title of them all in 1 code using iterator.
      */

    // question
    @Test
    public void verifyAllFooterLinks() {
        driver.get("https://www.day.az/");
        WebElement footerElements = driver.findElement(By.xpath("//div[@class='row no-gutters px-3']"));
        List<WebElement> allFooterLinks = footerElements.findElements(By.tagName("a"));
        System.out.println("Footer links size = " + allFooterLinks.size());
        for (WebElement elem : allFooterLinks) {
            System.out.println(elem.getText());
        }
    }

    @Test
    public void verifyLinkCountInFirstTabInFooter() {
        driver.get("https://www.day.az/");
        //first locate the area of footer as 1 WebElement (area of footer)
        WebElement column1InFooter = driver.findElement(By.xpath("//div[@class='row no-gutters px-3']//div[1]/ul[1]"));
        //Locate Find all links in
        List<WebElement> allLinks = column1InFooter.findElements(By.xpath("//div[@class='col-3 px-2']//a"));
        System.out.println(allLinks.size());
        // Loop and click each element
       // int i=0;
        for (WebElement elem : allLinks) {
           // if(i==11){
           //     continue;
           // }
            elem.sendKeys(Keys.chord(Keys.COMMAND, Keys.ENTER));
           // i++;
        }

        BrowserUtil.wait(3);
        // Then we must switch driver to opened links to get their titles
        // 1 . Handle all opened tabs and iterate them with Iterator
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        while (it.hasNext()) {
            driver.switchTo().window(it.next());
            System.out.println(driver.getTitle());
        }
    }


}



