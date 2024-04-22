package io.github.the_sdet.cucumber;

import com.microsoft.playwright.Page;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Scenario;
import io.github.the_sdet.logger.Log;
import io.github.the_sdet.web.PlaywrightUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for Cucumber use cases.
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class CucumberUtils {

  /**
   * ThreadLocal variable to hold the current scenario.
   */
  public static ThreadLocal<Scenario> tlScenario = new ThreadLocal<>();

  /**
   * HTML style for success log messages.
   */
  private static final String SUCCESS_LOG = "<span style='color: #06980e;'>$message</span>";

  /**
   * HTML style for failure log messages.
   */
  private static final String FAILURE_LOG = "<span style='color: red;'>$message</span>";

  /**
   * HTML style for warning log messages.
   */
  private static final String WARN_LOG = "<span style='color: #ff8800;'>$message</span>";

  /**
   * HTML style for skip log messages.
   */
  private static final String SKIP_LOG = "<span style='color: #d4d170;'>$message</span>";

  /**
   * HTML style for abort log messages.
   */
  private static final String ABORT_LOG = "<span style='color: #5c5c5c;'>$message</span>";

  /**
   * Retrieves the current Cucumber scenario.
   *
   * @return The current scenario
   * @throws NullPointerException
   *             if the scenario is not set
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Scenario getCurrentScenario() {
    if (tlScenario.get() == null)
      throw new NullPointerException("Set Scenario First...");
    else
      return tlScenario.get();
  }

  /**
   * Sets the current Cucumber scenario.
   *
   * @param scenario
   *            The scenario to set
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void setCurrentScenario(Scenario scenario) {
    tlScenario.set(scenario);
  }

  /**
   * Attaches a Base64 encoded screenshot from a WebDriver to the current
   * scenario.
   *
   * @param driver
   *            The WebDriver instance
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void attachBase64Screenshot(WebDriver driver) {
    String base64Image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    getCurrentScenario().log("<img src=data:image/png;base64," + base64Image + ">");
  }

  /**
   * Attaches a Base64 encoded screenshot from a AppiumDriver to the current
   * scenario.
   *
   * @param driver
   *            The WebDriver instance
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void attachBase64Screenshot(AppiumDriver driver) {
    String base64Image = driver.getScreenshotAs(OutputType.BASE64);
    getCurrentScenario().log("<img src=data:image/png;base64," + base64Image + ">");
  }

  /**
   * Attaches a Base64 encoded screenshot from a Playwright page to the current
   * scenario.
   *
   * @param page
   *            The Playwright page instance
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void attachBase64Screenshot(Page page) {
    String base64Image = new PlaywrightUtils(page).getScreenshotAsBase64();
    getCurrentScenario().log("<img src=data:image/png;base64," + base64Image + ">");
  }

  /**
   * Attaches a screenshot from a WebDriver to the current scenario.
   *
   * @param driver
   *            The WebDriver instance
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void attachScreenshot(WebDriver driver) {
    getCurrentScenario().attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
        "Attached Image");
  }

  /**
   * Attaches a screenshot from a WebDriver to the current scenario.
   *
   * @param driver
   *            The WebDriver instance
   * @param screenshotName
   *            screenshot attachment name
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void attachScreenshot(WebDriver driver, String screenshotName) {
    getCurrentScenario().attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png",
        screenshotName);
  }

  /**
   * Attaches a screenshot from a AppiumDriver to the current scenario.
   *
   * @param driver
   *            The WebDriver instance
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void attachScreenshot(AppiumDriver driver) {
    getCurrentScenario().attach(driver.getScreenshotAs(OutputType.BYTES), "image/png", "Attached Image");
  }

  /**
   * Attaches a screenshot from a AppiumDriver to the current scenario.
   *
   * @param driver
   *            The WebDriver instance
   * @param screenshotName
   *            screenshot attachment name
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void attachScreenshot(AppiumDriver driver, String screenshotName) {
    getCurrentScenario().attach(driver.getScreenshotAs(OutputType.BYTES), "image/png", screenshotName);
  }

  /**
   * Attaches a screenshot from a AppiumDriver to the current scenario.
   *
   * @param page
   *            The Playwright page instance
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void attachScreenshot(Page page) {
    getCurrentScenario().attach(page.screenshot(), "image/png", "Attached Image");
  }

  /**
   * Attaches a screenshot from a AppiumDriver to the current scenario.
   *
   * @param page
   *            The Playwright page instance
   * @param screenshotName
   *            screenshot attachment name
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void attachScreenshot(Page page, String screenshotName) {
    getCurrentScenario().attach(page.screenshot(), "image/png", screenshotName);
  }

  /**
   * Logs a generic message to the report and to the application log.
   *
   * @param text
   *            The message to log
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void logToReport(String text) {
    getCurrentScenario().log(text);
    Log.info(text);
  }

  /**
   * Logs a success message to the report and to the application log.
   *
   * @param message
   *            The success message to log
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void logSuccessToReport(String message) {
    getCurrentScenario().log(SUCCESS_LOG.replace("$message", message));
    Log.info(message);
  }

  /**
   * Logs a failure message to the report and to the application log.
   *
   * @param message
   *            The failure message to log
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void logFailureToReport(String message) {
    getCurrentScenario().log(FAILURE_LOG.replace("$message", message));
    Log.info(message);
  }

  /**
   * Logs a warning message to the report and to the application log.
   *
   * @param message
   *            The warning message to log
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void logWarningToReport(String message) {
    getCurrentScenario().log(WARN_LOG.replace("$message", message));
    Log.info(message);
  }

  /**
   * Logs a skip message to the report and to the application log.
   *
   * @param message
   *            The skip message to log
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void logSkipToReport(String message) {
    getCurrentScenario().log(SKIP_LOG.replace("$message", message));
    Log.info(message);
  }

  /**
   * Logs an abort message to the report and to the application log.
   *
   * @param message
   *            The abort message to log
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void logAbortToReport(String message) {
    getCurrentScenario().log(ABORT_LOG.replace("$message", message));
    Log.info(message);
  }

  /**
   * Extracts the feature name from the given Scenario object.
   *
   * @param scenario
   *            The Scenario object from Cucumber.
   * @param withPackageName
   *            A boolean indicating whether to include package name along with
   *            feature name.
   * @return The feature name extracted from the Scenario.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static String getFeatureNameFromScenario(Scenario scenario, boolean withPackageName) {
    String uri = scenario.getUri().toString();
    String featureName, packageName;
    String[] test = uri.split("/");
    if (uri.startsWith("file")) {
      int size = test.length;
      featureName = test[size - 1].split("\\.")[0];
      packageName = test[size - 2];
    } else {
      featureName = test[1].split("\\.")[0];
      packageName = test[0].split(":")[1];
    }
    Log.info("Feature: " + featureName);
    Log.info("Package: " + packageName);
    if (withPackageName)
      return packageName + " - " + featureName;
    else
      return featureName;
  }

  /**
   * Retrieves all tags associated with the provided Scenario object.
   *
   * @param scenario
   *            the Scenario object from which to retrieve the tags.
   * @return a List containing all tags associated with the provided Scenario
   *         object, without the leading '@' symbol.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static List<String> getAllTagsOnScenario(Scenario scenario) {
    return scenario.getSourceTagNames().stream().map(e -> e.substring(1)).collect(Collectors.toList());
  }
}
