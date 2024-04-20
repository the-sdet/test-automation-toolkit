package io.github.the_sdet.web;

import java.io.*;
import java.time.Duration;
import java.util.List;

/**
 * Abstract class containing utility methods for web automation.
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings({"unused", "UnusedReturnValue", "SameParameterValue"})
public abstract class Utils<T> {

  /**
   * Opens the specified URL in the browser.
   *
   * @param url
   *            The URL to open.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void openPage(String url);

  /**
   * Maximizes the screen.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void maximizeScreen();

  /**
   * Sets the screen size to the specified width and height.
   *
   * @param width
   *            The width of the screen.
   * @param height
   *            The height of the screen.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void setScreenSize(int width, int height);

  /**
   * Sets the default timeout for waiting for elements to appear on the page.
   *
   * @param duration
   *            The default timeout value, in seconds.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void setDefaultTimeOut(Duration duration);

  /**
   * Clicks on the element identified by the specified XPath.
   *
   * @param xpath
   *            The XPath of the element to click.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void click(String xpath);

  /**
   * Enters value on to element identified by the specified XPath
   *
   * @param xpath
   *            The XPath of the element
   * @param value
   *            Value to send
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void fillText(String xpath, String value);

  /**
   * Retrieves the WebElement by Xpath.
   *
   * @param xpath
   *            The XPath of the element to retrieve.
   * @return The WebElement found.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract T getElement(String xpath);
  abstract T getElementByText(String xpath, String textContent);

  /**
   * Retrieves the WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @return The WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract T getElements(String xpath);

  /**
   * Retrieves the WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @return The WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract T getElements(String xpath, boolean waitForFirstElement);

  /**
   * Retrieves the WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @param duration
   *            maximum wait
   * @return The WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract T getElements(String xpath, boolean waitForFirstElement, Duration duration);

  /**
   * Retrieves the text content of WebElement by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @return The text content of WebElement
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract String getElementTextContent(String xpath);

  /**
   * Retrieves the text content of WebElement by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForElement
   *            true if wait for the element
   * @return The text content of WebElement
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract String getElementTextContent(String xpath, boolean waitForElement);

  /**
   * Retrieves the text content of WebElement by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForElement
   *            true if wait for the element
   * @param duration
   *            maximum wait
   * @return The text content of WebElement
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract String getElementTextContent(String xpath, boolean waitForElement, Duration duration);

  /**
   * Retrieves the text content of WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @return The text content of WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract List<String> getElementsTextContent(String xpath);

  /**
   * Retrieves the text content of WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @return The text content of WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract List<String> getElementsTextContent(String xpath, boolean waitForFirstElement);

  /**
   * Retrieves the text content of WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @param duration
   *            maximum wait
   * @return The text content of WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract List<String> getElementsTextContent(String xpath, boolean waitForFirstElement, Duration duration);

  /**
   * Retrieves the number of elements by the specified locator
   *
   * @param xpath
   *            The XPath of the element
   * @return The element count
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract int getElementsCount(String xpath);

  /**
   * Retrieves the number of elements by the specified locator
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @return The element count
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract int getElementsCount(String xpath, boolean waitForFirstElement);

  /**
   * Retrieves the number of elements by the specified locator
   *
   * @param xpath
   *            The XPath of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @param duration
   *            maximum wait
   * @return The element count
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract int getElementsCount(String xpath, boolean waitForFirstElement, Duration duration);

  /**
   * Clicks on the element identified by the specified XPath using JavaScript.
   *
   * @param xpath
   *            The XPath of the element to click.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void javaScriptClick(String xpath);

  /**
   * Enters value on to element identified by the specified XPath using
   * JavaScript.
   *
   * @param xpath
   *            The XPath of the element
   * @param value
   *            Value to send
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void javaScriptFillText(String xpath, String value);

  /**
   * Waits for the element identified by the specified XPath to be clickable and
   * then clicks on it.
   *
   * @param xpath
   *            The XPath of the element to wait for and click.
   * @param duration
   *            The maximum time to wait for the element to be clickable, in
   *            seconds.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void waitAndClick(String xpath, Duration duration);

  /**
   * Waits for the specified number of seconds.
   *
   * @param duration
   *            wait duration.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void waitFor(Duration duration);

  /**
   * Enters the specified text into the element identified by the specified XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @param text
   *            The text to enter.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void enterText(String xpath, String text);

  /**
   * Clears the element identified by the specified XPath and enters the given
   * text into it.
   *
   * @param xpath
   *            The XPath of the element.
   * @param text
   *            The text to enter.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void clearAndEnterText(String xpath, String text);

  /**
   * Retrieves a screenshot of the current page.
   *
   * @return The screenshot file.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract File getScreenshot();

  /**
   * Takes a screenshot of the current page and saves it to the specified file
   * path.
   *
   * @param filepath
   *            The file path where the screenshot should be saved.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void takeScreenshot(String filepath);

  /**
   * Retrieves a screenshot of the element identified by the specified XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @return The screenshot file.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract File getElementScreenshot(String xpath);

  /**
   * Takes a screenshot of the element identified by the specified XPath and saves
   * it to the specified file path.
   *
   * @param xpath
   *            The XPath of the element.
   * @param filepath
   *            The file path where the screenshot should be saved.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void takeElementScreenshot(String xpath, String filepath);

  /**
   * Takes a full-page screenshot.
   *
   * @return The screenshot file.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract File takeFullPageScreenshot();

  /**
   * Takes a full-page screenshot and saves it to the specified file path.
   *
   * @param filepath
   *            The file path where the screenshot should be saved.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void takeFullPageScreenshot(String filepath);

  /**
   * Retrieves the screenshot of the current page as a Base64 encoded string.
   *
   * @return The Base64 encoded screenshot string.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract String getScreenshotAsBase64();

  /**
   * Retrieves the screenshot of the element identified by the specified XPath as
   * a Base64 encoded string.
   *
   * @param xpath
   *            The XPath of the element.
   * @return The Base64 encoded screenshot string.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract String getElementScreenshotAsBase64(String xpath);

  /**
   * Retrieves the full-page screenshot as a Base64 encoded string.
   *
   * @return The Base64 encoded screenshot string.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract String getFullPageScreenshotAsBase64();

  /**
   * Retrieves the screenshot of the current page as a byte array.
   *
   * @return The screenshot byte array.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract byte[] getScreenshotAsByte();

  /**
   * Retrieves the screenshot of the element identified by the specified XPath as
   * a byte array.
   *
   * @param xpath
   *            The XPath of the element.
   * @return The screenshot byte array.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract byte[] getElementScreenshotAsByte(String xpath);

  /**
   * Retrieves the full-page screenshot as a byte array.
   *
   * @return The screenshot byte array.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract byte[] getFullPageScreenshotAsByte();

  /**
   * Focuses on the element identified by the specified XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void focusOnElement(String xpath);

  /**
   * Hovers over the element identified by the specified XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void hoverOverElement(String xpath);

  /**
   * Simulates pressing the Tab key.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void pressTab();

  /**
   * Simulates pressing the Enter key.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void pressEnter();

  /**
   * Simulates pressing the Tab key on the element identified by the specified
   * XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void pressTabOnElement(String xpath);

  /**
   * Simulates pressing the Enter key on the element identified by the specified
   * XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void pressEnterOnElement(String xpath);

  /**
   * Scrolls the element identified by the specified XPath into view.
   *
   * @param xpath
   *            The XPath of the element.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void scrollElementIntoToView(String xpath);

  /**
   * Scrolls the page by the specified percentage.
   *
   * @param percentage
   *            The percentage of the page height to scroll by.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void scrollByPercent(double percentage);

  /**
   * Retrieves the value of the specified attribute of the element identified by
   * the specified XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @param attributeName
   *            The name of the attribute.
   * @return The value of the attribute.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract String getAttributeValue(String xpath, String attributeName);

  /**
   * Checks if the element identified by XPath is visible.
   *
   * @param xpath
   *            XPath identifying the element
   * @return true if the element is visible, false otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract boolean isVisible(String xpath);

  /**
   * Waits for the element identified by XPath to be visible.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @return true if the element becomes visible within the duration, false
   *         otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract boolean waitAndCheckIsVisible(String xpath, Duration duration);

  /**
   * Waits for the element identified by XPath to be clickable.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract boolean waitAndCheckIsClickable(String xpath, Duration duration);

  /**
   * Waits for the element identified by XPath to become invisible.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @return true if the element becomes invisible within the duration, false
   *         otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract boolean waitAndCheckIsInVisible(String xpath, Duration duration);
  /**
   * Waits for the element identified by XPath to become visible.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void waitForElementToBeVisible(String xpath, Duration duration);
  /**
   * Waits for the element identified by XPath to become invisible.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void waitForElementToBeInvisible(String xpath, Duration duration);

  /**
   * Waits for the element identified by XPath to become clickable.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract void waitForElementToBeClickable(String xpath, Duration duration);
  /**
   * Returns the current page source
   * 
   * @return String page source
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  abstract String getPageSource();
  /**
   * Customizes the XPath pattern by replacing placeholder values with the
   * provided values.
   *
   * @param rawXpath
   *            The raw XPath pattern.
   * @param value
   *            The value to replace the placeholder.
   * @return The customized XPath.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static String customizeXpath(String rawXpath, String value) {
    return rawXpath.replace("v1", value);
  }

  /**
   * Customizes the XPath pattern by replacing placeholder values with the
   * provided values.
   *
   * @param rawXpath
   *            The raw XPath pattern.
   * @param value1
   *            The first value to replace the first placeholder.
   * @param value2
   *            The second value to replace the second placeholder.
   * @return The customized XPath.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static String customizeXpath(String rawXpath, String value1, String value2) {
    return rawXpath.replace("v1", value1).replace("v2", value2);
  }

  /**
   * Customizes the XPath pattern by replacing placeholder values with the
   * provided values.
   *
   * @param rawXpath
   *            The raw XPath pattern.
   * @param value1
   *            The first value to replace the first placeholder.
   * @param value2
   *            The second value to replace the second placeholder.
   * @param value3
   *            The third value to replace the third placeholder.
   * @return The customized XPath.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static String customizeXpath(String rawXpath, String value1, String value2, String value3) {
    return rawXpath.replace("v1", value1).replace("v2", value2).replace("v3", value2);
  }
}
