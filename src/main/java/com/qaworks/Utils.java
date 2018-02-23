package com.qaworks;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static com.qaworks.BrowserFactory.driver;
import static com.qaworks.BrowserFactory.getDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Utils {

    private FluentWait<WebDriver> wait;

    /**
     * Initialise the page element and the fluent wait.
     */
    public Utils() {
        PageFactory.initElements(getDriver(), this);
        wait = getWait();
    }

    /**
     * Returns the webdriver for current test session.
     *
     * @return
     */
//    public WebDriver getDriver() {
//        return BrowserFactory.getDriver();
//    }
    public static void sleep(int i) {
        try {
            Thread.sleep(i * 2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void selectFromDropDown(By by, String text) {
        Select sel = new Select(driver.findElement(by));
        sel.selectByVisibleText(text);
    }

    public static void selectFromDropDown1(By by, int index) {
        Select sel = new Select(driver.findElement(by));
        sel.selectByIndex(index);
    }

    public static boolean isElementPresent(By element) {
        try {
            return driver.findElement(element).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    /**
     * visit given url.
     *
     * @param url
     */
    public void visit(String url) {
        getDriver().get(url);
        waitForAjaxAndAnimation();
    }

    /**
     * Wait for the element be present in the DOM.
     *
     * @param element e.g #firstname or //input[@id='firstname']
     * @param locator e.g css or xpath
     */
//  private void waitForElementToBePresent(String element, String locator) {
//        wait.until(
//                new Predicate<WebDriver>()
//{
//                    public boolean apply(WebDriver driver) {
//                        List<WebElement> webElements = driver.findElements(Locators.valueOf(locator.toUpperCase())
//                                .getLocator(element));
//                        return webElements.size() > 0;
//                    }
//                }
//        );
//
//    }

    /**
     * Return the current url.
     *
     * @return
     */
    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    /**
     * Creating a standard fluent wait.
     *
     * @return
     */
    private FluentWait<WebDriver> getWait() {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(120, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .withMessage("The message you will see in if a TimeoutException is thrown");

        return wait;
    }

    /**
     * Wait for the element be present and visible in the DOM.
     *
     * @param element e.g #firstname or //input[@id='firstname']
     * @param locator e.g css or xpath
     */
    public void waitForElement(String element, String locator) {
//        waitForElementToBePresent(element, locator);
        waitForElementToBeVisible(element, locator);
    }

    /**
     * Wait for the element be visible in the DOM.
     *
     * @param element e.g #firstname or //input[@id='firstname']
     * @param locator e.g css or xpath
     */
    private void waitForElementToBeVisible(String element, String locator) {
        wait.until(visibilityOfElementLocated(Locators.valueOf(locator.toUpperCase()).getLocator(element)));

    }

    /**
     * Wait for the ajax events on the page to complete.
     *
     * @return
     */
    private ExpectedCondition<Boolean> waitForAjax() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
    }

    /**
     * Wait for elements that are in progress of an animation to complete.
     *
     * @return
     */
    private ExpectedCondition<Boolean> waitForAnimation() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (window.jQuery != null) && (jQuery(':animated').length === 0) && (jQuery('#loader').length === 0);");
            }
        };
    }

    /**
     * Wait for ajax events on the page and
     * elements that are in progress of an animation to complete.
     *
     * @return
     */
    public void waitForAjaxAndAnimation() {
        if (wait == null) {
            wait = getWait();
        }
        wait.until(waitForAjax());
        wait.until(waitForAnimation());
    }

    /**
     * Selecting options by visible text from the dropdown.
     *
     * @param element WebElement
     * @param text    visible text in the dropdown to be selected
     */
    public void select(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
            waitForAjaxAndAnimation();
        } catch (Exception e) {
            System.out.printf("ERROR: Attempting to click on '%s' has failed", element.toString());
        }
    }


//    protected static WebDriver driver = getDriver();

    /**
     * Type in the value into element.
     *
     * @param element WebElement
     * @param text    text to be set in the WebElement
     */
    public void type(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            waitForAjaxAndAnimation();
        } catch (Exception e) {
            System.out.printf("ERROR: Attempting to type '%s' into '%s' ", text, element.toString());
        }
    }

    /**
     * Click on element.
     *
     * @param element e.g #signin or //button[@id='signin']
     * @param locator e.g css or xpath
     */
    public void click(String element, String locator) {
        try {
            getDriver().findElement(Locators.valueOf(locator.toUpperCase()).getLocator(element)).click();
            waitForAjaxAndAnimation();
        } catch (Exception e) {
            System.out.printf("ERROR: Attempting to click on '%s' has failed", element);
        }

    }

    /**
     * Click on element.
     *
     * @param element WebElement
     */
    public void click(WebElement element) {
        try {
            element.click();
            waitForAjaxAndAnimation();
        } catch (Exception e) {
            System.out.printf("ERROR: Attempt to click on '%s' has failed", element.toString());
        }
    }

    public boolean isElementPresent(String element, String locator) {
        waitForElementToBeVisible(element, locator);
        {
            return true;
        }
    }


}




