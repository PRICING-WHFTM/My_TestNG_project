package com.business.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;

/**
 * We use Singleton design pattern in order to have one driver instance at the same time
 */

public class DriverUtil {


    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private DriverUtil() {
    }

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browser = ConfigReader.getProperty("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    HashMap<String, Object> chromePrefs = new HashMap<>();
                    chromePrefs.put("profile.default_content_settings.popups", 0);
                    chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
                    driverPool.set(new ChromeDriver(new ChromeOptions().setExperimentalOption("prefs", chromePrefs)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    HashMap<String, Object> chromePrefHeadless = new HashMap<String, Object>();
                    chromePrefHeadless.put("profile.default_content_settings.popups", 0);
                    chromePrefHeadless.put("download.default_directory", System.getProperty("user.dir"));
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true).setExperimentalOption("prefs", chromePrefHeadless)));
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "chrome-remote":
                    try {
                        URL url = new URL("http://localhost:4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        desiredCapabilities.setPlatform(Platform.WINDOWS);
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "firefox-remote":
                    try {
                        URL url = new URL("http://localhost:4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("firefox");
                        desiredCapabilities.setPlatform(Platform.WINDOWS);
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    }
                    WebDriverManager.iedriver().setup();
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
                        throw new WebDriverException("Your OS doesn't support Edge");
                    }
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "safari": // forwarding to saucelabs
//                    if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
//                        throw new WebDriverException("Your OS doesn't support Safari");
//                    }
//                    WebDriverManager.getInstance(SafariDriver.class).setup();
//                    driverPool.set(new SafariDriver());
                    try {
                        URL url = new URL("https://Xose1:af4c088f-4075-4f97-b1bd-6d2fd7210e19@ondemand.us-west-1.saucelabs.com:443/wd/hub");
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setPlatform(Platform.MAC);
                        caps.setBrowserName("Safari");
                        caps.setVersion("latest");
                        driverPool.set(new RemoteWebDriver(url, caps));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new RuntimeException("Invalid browser name!");
            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        driverPool.get().close();
    }

    public static void quitDriver() {
        driverPool.get().quit();
        driverPool.remove();
    }

}
