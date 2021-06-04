package com.business.Tests;

import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class Windows_Handling extends Base {

    @Test
    public void handle_Windows() {
        driver.get("https://the-internet.herokuapp.com/windows");
        String parentTitle = driver.getTitle();
        for (int i = 0; i < 3; i++) {
            driver.findElement(By.linkText("Click Here")).click();
        }
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentWindow = it.next();
        while (it.hasNext()) {
            driver.switchTo().window(it.next());
            System.out.println(driver.getWindowHandle());   // Will print 3 new windows (not main window)
            System.out.println(driver.getTitle());
        }
        driver.switchTo().window(parentWindow);
        System.out.println("Parent window " + parentWindow);
        Assert.assertEquals(parentTitle, driver.getTitle());


    }



}
