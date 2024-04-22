package io.github.the_sdet.web;

import io.github.the_sdet.logger.Log;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static io.github.the_sdet.common.CommonUtils.replaceLineBreaksWithSpace;
import static io.github.the_sdet.files.FileUtils.copyFile;

/**
 * Utility class for Selenium-based web automation tasks. This class provides
 * various methods to interact with web elements, take screenshots, handle
 * timeouts, and perform other common actions using the Selenium WebDriver API.
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings({"unused", "rawtypes"})
public class SeleniumUtils extends Utils {

  /**
   * The WebDriver instance used for web automation. This field holds the
   * reference to the WebDriver object used by various utility methods in this
   * class to perform web automation tasks.
   */
  protected WebDriver driver;
  JavascriptExecutor javascriptExecutor;
  Actions actions;

  /**
   * Constructor to initialize SeleniumUtils.
   *
   * @param driver
   *            The WebDriver instance to use.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public SeleniumUtils(WebDriver driver) {
    this.driver = driver;
    javascriptExecutor = (JavascriptExecutor) driver;
    actions = new Actions(driver);
  }

  /**
   * Retrieves the XPath of the specified WebElement.
   *
   * @param element
   *            The WebElement for which to retrieve the XPath.
   * @return The XPath of the WebElement.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  private String getXpathOfElement(WebElement element) {
    return element.toString().split("xpath: ")[1];
  }

  /**
   * Retrieves the XPath of the specified By selector.
   *
   * @param element
   *            The By selector for which to retrieve the XPath.
   * @return The XPath of the By selector.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  private String getXpathOfElement(By element) {
    return element.toString().split("xpath: ")[1];
  }

  /**
   * Opens the specified URL in the browser.
   *
   * @param url
   *            The URL to open.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void openPage(String url) {
    driver.get(url);
    Log.info("Opened URL: " + url);
  }

  /**
   * Maximizes the browser window.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void maximizeScreen() {
    driver.manage().window().maximize();
    Log.info("Maximized Screen...");
  }

  /**
   * Sets the screen size to the specified width and height.
   *
   * @param width
   *            The width of the screen.
   * @param height
   *            The height of the screen.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void setScreenSize(int width, int height) {
    driver.manage().window().setSize(new Dimension(width, height));
    Log.info("Screen Size set to: " + width + " x " + height);
  }

  /**
   * Clicks on the element identified by the given XPath.
   *
   * @param xpath
   *            The XPath of the element to click.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void click(String xpath) {
    getElement(xpath).click();
    Log.info("Clicked on Element with Xpath: " + xpath);
  }

  /**
   * Enters value on to element identified by the specified XPath
   *
   * @param element
   *            The locator of the element
   * @param value
   *            Value to send
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void fillText(By element, String value) {
    getElement(element).sendKeys(value);
  }

  /**
   * Enters value on to element identified by the specified XPath
   *
   * @param xpath
   *            The XPath of the element
   * @param value
   *            Value to send
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void fillText(String xpath, String value) {
    getElement(xpath).sendKeys(value);
  }

  /**
   * Retrieves the WebElement by Xpath.
   *
   * @param xpath
   *            The XPath of the element to retrieve.
   * @return The WebElement found.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public WebElement getElement(String xpath) {
    return driver.findElement(By.xpath(xpath));
  }

  /**
   * Retrieves the WebElement
   *
   * @param element
   *            By object of element
   * @return The WebElement found.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public WebElement getElement(By element) {
    return driver.findElement(element);
  }

  /**
   * Retrieves the WebElement by text content from a list of matching WebElements
   *
   * @param xpath
   *            Xpath of element
   * @param textContent
   *            text value to look for
   * @return The WebElement found.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public WebElement getElementByText(String xpath, String textContent) {
    List<WebElement> elements = getElements(xpath);
    WebElement elementToReturn = null;
    for (WebElement element : elements) {
      if (element.getText().equals(textContent.trim())) {
        elementToReturn = element;
        break;
      }
    }
    if (elementToReturn == null)
      throw new NoSuchElementException("No Element Found with text content: " + textContent);
    else
      return elementToReturn;
  }

  /**
   * Retrieves the WebElement by text content from a list of matching WebElements
   *
   * @param element
   *            The locator of the element
   * @param textContent
   *            text value to look for
   * @return The WebElement found.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public WebElement getElementByText(By element, String textContent) {
    List<WebElement> elements = getElements(element);
    WebElement elementToReturn = null;
    for (WebElement ele : elements) {
      if (ele.getText().equals(textContent.trim())) {
        elementToReturn = ele;
        break;
      }
    }
    if (elementToReturn == null)
      throw new NoSuchElementException("No Element Found with text content: " + textContent);
    else
      return elementToReturn;
  }

  /**
   * Retrieves the WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @return The WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public List<WebElement> getElements(String xpath) {
    return driver.findElements(By.xpath(xpath));
  }

  /**
   * Retrieves the WebElements by Xpath.
   *
   * @param element
   *            The locator of the element
   * @return The WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public List<WebElement> getElements(By element) {
    return driver.findElements(element);
  }

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
  @Override
  public List<WebElement> getElements(String xpath, boolean waitForFirstElement) {
    if (waitForFirstElement)
      if (waitAndCheckIsVisible(xpath, Duration.ofSeconds(5)))
        return getElements(xpath);
      else
        return new ArrayList<>();
    else
      return getElements(xpath);
  }

  /**
   * Retrieves the WebElements by Xpath.
   *
   * @param element
   *            The locator of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @return The WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public List<WebElement> getElements(By element, boolean waitForFirstElement) {
    if (waitForFirstElement)
      if (waitAndCheckIsVisible(element, Duration.ofSeconds(5)))
        return getElements(element);
      else
        return new ArrayList<>();
    else
      return getElements(element);
  }

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
  @Override
  public List<WebElement> getElements(String xpath, boolean waitForFirstElement, Duration duration) {
    if (waitForFirstElement)
      if (waitAndCheckIsVisible(xpath, duration))
        return getElements(xpath);
      else
        return new ArrayList<>();
    else
      return getElements(xpath);
  }

  /**
   * Retrieves the WebElements by Xpath.
   *
   * @param element
   *            The locator of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @param duration
   *            maximum wait
   * @return The WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public List<WebElement> getElements(By element, boolean waitForFirstElement, Duration duration) {
    if (waitForFirstElement)
      if (waitAndCheckIsVisible(element, duration))
        return getElements(element);
      else
        return new ArrayList<>();
    else
      return getElements(element);
  }

  /**
   * Retrieves the text content of WebElement by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @return The text content of WebElement
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public String getElementTextContent(String xpath) {
    return getElement(xpath).getText().trim();
  }

  /**
   * Retrieves the text content of WebElement by Xpath.
   *
   * @param element
   *            The locator of the element
   * @return The text content of WebElement
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public String getElementTextContent(By element) {
    return getElement(element).getText().trim();
  }

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
  @Override
  public String getElementTextContent(String xpath, boolean waitForElement) {
    if (waitForElement) {
      return waitAndFindElement(xpath).getText().trim();
    } else
      return getElementTextContent(xpath);
  }

  /**
   * Retrieves the text content of WebElement by Xpath.
   *
   * @param element
   *            The locator of the element
   * @param waitForElement
   *            true if wait for the element
   * @return The text content of WebElement
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public String getElementTextContent(By element, boolean waitForElement) {
    if (waitForElement) {
      return waitAndFindElement(element).getText().trim();
    } else
      return getElementTextContent(element);
  }

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
  @Override
  public String getElementTextContent(String xpath, boolean waitForElement, Duration duration) {
    if (waitForElement) {
      return waitAndFindElement(xpath, duration).getText().trim();
    } else
      return getElementTextContent(xpath);
  }

  /**
   * Retrieves the text content of WebElement by Xpath.
   *
   * @param element
   *            The locator of the element
   * @param waitForElement
   *            true if wait for the element
   * @param duration
   *            maximum wait
   * @return The text content of WebElement
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public String getElementTextContent(By element, boolean waitForElement, Duration duration) {
    if (waitForElement) {
      return waitAndFindElement(element, duration).getText().trim();
    } else
      return getElementTextContent(element);
  }

  /**
   * Retrieves the text content of WebElements by Xpath.
   *
   * @param xpath
   *            The XPath of the element
   * @return The text content of WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public List<String> getElementsTextContent(String xpath) {
    List<String> data = new ArrayList<>();
    List<WebElement> elements = getElements(xpath);
    for (WebElement element : elements) {
      data.add(replaceLineBreaksWithSpace(element.getText()).trim());
    }
    return data;
  }

  /**
   * Retrieves the text content of WebElements by Xpath.
   *
   * @param element
   *            The locator of the element
   * @return The text content of WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public List<String> getElementsTextContent(By element) {
    List<String> data = new ArrayList<>();
    List<WebElement> elements = getElements(element);
    for (WebElement ele : elements) {
      data.add(replaceLineBreaksWithSpace(ele.getText()).trim());
    }
    return data;
  }

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
  @Override
  public List<String> getElementsTextContent(String xpath, boolean waitForFirstElement) {
    if (waitForFirstElement)
      if (waitAndCheckIsVisible(xpath, Duration.ofSeconds(5)))
        return getElementsTextContent(xpath);
      else
        return new ArrayList<>();
    else
      return getElementsTextContent(xpath);
  }

  /**
   * Retrieves the text content of WebElements by Xpath.
   *
   * @param element
   *            The locator of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @return The text content of WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public List<String> getElementsTextContent(By element, boolean waitForFirstElement) {
    if (waitForFirstElement)
      if (waitAndCheckIsVisible(element, Duration.ofSeconds(5)))
        return getElementsTextContent(element);
      else
        return new ArrayList<>();
    else
      return getElementsTextContent(element);
  }

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
  @Override
  public List<String> getElementsTextContent(String xpath, boolean waitForFirstElement, Duration duration) {
    if (waitForFirstElement)
      if (waitAndCheckIsVisible(xpath, duration))
        return getElementsTextContent(xpath);
      else
        return new ArrayList<>();
    else
      return getElementsTextContent(xpath);
  }

  /**
   * Retrieves the text content of WebElements by Xpath.
   *
   * @param element
   *            The locator of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @param duration
   *            maximum wait
   * @return The text content of WebElements found as a list
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public List<String> getElementsTextContent(By element, boolean waitForFirstElement, Duration duration) {
    if (waitForFirstElement)
      if (waitAndCheckIsVisible(element, duration))
        return getElementsTextContent(element);
      else
        return new ArrayList<>();
    else
      return getElementsTextContent(element);
  }

  /**
   * Retrieves the number of elements by the specified locator
   *
   * @param xpath
   *            The XPath of the element
   * @return The element count
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public int getElementsCount(String xpath) {
    return getElements(xpath).size();
  }

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
  @Override
  public int getElementsCount(String xpath, boolean waitForFirstElement) {
    if (waitForFirstElement) {
      return getElements(xpath, true).size();
    } else
      return getElementsCount(xpath);
  }

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
  @Override
  public int getElementsCount(String xpath, boolean waitForFirstElement, Duration duration) {
    if (waitForFirstElement) {
      return getElements(xpath, true, duration).size();
    } else
      return getElementsCount(xpath);
  }

  /**
   * Retrieves the number of elements by the specified locator
   *
   * @param element
   *            The locator of the element
   * @return The element count
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public int getElementsCount(By element) {
    return getElements(element).size();
  }

  /**
   * Retrieves the number of elements by the specified locator
   *
   * @param element
   *            The locator of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @return The element count
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public int getElementsCount(By element, boolean waitForFirstElement) {
    if (waitForFirstElement) {
      return getElements(element, true).size();
    } else
      return getElementsCount(element);
  }

  /**
   * Retrieves the number of elements by the specified locator
   *
   * @param element
   *            The locator of the element
   * @param waitForFirstElement
   *            true if wait for first element
   * @param duration
   *            maximum wait
   * @return The element count
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public int getElementsCount(By element, boolean waitForFirstElement, Duration duration) {
    if (waitForFirstElement) {
      return getElements(element, true, duration).size();
    } else
      return getElementsCount(element);
  }

  /**
   * Clicks on the specified WebElement.
   *
   * @param element
   *            The WebElement to click.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void click(WebElement element) {
    element.click();
    Log.info("Clicked on Element with Xpath: " + element);
  }

  /**
   * Clicks on the element identified by the given By selector.
   *
   * @param element
   *            The By selector of the element to click.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void click(By element) {
    driver.findElement(element).click();
    Log.info("Clicked on Element with Xpath: " + element);
  }

  /**
   * Clicks on the element identified by the given XPath using JavaScript.
   *
   * @param xpath
   *            The XPath of the element to click.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void javaScriptClick(String xpath) {
    try {
      javascriptExecutor.executeScript("arguments[0].click();", getElement(xpath));
      Log.info("Clicked on Element with Xpath: " + xpath);
    } catch (Exception e) {
      Log.error("An error occurred: " + e.getMessage(), e);
    }
  }

  /**
   * Clicks on the element identified by the given XPath using JavaScript.
   *
   * @param element
   *            The locator of the element to click.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void javaScriptClick(By element) {
    try {
      javascriptExecutor.executeScript("arguments[0].click();", getElement(element));
      Log.info("Clicked on Element with Xpath: " + element);
    } catch (Exception e) {
      Log.error("An error occurred: " + e.getMessage(), e);
    }
  }

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
  @Override
  public void javaScriptFillText(String xpath, String value) {
    try {
      javascriptExecutor.executeScript("arguments[0].value=" + value + ";", getElement(xpath));
      Log.info("Entered Text" + value + " on Element with Xpath: " + xpath);
    } catch (Exception e) {
      Log.error("An error occurred: " + e.getMessage(), e);
    }
  }

  /**
   * Enters value on to element identified by the specified XPath using
   * JavaScript.
   *
   * @param element
   *            The locator of the element
   * @param value
   *            Value to send
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void javaScriptFillText(By element, String value) {
    try {
      javascriptExecutor.executeScript("arguments[0].value=" + value + ";", getElement(element));
      Log.info("Entered Text" + value + " on Element with Xpath: " + element);
    } catch (Exception e) {
      Log.error("An error occurred: " + e.getMessage(), e);
    }
  }

  /**
   * Enters the specified text into the element identified by the given XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @param text
   *            The text to enter.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void enterText(String xpath, String text) {
    getElement(xpath).sendKeys(text);
    Log.info("Entered text: " + text + " into element with Xpath: " + xpath);
  }

  /**
   * Enters the specified text into the element identified by the given XPath.
   *
   * @param element
   *            The locator of the element.
   * @param text
   *            The text to enter.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void enterText(By element, String text) {
    getElement(element).sendKeys(text);
    Log.info("Entered text: " + text + " into element with Xpath: " + element);
  }

  /**
   * Retrieves a screenshot of the current page.
   *
   * @return A File object representing the screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public File getScreenshot() {
    TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
    return screenshotDriver.getScreenshotAs(OutputType.FILE);
  }

  /**
   * Takes a screenshot of the current page and saves it to the specified
   * filepath.
   *
   * @param filepath
   *            The filepath where the screenshot should be saved.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void takeScreenshot(String filepath) {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    copyFile(screenshot, new File(filepath), "Screenshot");
  }

  /**
   * Retrieves a screenshot of the specified element.
   *
   * @param xpath
   *            The XPath of the element.
   * @return A File object representing the screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public File getElementScreenshot(String xpath) {
    return getElement(xpath).getScreenshotAs(OutputType.FILE);
  }

  /**
   * Takes a screenshot of the specified element and saves it to the specified
   * filepath.
   *
   * @param xpath
   *            The XPath of the element.
   * @param filepath
   *            The filepath where the screenshot should be saved.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void takeElementScreenshot(String xpath, String filepath) {
    File screenshot = getElement(xpath).getScreenshotAs(OutputType.FILE);
    copyFile(screenshot, new File(filepath), "Screenshot");
  }

  /**
   * Retrieves a screenshot of the specified element.
   *
   * @param element
   *            The locator of the element.
   * @return A File object representing the screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public File getElementScreenshot(By element) {
    return getElement(element).getScreenshotAs(OutputType.FILE);
  }

  /**
   * Takes a screenshot of the specified element and saves it to the specified
   * filepath.
   *
   * @param element
   *            The locator of the element.
   * @param filepath
   *            The filepath where the screenshot should be saved.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void takeElementScreenshot(By element, String filepath) {
    File screenshot = getElement(element).getScreenshotAs(OutputType.FILE);
    copyFile(screenshot, new File(filepath), "Screenshot");
  }

  /**
   * Takes a full-page screenshot of the current page.
   *
   * @return A File object representing the full-page screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public File takeFullPageScreenshot() {
    Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
        .takeScreenshot(driver);

    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
    try {
      ImageIO.write(screenshot.getImage(), "PNG", byteStream);
    } catch (IOException e) {
      Log.error("Could NOT save Screenshot...", e);
    }

    return new File("screenshot.png");
  }

  /**
   * Takes a full-page screenshot of the current page and saves it to the
   * specified filepath.
   *
   * @param filepath
   *            The filepath where the screenshot should be saved.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void takeFullPageScreenshot(String filepath) {
    Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
        .takeScreenshot(driver);

    File screenshotFile = new File(filepath);
    try {
      ImageIO.write(screenshot.getImage(), "PNG", screenshotFile);
    } catch (IOException e) {
      Log.error("Could NOT save Screenshot...", e);
    }
  }

  /**
   * Retrieves the screenshot of the current page as a Base64 encoded string.
   *
   * @return The Base64 encoded string representation of the screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public String getScreenshotAsBase64() {
    return Base64.getEncoder().encodeToString(getScreenshotAsByte());
  }

  /**
   * Retrieves the screenshot of the element identified by the given XPath as a
   * Base64 encoded string.
   *
   * @param xpath
   *            The XPath of the element.
   * @return The Base64 encoded string representation of the screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public String getElementScreenshotAsBase64(String xpath) {
    return Base64.getEncoder().encodeToString(getElementScreenshotAsByte(xpath));
  }

  /**
   * Retrieves the screenshot of the element identified by the given XPath as a
   * Base64 encoded string.
   *
   * @param element
   *            The locator of the element.
   * @return The Base64 encoded string representation of the screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public String getElementScreenshotAsBase64(By element) {
    return Base64.getEncoder().encodeToString(getElementScreenshotAsByte(element));
  }

  /**
   * Retrieves the full-page screenshot as a Base64 encoded string.
   *
   * @return The Base64 encoded string representation of the full-page screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public String getFullPageScreenshotAsBase64() {
    return Base64.getEncoder().encodeToString(getFullPageScreenshotAsByte());
  }

  /**
   * Retrieves the screenshot of the current page as a byte array.
   *
   * @return The byte array representing the screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public byte[] getScreenshotAsByte() {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }

  /**
   * Retrieves the screenshot of the element identified by the given XPath as a
   * byte array.
   *
   * @param xpath
   *            The XPath of the element.
   * @return The byte array representing the screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public byte[] getElementScreenshotAsByte(String xpath) {
    return getElement(xpath).getScreenshotAs(OutputType.BYTES);
  }

  /**
   * Retrieves the screenshot of the element identified by the given XPath as a
   * byte array.
   *
   * @param element
   *            The locator of the element.
   * @return The byte array representing the screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public byte[] getElementScreenshotAsByte(By element) {
    return getElement(element).getScreenshotAs(OutputType.BYTES);
  }

  /**
   * Retrieves the full-page screenshot as a byte array.
   *
   * @return The byte array representing the full-page screenshot.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public byte[] getFullPageScreenshotAsByte() {
    Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
        .takeScreenshot(driver);
    byte[] screenshotBytes = null;
    try {
      BufferedImage bufferedImage = screenshot.getImage();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ImageIO.write(bufferedImage, "png", outputStream);
      screenshotBytes = outputStream.toByteArray();
    } catch (IOException e) {
      Log.error("Could NOT save Screenshot...", e);
    }
    return screenshotBytes;
  }

  /**
   * Clears the existing text and enters the specified text into the element
   * identified by the given XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @param text
   *            The text to enter.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void clearAndEnterText(String xpath, String text) {
    WebElement element = getElement(xpath);
    element.clear();
    element.sendKeys(text);
    Log.info("Entered text: " + text + " into element with Xpath: " + xpath);
  }

  /**
   * Clears the existing text and enters the specified text into the element
   * identified by the given XPath.
   *
   * @param element
   *            The locator of the element.
   * @param text
   *            The text to enter.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void clearAndEnterText(By element, String text) {
    WebElement ele = getElement(element);
    ele.clear();
    ele.sendKeys(text);
    Log.info("Entered text: " + text + " into element with Xpath: " + element);
  }

  /**
   * Waits for the specified element to be clickable and then clicks on it.
   *
   * @param xpath
   *            The XPath of the element to click.
   * @param duration
   *            The maximum time to wait for the element to be clickable
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void waitAndClick(String xpath, Duration duration) {
    try {
      click(new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))));
    } catch (TimeoutException e) {
      Log.error("Couldn't find element within specified time period. Xpath: " + xpath, e);
    }
  }

  /**
   * Waits for the specified element to be clickable and then clicks on it.
   *
   * @param element
   *            The locator of the element to click.
   * @param duration
   *            The maximum time to wait for the element to be clickable
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void waitAndClick(By element, Duration duration) {
    try {
      click(new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(element)));
    } catch (TimeoutException e) {
      Log.error("Couldn't find element within specified time period. Xpath: " + element, e);
    }
  }

  /**
   * Pauses the execution for the specified duration.
   *
   * @param duration
   *            wait duration.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void waitFor(Duration duration) {
    try {
      Thread.sleep(duration.toMillis());
    } catch (InterruptedException e) {
      Log.error("Error while applying wait...", e);
    }
    Log.info(duration.toSeconds() + " seconds of wait completed...");
  }

  /**
   * Sets the default timeout for implicit waits.
   *
   * @param duration
   *            The default timeout value, in seconds.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void setDefaultTimeOut(Duration duration) {
    driver.manage().timeouts().implicitlyWait(duration);
    Log.info("Default timeout set to: " + duration.toSeconds() + " seconds...");
  }

  /**
   * Focuses on the element identified by the given XPath.
   *
   * @param xpath
   *            The XPath of the element to focus on.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void focusOnElement(String xpath) {
    javascriptExecutor.executeScript("arguments[0].focus();", getElement(xpath));
  }

  /**
   * Focuses on the element identified by the given XPath.
   *
   * @param element
   *            The locator of the element to focus on.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void focusOnElement(By element) {
    javascriptExecutor.executeScript("arguments[0].focus();", getElement(element));
  }

  /**
   * Hovers over the element identified by the given XPath.
   *
   * @param element
   *            The locator of the element to hover over.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void hoverOverElement(By element) {
    actions.moveToElement(getElement(element));
  }

  /**
   * Hovers over the element identified by the given XPath.
   *
   * @param xpath
   *            The XPath of the element to hover over.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void hoverOverElement(String xpath) {
    actions.moveToElement(getElement(xpath));
  }

  /**
   * Simulates pressing the Tab key.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void pressTab() {
    actions.sendKeys(Keys.TAB).perform();
  }

  /**
   * Simulates pressing the Enter key.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void pressEnter() {
    actions.sendKeys(Keys.ENTER).perform();
  }

  /**
   * Simulates pressing the Tab key on the element identified by the given XPath.
   *
   * @param xpath
   *            The XPath of the element to press Tab on.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void pressTabOnElement(String xpath) {
    actions.sendKeys(getElement(xpath), Keys.TAB).perform();
  }

  /**
   * Simulates pressing the Tab key on the element identified by the given XPath.
   *
   * @param element
   *            The locator of the element to press Tab on.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void pressTabOnElement(By element) {
    actions.sendKeys(getElement(element), Keys.TAB).perform();
  }

  /**
   * Simulates pressing the Enter key on the element identified by the given
   * XPath.
   *
   * @param element
   *            The locator of the element to press Enter on.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void pressEnterOnElement(By element) {
    actions.sendKeys(getElement(element), Keys.ENTER).perform();
  }

  /**
   * Simulates pressing the Enter key on the element identified by the given
   * XPath.
   *
   * @param xpath
   *            The XPath of the element to press Enter on.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void pressEnterOnElement(String xpath) {
    actions.sendKeys(getElement(xpath), Keys.ENTER).perform();
  }

  /**
   * Scrolls the element identified by the given XPath into view.
   *
   * @param xpath
   *            The XPath of the element to scroll into view.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void scrollElementIntoToView(String xpath) {
    javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(xpath));
  }

  /**
   * Scrolls the element identified by the given XPath into view.
   *
   * @param element
   *            The locator of the element to scroll into view.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void scrollElementIntoToView(By element) {
    javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(element));
  }

  /**
   * Scrolls the page by the specified percentage.
   *
   * @param percentage
   *            The percentage by which to scroll the page.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void scrollByPercent(double percentage) {
    javascriptExecutor.executeScript(
        "window.scrollTo(0, document.documentElement.scrollHeight * (" + percentage + "/ 100.0));");
  }

  /**
   * Retrieves the value of the specified attribute of the element identified by
   * the given XPath.
   *
   * @param xpath
   *            The XPath of the element.
   * @param attributeName
   *            The name of the attribute.
   * @return The value of the attribute.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public String getAttributeValue(String xpath, String attributeName) {
    return getAttributeValue(By.xpath(xpath), attributeName);
  }

  /**
   * Retrieves the value of the specified attribute of the element identified by
   * the given XPath.
   *
   * @param element
   *            The locator of the element.
   * @param attributeName
   *            The name of the attribute.
   * @return The value of the attribute.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public String getAttributeValue(By element, String attributeName) {
    return getElement(element).getAttribute(attributeName);
  }

  /**
   * Finds the element using the customized XPath with one parameter.
   *
   * @param rawXpath
   *            The raw XPath pattern.
   * @param value
   *            The value to be replaced in the XPath.
   * @return The WebElement found.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public WebElement findElementByCustomizeXpath(String rawXpath, String value) {
    return getElement(customizeXpath(rawXpath, value));
  }

  /**
   * Finds the element using the customized XPath with two parameters.
   *
   * @param rawXpath
   *            The raw XPath pattern.
   * @param value1
   *            The first value to be replaced in the XPath.
   * @param value2
   *            The second value to be replaced in the XPath.
   * @return The WebElement found.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public WebElement findElementByCustomizeXpath(String rawXpath, String value1, String value2) {
    return getElement(customizeXpath(rawXpath, value1, value2));
  }

  /**
   * Finds the element using the customized XPath with three parameters.
   *
   * @param rawXpath
   *            The raw XPath pattern.
   * @param value1
   *            The first value to be replaced in the XPath.
   * @param value2
   *            The second value to be replaced in the XPath.
   * @param value3
   *            The third value to be replaced in the XPath.
   * @return The WebElement found.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public WebElement findElementByCustomizeXpath(String rawXpath, String value1, String value2, String value3) {
    return getElement(customizeXpath(rawXpath, value1, value2, value3));
  }

  /**
   * Checks if the element identified by XPath is visible.
   *
   * @param xpath
   *            XPath identifying the element
   * @return true if the element is visible, false otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public boolean isVisible(String xpath) {
    return isVisible(By.xpath(xpath));
  }

  /**
   * Checks if the element identified by the given locator is visible.
   *
   * @param locator
   *            locator for identifying the element
   * @return true if the element is visible, false otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  public boolean isVisible(By locator) {
    try {
      return driver.findElement(locator).isDisplayed();
    } catch (Exception e) {
      return false;
    }
  }

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
  @Override
  public boolean waitAndCheckIsVisible(String xpath, Duration duration) {
    return waitAndCheckIsVisible(By.xpath(xpath), duration);
  }

  /**
   * Waits for the element identified by XPath to be visible.
   *
   * @param element
   *            locator for identifying the element
   * @param duration
   *            maximum duration to wait
   * @return true if the element becomes visible within the duration, false
   *         otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean waitAndCheckIsVisible(By element, Duration duration) {
    try {
      new WebDriverWait(driver, duration).until(ExpectedConditions.visibilityOfElementLocated(element));
      Log.info("Element is Visible: " + element);
      return true;
    } catch (Exception e) {
      Log.info("Element is NOT Visible...");
      return false;
    }
  }

  /**
   * Waits for the element identified by XPath to be clickable.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @return true if the element becomes clickable within the duration, false
   *         otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public boolean waitAndCheckIsClickable(String xpath, Duration duration) {
    return waitAndCheckIsClickable(By.xpath(xpath), duration);
  }

  /**
   * Waits for the element identified by XPath to be clickable.
   *
   * @param element
   *            locator for identifying the element
   * @param duration
   *            maximum duration to wait
   * @return true if the element becomes clickable within the duration, false
   *         otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean waitAndCheckIsClickable(By element, Duration duration) {
    try {
      new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(element));
      Log.info("Element is Clickable: " + element);
      return true;
    } catch (Exception e) {
      Log.info("Element is NOT Clickable...");
      return false;
    }
  }

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
  @Override
  public boolean waitAndCheckIsInVisible(String xpath, Duration duration) {
    return waitAndCheckIsInVisible(By.xpath(xpath), duration);
  }

  /**
   * Waits for the element identified by XPath to become visible.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void waitForElementToBeVisible(String xpath, Duration duration) {
    waitForElementToBeVisible(By.xpath(xpath), duration);
  }

  /**
   * Waits for the element identified by XPath to become invisible.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void waitForElementToBeInvisible(String xpath, Duration duration) {
    waitForElementToBeInvisible(By.xpath(xpath), duration);
  }

  /**
   * Waits for the element identified by XPath to become clickable.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public void waitForElementToBeClickable(String xpath, Duration duration) {
    waitForElementToBeClickable(By.xpath(xpath), duration);
  }

  /**
   * Waits for the element identified by XPath to become visible.
   *
   * @param locator
   *            the element locator
   * @param duration
   *            maximum duration to wait
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void waitForElementToBeVisible(By locator, Duration duration) {
    new WebDriverWait(driver, duration).until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  /**
   * Waits for the element identified by XPath to become invisible.
   *
   * @param locator
   *            the element locator
   * @param duration
   *            maximum duration to wait
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void waitForElementToBeInvisible(By locator, Duration duration) {
    new WebDriverWait(driver, duration).until(ExpectedConditions.invisibilityOfElementLocated(locator));
  }

  /**
   * Waits for the element identified by XPath to become clickable.
   *
   * @param locator
   *            the element locator
   * @param duration
   *            maximum duration to wait
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void waitForElementToBeClickable(By locator, Duration duration) {
    new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(locator));
  }

  /**
   * Returns the current page source
   *
   * @return String page source
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @Override
  public String getPageSource() {
    return driver.getPageSource();
  }

  /**
   * Waits for the element identified by the given locator to become invisible.
   *
   * @param element
   *            locator for identifying the element
   * @param duration
   *            maximum duration to wait
   * @return true if the element becomes invisible within the duration, false
   *         otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean waitAndCheckIsInVisible(By element, Duration duration) {
    try {
      new WebDriverWait(driver, duration).until(ExpectedConditions.invisibilityOfElementLocated(element));
      Log.info("Element is Invisible: " + element.toString());
      return true;
    } catch (Exception e) {
      Log.info("Element is visible...");
      return false;
    }
  }

  /**
   * Waits for the element identified by XPath to be present within a specified
   * duration.
   *
   * @param xpath
   *            XPath identifying the element
   * @param duration
   *            maximum duration to wait
   * @return WebElement representing the located element
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public WebElement waitAndFindElement(String xpath, Duration duration) {
    return waitAndFindElement(By.xpath(xpath), duration);
  }

  /**
   * Waits for the element identified by the given locator to be present within a
   * specified duration.
   *
   * @param locator
   *            locator for identifying the element
   * @param duration
   *            maximum duration to wait
   * @return WebElement representing the located element
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public WebElement waitAndFindElement(By locator, Duration duration) {
    WebDriverWait wait = new WebDriverWait(driver, duration);
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  /**
   * Waits for the element identified by XPath to be present within a default
   * duration of 5 seconds.
   *
   * @param xpath
   *            XPath identifying the element
   * @return WebElement representing the located element
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public WebElement waitAndFindElement(String xpath) {
    return waitAndFindElement(By.xpath(xpath));
  }

  /**
   * Waits for the element identified by the given locator to be present within a
   * default duration of 5 seconds.
   *
   * @param locator
   *            locator for identifying the element
   * @return WebElement representing the located element
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public WebElement waitAndFindElement(By locator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }

  /**
   * Waits for a specific seconds for the element to have text.
   *
   * @param element
   *            - By element
   * @param timeout
   *            - Duration timeout
   * @param expectedText
   *            - String expected text
   * @return boolean - true if element has expected text, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckElementHasText(By element, Duration timeout, String expectedText) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      return wait.until(ExpectedConditions.textToBePresentInElementLocated(element, expectedText));
    } catch (TimeoutException e) {
      Log.error("TimeoutException: Element did not have the expected text within the specified time.");
      return false;
    }
  }

  /**
   * Waits for a specific seconds for the element to have text.
   *
   * @param xpath
   *            - Xpath of the Element
   * @param timeout
   *            - Duration timeout
   * @param expectedText
   *            - String expected text
   * @return boolean - true if element has expected text, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckElementHasText(String xpath, Duration timeout, String expectedText) {
    return waitAndCheckElementHasText(By.xpath(xpath), timeout, expectedText);
  }

  /**
   * Waits for a specific seconds for the URL to contain the expected value.
   *
   * @param expectedValue
   *            - String expected URL value
   * @param timeout
   *            - Duration timeout
   * @return boolean - true if URL contains the expected value, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckUrlContains(String expectedValue, Duration timeout) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      return wait.until(ExpectedConditions.urlContains(expectedValue));
    } catch (TimeoutException e) {
      Log.error("TimeoutException: URL did not contain the expected value within the specified time.");
      return false;
    }
  }

  /**
   * Waits for a specific seconds for an alert to be present.
   *
   * @param timeout
   *            - Duration timeout
   * @return boolean - true if alert is present, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckIsAlertPresent(Duration timeout) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      return wait.until(ExpectedConditions.alertIsPresent()) != null;
    } catch (TimeoutException e) {
      Log.error("TimeoutException: Alert did not appear within the specified time.");
      return false;
    }
  }

  /**
   * Waits for a specific seconds for the element to be selected.
   *
   * @param element
   *            - By element
   * @param timeout
   *            - Duration timeout
   * @return boolean - true if element is selected, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckIsElementSelected(By element, Duration timeout) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      return wait.until(ExpectedConditions.elementToBeSelected(element));
    } catch (TimeoutException e) {
      Log.error("TimeoutException: Element was not selected within the specified time.");
      return false;
    }
  }

  /**
   * Waits for a specific seconds for the element to be selected.
   *
   * @param xpath
   *            - Xpath of the element
   * @param timeout
   *            - Duration timeout
   * @return boolean - true if element is selected, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckIsElementSelected(String xpath, Duration timeout) {
    return waitAndCheckIsElementSelected(By.xpath(xpath), timeout);
  }

  /**
   * Waits for a specific seconds for the element's attribute to have a certain
   * value.
   *
   * @param xpath
   *            - Xpath of the element
   * @param timeout
   *            - duration
   * @param attributeName
   *            - String attribute name
   * @param expectedValue
   *            - String expected attribute value
   * @return boolean - true if the attribute has the expected value, false
   *         otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckIsElementAttributeHasValue(String xpath, Duration timeout, String attributeName,
      String expectedValue) {
    return waitAndCheckIsElementAttributeHasValue(By.xpath(xpath), timeout, attributeName, expectedValue);
  }

  /**
   * Waits for a specific seconds for the element's attribute to have a certain
   * value.
   *
   * @param element
   *            - By element
   * @param timeout
   *            - duration
   * @param attributeName
   *            - String attribute name
   * @param expectedValue
   *            - String expected attribute value
   * @return boolean - true if the attribute has the expected value, false
   *         otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckIsElementAttributeHasValue(By element, Duration timeout, String attributeName,
      String expectedValue) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      return wait.until(ExpectedConditions.attributeToBe(element, attributeName, expectedValue));
    } catch (TimeoutException e) {
      Log.error("TimeoutException: Element attribute did not have the expected value within the specified time.");
      return false;
    }
  }

  /**
   * Waits for a specific seconds for the element to be present in the DOM.
   *
   * @param xpath
   *            - Xpath of the element
   * @param timeout
   *            - Duration timeout
   * @return boolean - true if element is present, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckIsPresent(String xpath, Duration timeout) {
    return waitAndCheckIsPresent(By.xpath(xpath), timeout);
  }

  /**
   * Waits for a specific seconds for the element to be present in the DOM.
   *
   * @param element
   *            - By element
   * @param timeout
   *            - Duration timeout
   * @return boolean - true if element is present, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckIsPresent(By element, Duration timeout) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      return wait.until(ExpectedConditions.presenceOfElementLocated(element)) != null;
    } catch (TimeoutException e) {
      Log.error("TimeoutException: Element was not present within the specified time.");
      return false;
    }
  }

  /**
   * Waits for a specific seconds for the element to be clickable using a custom
   * condition.
   *
   * @param element
   *            - By element
   * @param timeout
   *            - Duration timeout
   * @return boolean - true if the element is clickable, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckIsClickableEnhanced(By element, Duration timeout) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeout);
      return wait.until(driver -> {
        WebElement webElement = driver.findElement(element);
        return webElement.isDisplayed() && webElement.isEnabled();
      });
    } catch (TimeoutException e) {
      Log.error("TimeoutException: Element was not clickable within the specified time.");
      return false;
    }
  }

  /**
   * Waits for a specific seconds for the element to be clickable using a custom
   * condition.
   *
   * @param xpath
   *            - Xpath of the element
   * @param timeout
   *            - Duration timeout
   * @return boolean - true if the element is clickable, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean waitAndCheckIsClickableEnhanced(String xpath, Duration timeout) {
    return waitAndCheckIsClickableEnhanced(By.xpath(xpath), timeout);
  }

  /**
   * Scrolls to the element
   *
   * @param element
   *            - By element
   * @return boolean - true if the scroll were successful, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean scrollToElement(By element) {
    try {
      WebElement webElement = getElement(element);
      actions.moveToElement(webElement).perform();
      return true;
    } catch (Exception e) {
      Log.error("Exception during scroll and click...", e);
      return false;
    }
  }

  /**
   * Scrolls to the element
   *
   * @param xpath
   *            - Xpath of the element
   * @return boolean - true if the scroll were successful, false otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean scrollToElement(String xpath) {
    return scrollToElement(By.xpath(xpath));
  }

  /**
   * Scrolls to the element and performs a click.
   *
   * @param element
   *            - By element
   * @return boolean - true if the scroll and click were successful, false
   *         otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean scrollAndClick(By element) {
    try {
      WebElement webElement = getElement(element);
      actions.moveToElement(webElement).click().build().perform();
      return true;
    } catch (Exception e) {
      Log.error("Exception during scroll and click...", e);
      return false;
    }
  }

  /**
   * Scrolls to the element and performs a click.
   *
   * @param xpath
   *            - Xpath of the element
   * @return boolean - true if the scroll and click were successful, false
   *         otherwise
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public boolean scrollAndClick(String xpath) {
    return scrollAndClick(By.xpath(xpath));
  }

  /**
   * Finds a web element using multiple locators.
   *
   * @param xPaths
   *            The XPaths of the elements to search for.
   * @return The WebElement found.
   * @throws NoSuchElementException
   *             if the element is not found for any of the provided locators.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public WebElement findElementByMultipleLocators(String... xPaths) {
    for (String xpath : xPaths) {
      try {
        return getElement(By.xpath(xpath));
      } catch (NoSuchElementException e) {
        Log.info("No element found for Xpath: " + xpath);
      }
    }
    throw new NoSuchElementException("Element NOT found for any of the provided locators...");
  }
}