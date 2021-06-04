package com.business.Tests;

import com.business.Utilities.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class RobotClass extends Base {

    @Test
    public void downloadWithRobot() throws AWTException {
        driver.get("https://the-internet.herokuapp.com/download");
        WebElement file = driver.findElement(By.linkText("some-file.txt"));
        File filePath = new File(System.getProperty("user.dir") + "/" + file.getText());
        if (!filePath.exists()) {
            file.click();
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        //important
        Assert.assertTrue(filePath.exists());

    }
}
