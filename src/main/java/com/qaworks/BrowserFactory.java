package com.qaworks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public abstract class BrowserFactory {

    public static WebDriver driver;

    public static WebDriver StartBrowser(String Browser, String URL) throws MalformedURLException, InterruptedException {
        if (driver == null || !isSessionActive()) {
            driver = startRemoteWebBrowser(Browser, URL);
        }
        driver.manage().window().maximize();
        return driver;

    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static boolean isSessionActive() {
        try {
            return driver.findElements(By.tagName("body")).size() > 0;
        } catch (Exception e) {

        }
        return false;
    }

    public static void QuitBrowser() {
        driver.quit();
        driver = null;
    }

    public static WebDriver startRemoteWebBrowser(String browser, String URL) {


        try {
            if (browser.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.gecko.driver", "C:/Users/Iswarya/IdeaProjects/qaworkstest/Drivers/geckodriver.exe");
//                   DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//                   capabilities.setCapability("marionette", false);
                driver = new FirefoxDriver();
                //             driver.get("http://www.qaworks.com/contact-us/");
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();

            } else if (browser.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", "C:/Users/Iswarya/IdeaProjects/qaworkstest/Drivers/chromedriver");
                driver = new ChromeDriver();
            } else
                throw new RuntimeException("Browser give " + browser + " did not load..");
        } catch (Exception e) {
            throw new RuntimeException("Browser give " + browser + " did not load..");
        }

        driver.get(URL);
        return driver;
    }

}

