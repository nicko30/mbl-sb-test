package demo.pages.base;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static demo.android_driver.AndroidDriverInstance.androidDriver;
import static demo.data.Constants.TIMEOUT;
import static demo.utils.Utils.ELEMENTS;
import static demo.utils.Utils.printError;

public class BasePageObject {

    /**
     * Returns the current Android driver instance
     * @return AndroidDriver - the active driver for mobile automation
     */
    public AndroidDriver getDriver() {
        return androidDriver;
    }

    /**
     * Converts a string locator key to a Selenium By object
     * Reads from properties file and creates appropriate locator type
     * @param elementLocator - key from properties file (e.g., "loginButton")
     * @return By object for element location
     */
    public By element(String elementLocator) {
        String elementValue = ELEMENTS.getProperty(elementLocator);
        if (elementValue == null) {
            throw new NoSuchElementException("Couldn't find element : " + elementLocator);
        } else {
            String[] locator = elementValue.split("_");
            String locatorType = locator[0];
            String locatorValue = elementValue.substring(elementValue.indexOf("_") + 1);
            return switch (locatorType) {
                case "id" -> By.id(String.format("%s%s", "com.saucelabs.mydemoapp.android:id/", locatorValue));
                case "name" -> By.name(locatorValue);
                case "xpath" -> By.xpath(locatorValue);
                case "containsText" -> By.xpath(String.format("//*[contains(@text, '%s')]", locatorValue));
                case "cssSelector" -> By.cssSelector(locatorValue);
                case "accessibilityId" -> AppiumBy.accessibilityId(locatorValue);
                default -> throw new IllegalStateException("Unexpected locator type: " + locatorType);
            };
        }
    }

    /**
     * Creates dynamic locators with parameters (e.g., for lists or dynamic content)
     * @param elementLocator - base locator key from properties
     * @param args - arguments to format into the locator string
     * @return String key for the constructed temporary locator
     */
    public String element(String elementLocator, Object... args) {
        String elementValue = ELEMENTS.getProperty(elementLocator);
        String constructedValue = String.format(elementValue, args);
        String constructedLocator = "TEMP_" + elementLocator;

        try {
            ELEMENTS.remove(constructedLocator);
        } catch (NullPointerException e) {
            printError("No properties key found!");
        }

        ELEMENTS.setProperty(constructedLocator, constructedValue);
        return constructedLocator;
    }


    /**
     * Checks if an element exists on the page within specified timeout
     * @param timeout - how long to wait in seconds
     * @param element - By locator for the element
     * @return true if element is found, false if not found within timeout
     */
    public boolean isPresent(long timeout, By element) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
            wait.ignoring(NoSuchElementException.class);
            wait.ignoring(StaleElementReferenceException.class);
            wait.ignoring(NoSuchFrameException.class);

            wait.until(ExpectedConditions.presenceOfElementLocated(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPresent(By element) {
        return isPresent(TIMEOUT, element);
    }

    public void assertIsPresent(By element) {
        if (!isPresent(element)) {
            printError("Element " + element + " not found!");
        }
    }

    public void assertIsPresent(String element) {
        assertIsPresent(element(element));
    }

    /**
     * Generic wait method with custom conditions and timeout
     * @param conditions - ExpectedCondition to wait for
     * @param timeout - timeout in seconds
     * @return WebElement when condition is met
     */
    public WebElement waitUntil(ExpectedCondition<WebElement> conditions, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        return wait.until(conditions);
    }

    public WebElement waitUntilClickable(By by) {
        return waitUntil(ExpectedConditions.elementToBeClickable(by), TIMEOUT);
    }

    public WebElement waitUntilPresent(By by) {
        return waitUntil(ExpectedConditions.presenceOfElementLocated(by), TIMEOUT);
    }

    public WebElement waitUntilVisible(By by) {
        return waitUntil(ExpectedConditions.visibilityOfElementLocated(by), TIMEOUT);
    }

    /**
     * Taps/clicks on an element after waiting for it to be clickable
     * @param by - By locator for element to tap
     */
    public void tapOn(By by) {
        waitUntilClickable(by).click();
    }

    public void tapOn(String element) {
        tapOn(element(element));
    }

    public void tapOn(String element, Object args) {
        tapOn(element(element, args));
    }

    /**
     * Types text into an input field (clears field first, then types)
     * @param by - By locator for input field
     * @param text - text to type
     */
    public void typeOn(By by, String text) {
        WebElement element = waitUntilVisible(by);
        element.clear();
        element.sendKeys(text);
    }

    public void typeOn(String element, String text) {
        typeOn(element(element), text);
    }

    /**
     * Gets the text content from an element
     * @param by - By locator for element
     * @return String text content of the element
     */
    public String getText(By by) {
        return find(by).getText();
    }

    public String getText(String element) {
        return getText(element(element));
    }

    public WebElement find(By by) {
        return waitUntilPresent(by);
    }

    /**
     * Performs swipe gesture with exact coordinates
     * @param startX - starting X coordinate
     * @param startY - starting Y coordinate
     * @param endX - ending X coordinate
     * @param endY - ending Y coordinate
     */
    public void swipeScreen(int startX, int startY, int endX, int endY) {
        ((JavascriptExecutor) getDriver()).executeScript("mobile: dragGesture", ImmutableMap.of(
                "startX", startX,
                "startY", startY,
                "endX", endX,
                "endY", endY,
                "speed", 600
        ));
    }

    /**
     * Swipes screen in specified direction (UP, DOWN, LEFT, RIGHT)
     * Calculates swipe coordinates based on screen size
     * @param direction - direction to swipe ("UP", "DOWN", "LEFT", "RIGHT")
     */
    public void swipeScreen(String direction) {
        int deviceWidth = getDriver().manage().window().getSize().getWidth();
        int deviceHeight = getDriver().manage().window().getSize().getHeight();
        int midX = (deviceWidth / 2);
        int midY = (deviceHeight / 2);
        int bottomEdge = (int) (deviceHeight * 0.85f);

        Dimension size = getDriver().manage().window().getSize();
        int x0 = (int) ((double) size.width * 0.85f);
        int x1 = 0;
        int y0 = (int) ((double) size.height * 0.7D);
        int y1 = (int) ((double) size.height * 0.3D);

        switch (direction.toUpperCase()) {
            case "UP" -> swipeScreen(midX, y0, midX, y1);
            case "DOWN" -> swipeScreen(midX, midY, midX, bottomEdge);
            case "LEFT" -> swipeScreen(x0, midY, x1, midY);
            case "RIGHT" -> swipeScreen(x1, midY, x0, midY);
            default -> throw new IllegalStateException("Unexpected value: " + direction.toUpperCase());
        }
    }

    /**
     * Pauses execution for specified number of seconds
     * @param seconds - number of seconds to wait
     */
    public static void waitABit(Integer seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void swipeUpScreen(int max) {
        for (int i = 0; i < max; i++) {
            swipeScreen("UP");
        }
        waitABit(2); // wait for swipe animation to finish
    }
}
