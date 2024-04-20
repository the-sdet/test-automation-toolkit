package io.github.the_sdet.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.github.the_sdet.logger.Log;
import io.github.the_sdet.web.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;

import static java.util.Collections.singletonList;

/**
 * Utility class for Appium Mobile automation tasks. This class provides various
 * methods to interact with mobile elements, take screenshots, handling scroll
 * and swipes, etc...
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings("unused")
public class AppiumUtils extends SeleniumUtils {
  /**
   * The AppiumDriver instance used for mobile automation. This field holds the
   * reference to the AppiumDriver object used by various utility methods in this
   * class to perform mobile automation tasks.
   */
  protected AppiumDriver driver;

  /**
   * Constructor to initialize AppiumUtils.
   *
   * @param driver
   *            The Appium Driver (AndroidDriver/IOSDriver) instance to use.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public AppiumUtils(AppiumDriver driver) {
    super(driver);
    this.driver = driver;
  }

  /**
   * This method performs a click action at provided coordinate using gestures
   *
   * @param x
   *            x coordinate
   * @param y
   *            y coordinate
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void advanceClick(int x, int y) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence touch = new Sequence(finger, 0);
    touch.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), x, y))
        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(singletonList(touch));
  }

  /**
   * This method performs a click action on an element using gestures
   *
   * @param element
   *            element locator
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void advanceClick(By element) {
    waitFor(Duration.ofSeconds(1));
    WebElement webElement = getElement(element);
    Point point = webElement.getLocation();
    Dimension size = webElement.getSize();
    int x = point.getX() + size.getWidth() / 2;
    int y = point.getY() + size.getHeight() / 2;
    advanceClick(x, y);
  }

  /**
   * This method performs a click action on an element using gestures
   *
   * @param xpath
   *            element locator
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void advanceClick(String xpath) {
    advanceClick(By.xpath(xpath));
  }

  /**
   * Hides the on-screen keyboard if it is displayed.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void hideKeyboard() {
    Log.info("This method is only applicable for Android...");
    if (driver instanceof AndroidDriver) {
      if (((AndroidDriver) driver).isKeyboardShown())
        ((AndroidDriver) driver).hideKeyboard();
    }
  }

  /**
   * Presses the back button of Android Device.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void pressBackKey() {
    pressKey(AndroidKey.BACK);
  }

  /**
   * Presses specified key of Android Device.
   *
   * @param key
   *            Key
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void pressKey(AndroidKey key) {
    Log.info("This method is only applicable for Android...");
    if (driver instanceof AndroidDriver) {
      ((PressesKey) driver).pressKey(new KeyEvent(key));
    }
  }

  /**
   * Enumeration for swipe directions.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public enum SWIPE_DIRECTION {
    /**
     * Represents the leftward swipe direction.
     */
    LEFT,

    /**
     * Represents the rightward swipe direction.
     */
    RIGHT
  }

  /**
   * Enumeration for scroll/swipe directions.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public enum DIRECTION {
    /**
     * Represents the downward scroll direction.
     */
    DOWN,

    /**
     * Represents the upward scroll direction.
     */
    UP,

    /**
     * Represents the leftward swipe direction.
     */
    LEFT,

    /**
     * Represents the rightward swipe direction.
     */
    RIGHT
  }

  /**
   * This method scrolls and swipes in both direction to check an element's
   * presence.
   *
   * @param direction
   *            scroll direction
   * @param xpath
   *            locator
   * @param duration
   *            scroll or swipe duration
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void scrollOrSwipe(String xpath, DIRECTION direction, Duration duration) {
    By element = By.xpath(xpath);
    scrollOrSwipe(element, direction, duration);
  }

  /**
   * This method scrolls and swipes in both direction to check an element's
   * presence.
   *
   * @param direction
   *            scroll direction
   * @param element
   *            locator
   * @param duration
   *            scroll or swipe duration
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void scrollOrSwipe(By element, DIRECTION direction, Duration duration) {
    Dimension size = driver.manage().window().getSize();

    int startX, startY, endX, endY;

    switch (direction) {
      case RIGHT :
        startY = size.height / 2;
        startX = (int) (size.width * 0.05);
        endX = (int) (size.width * 0.09);

        for (int i = 0; i < 3; i++) {
          if (!isVisible(element)) {
            swipe(startX, startY, endX, duration);
          } else {
            break;
          }
        }
        break;

      case LEFT :
        startY = size.height / 2;
        startX = (int) (size.width * 0.90);
        endX = (int) (size.width * 0.05);

        for (int i = 0; i < 3; i++) {
          if (!isVisible(element)) {
            swipe(startX, startY, endX, duration);
          } else {
            break;
          }
        }
        break;

      case UP :
        endY = (int) (size.height * 0.70);
        startY = (int) (size.height * 0.30);
        startX = (size.width / 2);
        for (int i = 0; i <= 3; i++) {
          if (!isVisible(element)) {
            scroll(startX, startY, endY, duration);
          } else {
            break;
          }
        }
        break;

      case DOWN :
        startY = (int) (size.height * 0.70);
        endY = (int) (size.height * 0.30);
        startX = (size.width / 2);
        for (int i = 0; i <= 3; i++) {
          if (!isVisible(element)) {
            scroll(startX, startY, endY, duration);
          } else {
            break;
          }
        }
        break;
      default :
        Log.error("Invalid Direction...");
    }
  }

  /**
   * Handles swipe of one element inside another element
   *
   * @param containerXpath
   *            slider container xpath
   * @param sliderXpath
   *            actual slider xpath
   * @param direction
   *            Swipe Direction
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipeElementInsideAContainer(String containerXpath, String sliderXpath, SWIPE_DIRECTION direction) {
    swipeElementInsideAContainer(By.xpath(containerXpath), By.xpath(sliderXpath), direction);
  }

  /**
   * Handles swipe of one element inside another element
   *
   * @param container
   *            slider container
   * @param slider
   *            actual slider
   * @param direction
   *            Swipe Direction
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipeElementInsideAContainer(By container, By slider, SWIPE_DIRECTION direction) {
    int containerWidth = getElement(container).getSize().getWidth();
    WebElement sliderButton = getElement(slider);
    Dimension size = sliderButton.getSize();
    int middleX = size.getWidth() / 2;
    int middleY = size.getHeight() / 2;
    Point source = sliderButton.getLocation();
    int startX, endX;
    int startY = source.getY() + middleY;
    if (direction == SWIPE_DIRECTION.RIGHT) {
      startX = source.getX() + middleX;
      endX = startX + containerWidth;
    } else {
      startX = source.getX() + middleX;
      endX = source.getX() - containerWidth;
    }
    swipe(startX, startY, endX, Duration.ofMillis(500));
  }

  /**
   * Swipes off a push notification appearing on top of the screen
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipePushNotification() {
    Dimension size = driver.manage().window().getSize();
    int startX = (int) (size.width * 0.1);
    int endX = (int) (size.height * 0.9);
    int startY = (int) (size.height * 0.1);
    swipe(startX, startY, endX, Duration.ofMillis(600));
  }

  /**
   * Swipes left in the middle of the screen.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipeLeft() {
    int height = driver.manage().window().getSize().getHeight();
    int width = driver.manage().window().getSize().getWidth();

    int startY = height / 2;
    int startX = (int) (width * 0.9); // Swipe starts from 90% of the width
    int endX = (int) (width * 0.1); // Swipe ends at 10% of the width
    swipe(startX, startY, endX, Duration.ofMillis(500));
  }

  /**
   * Swipes right in the middle of the screen.
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipeRight() {
    int height = driver.manage().window().getSize().getHeight();
    int width = driver.manage().window().getSize().getWidth();

    int startY = height / 2;
    int startX = (int) (width * 0.1); // Swipe starts from 10% of the width
    int endX = (int) (width * 0.9); // Swipe ends at 90% of the width
    swipe(startX, startY, endX, Duration.ofMillis(500));
  }

  /**
   * Handles scroll functionality
   *
   * @param startX
   *            Start X coordinate
   * @param startY
   *            Start Y coordinate
   * @param endY
   *            End Y coordinate
   * @param delay
   *            Scroll Timeout
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void scroll(int startX, int startY, int endY, Duration delay) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 0);
    swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
        .addAction(finger.createPointerMove(delay, PointerInput.Origin.viewport(), startX, endY))
        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(singletonList(swipe));
    waitFor(Duration.ofSeconds(1));
  }

  /**
   * Handles swipe functionality
   *
   * @param startX
   *            Start X coordinate
   * @param startY
   *            Start Y coordinate
   * @param endX
   *            End X coordinate
   * @param delay
   *            Swipe Timeout
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipe(int startX, int startY, int endX, Duration delay) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
    Sequence swipe = new Sequence(finger, 0);
    swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
        .addAction(finger.createPointerMove(delay, PointerInput.Origin.viewport(), endX, startY))
        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    driver.perform(singletonList(swipe));
  }

  /**
   * Performs a swipe on the given element
   *
   * @param xpath
   *            The xpath locator of the element to swipe on.
   * @param startPercent
   *            The starting percentage of the element width for the swipe.
   * @param endPercent
   *            The ending percentage of the element width for the swipe.
   * @param duration
   *            The duration of the swipe.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipe(String xpath, double startPercent, double endPercent, Duration duration) {
    swipe(By.xpath(xpath), startPercent, endPercent, duration);
  }

  /**
   * Performs a swipe on the given element
   *
   * @param elementLocator
   *            The By locator of the element to swipe on.
   * @param startPercent
   *            The starting percentage of the element width for the swipe.
   * @param endPercent
   *            The ending percentage of the element width for the swipe.
   * @param duration
   *            The duration of the swipe.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipe(By elementLocator, double startPercent, double endPercent, Duration duration) {
    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    WebElement element = driver.findElement(elementLocator);

    int startX = (int) (element.getLocation().getX() + element.getSize().getWidth() * (startPercent / 100));
    int endX = (int) (element.getLocation().getX() + element.getSize().getWidth() * (endPercent / 100));
    int centerY = element.getLocation().getY() + element.getSize().getHeight() / 2;

    swipe(startX, centerY, endX, duration);
  }

  /**
   * Scrolls the screen by X percentage
   *
   * @param scrollPercentage
   *            percentage of the screen to scroll
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void scrollByPercent(int scrollPercentage) {
    double percentage = (double) scrollPercentage / 100.0;
    Dimension size = driver.manage().window().getSize();
    int startX = size.width / 2;
    int startY = (int) (size.height * (1 - percentage));
    int endY = (int) (size.height * percentage);

    scroll(startX, startY, endY, Duration.ofMillis(500));
  }

  /**
   * Refreshes the screen by swiping down the screen
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipeDownAndRefreshPage() {
    int deviceWidth = driver.manage().window().getSize().getWidth();
    int deviceHeight = driver.manage().window().getSize().getHeight();
    int midX = (deviceWidth / 2);
    int midY = (deviceHeight / 2);
    int bottomEdge = (int) (deviceHeight * 0.85f);

    scroll(midX, midY, bottomEdge, Duration.ofMillis(800));
  }

  /**
   * Tries to find an element by Scrolls the screen with a total scroll of 2
   * times. Returns true if it finds the element.
   *
   * @param locator
   *            element locator
   * @return true/false
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean checkIsElementPresentAllowScrolling(By locator) {
    for (int i = 0; i < 5; i++) {
      if (waitAndCheckIsVisible(locator, Duration.ofSeconds(2)))
        return true;
      else {
        scrollByPercent(10);
        if (waitAndCheckIsVisible(locator, Duration.ofSeconds(2)))
          return true;
      }
    }
    return false;
  }

  /**
   * Tries to find an element by Scrolls the screen with a total scroll of 2
   * times. Returns true if it finds the element.
   *
   * @param xpath
   *            Xpath of the element
   * @return true/false
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean checkIsElementPresentAllowScrolling(String xpath) {
    return checkIsElementPresentAllowScrolling(By.xpath(xpath));
  }

  /**
   * Tries to find an element by Scrolls the screen with a total scroll of 2
   * times. Returns true if it finds the element.
   *
   * @param locator
   *            element locator
   * @param maxScrolls
   *            maximum number of scrolls
   * @return true/false
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean checkIsElementPresentAllowScrolling(By locator, int maxScrolls) {
    for (int i = 0; i < maxScrolls; i++) {
      if (waitAndCheckIsVisible(locator, Duration.ofSeconds(2)))
        return true;
      else {
        scrollByPercent(10);
        if (waitAndCheckIsVisible(locator, Duration.ofSeconds(2)))
          return true;
      }
    }
    return false;
  }

  /**
   * Tries to find an element by Scrolls the screen with a total scroll of 2
   * times. Returns true if it finds the element.
   *
   * @param locator
   *            element locator
   * @param scrollPercentAtATime
   *            Percentage of scroll
   * @param maxScrolls
   *            maximum number of scrolls
   * @return true/false
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean checkIsElementPresentAllowScrolling(By locator, int scrollPercentAtATime, int maxScrolls) {
    for (int i = 0; i < maxScrolls; i++) {
      if (waitAndCheckIsVisible(locator, Duration.ofSeconds(2)))
        return true;
      else {
        scrollByPercent(scrollPercentAtATime);
        if (waitAndCheckIsVisible(locator, Duration.ofSeconds(2)))
          return true;
      }
    }
    return false;
  }

  /**
   * Tries to find an element by Scrolls the screen with a total scroll of 2
   * times. Returns true if it finds the element.
   *
   * @param xpath
   *            Xpath of the element
   * @param scrollPercentAtATime
   *            Percentage of scroll
   * @param maxScrolls
   *            maximum number of scrolls
   * @return true/false
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean checkIsElementPresentAllowScrolling(String xpath, int scrollPercentAtATime, int maxScrolls) {
    return checkIsElementPresentAllowScrolling(By.xpath(xpath), scrollPercentAtATime, maxScrolls);
  }

  /**
   * Scrolls to the element and performs a click.
   *
   * @param element
   *            - By element
   * @return boolean - true if the scroll and click were successful, false
   *         otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean scrollAndClick(By element) {
    try {
      if (checkIsElementPresentAllowScrolling(element)) {
        click(element);
        return true;
      } else
        return false;
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
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean scrollAndClick(String xpath) {
    return scrollAndClick(By.xpath(xpath));
  }

  /**
   * Scrolls to the element and performs a click.
   *
   * @param element
   *            - By element
   * @param scrollPercentAtATime
   *            Percentage of scroll
   * @param maxScrolls
   *            maximum number of scrolls
   * @return boolean - true if the scroll and click were successful, false
   *         otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean scrollAndClick(By element, int scrollPercentAtATime, int maxScrolls) {
    try {
      if (checkIsElementPresentAllowScrolling(element, scrollPercentAtATime, maxScrolls)) {
        click(element);
        return true;
      } else
        return false;
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
   * @param scrollPercentAtATime
   *            Percentage of scroll
   * @param maxScrolls
   *            maximum number of scrolls
   * @return boolean - true if the scroll and click were successful, false
   *         otherwise
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public boolean scrollAndClick(String xpath, int scrollPercentAtATime, int maxScrolls) {
    return scrollAndClick(By.xpath(xpath), scrollPercentAtATime, maxScrolls);
  }

  /**
   * Swipe right on the given element using the mobile:swipe command in Appium.
   *
   * @param element
   *            The By locator of the element to swipe on.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipeRight(By element) {
    swipe(element, 10, 90, Duration.ofMillis(500));
  }

  /**
   * Swipe right on the given element using the mobile:swipe command in Appium.
   *
   * @param xpath
   *            The Xpath locator of the element to swipe on.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipeRight(String xpath) {
    swipe(By.xpath(xpath), 10, 90, Duration.ofMillis(500));
  }

  /**
   * Swipe left on the given element using the mobile:swipe command in Appium.
   *
   * @param xpath
   *            The xpath locator of the element to swipe on.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipeLeft(String xpath) {
    swipe(By.xpath(xpath), 90, 10, Duration.ofMillis(500));
  }

  /**
   * Swipe left on the given element using the mobile:swipe command in Appium.
   *
   * @param element
   *            The By locator of the element to swipe on.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void swipeLeft(By element) {
    swipe(element, 90, 10, Duration.ofMillis(500));
  }

  /**
   * Clicks on the element identified by the given XPath using JavaScript.
   *
   * @param xpath
   *            The XPath of the element to click.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void javaScriptClick(String xpath) {
    advanceClick(xpath);
  }

  /**
   * Clicks on the element identified by the given XPath using JavaScript.
   *
   * @param element
   *            The locator of the element to click.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public void javaScriptClick(By element) {
    advanceClick(element);
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
  public void javaScriptFillText(String xpath, String value) {
    getElement(xpath).sendKeys(value);
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
    getElement(element).sendKeys(value);
  }
}
