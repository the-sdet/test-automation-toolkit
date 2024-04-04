package io.github.the_sdet.common;

import io.github.the_sdet.logger.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * This class handles various common Utilities and Helper methods
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings("unused")
public class CommonUtils {
  /**
   * Represents a String without content.
   */
  public static String EMPTY_STRING = "";

  /**
   * This method extracts numeric value from a string. E.g., -$300.00 will return
   * 300.00
   *
   * @param elementText
   *            String value from which number to be extracted.
   * @return number in double format
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public static double getNumericValue(String elementText) {
    return Double.parseDouble(elementText.replaceAll("[^\\d.]", EMPTY_STRING));
  }

  /**
   * This method extracts numeric integer value from a string. E.g., -$300.00 will
   * return 300
   *
   * @param elementText
   *            String value from which number to be extracted.
   * @return number in integer format
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public static int getIntegerValue(String elementText) {
    return (int) Double.parseDouble(elementText.replaceAll("[^\\d.]", EMPTY_STRING));
  }

  /**
   * Waits for the specified duration.
   *
   * @param duration
   *            The duration to wait.
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public static void waitFor(Duration duration) {
    try {
      Thread.sleep(duration.toMillis());
    } catch (InterruptedException e) {
      Log.error("Error while applying wait...", e);
    }
    Log.info(duration.toSeconds() + " seconds of wait completed...");
  }

  /**
   * Replaces line breaks in the input string with spaces.
   *
   * @param input
   *            The input string.
   * @return The input string with line breaks replaced by spaces.
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public static String replaceLineBreaksWithSpace(String input) {
    if (input == null) {
      return null;
    }
    return input.replaceAll("\\r?\\n", " ");
  }

  /**
   * Enum representing the status of a test scenario.
   * <p>
   * This enum defines two possible statuses: PASS and FAIL.
   * </p>
   *
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public enum STATUS {
    /**
     * Represents status PASS.
     */
    PASS("PASS"),

    /**
     * Represents status FAIL.
     */
    FAIL("FAIL");

    /**
     * The status string.
     */
    public final String status;

    /**
     * Constructs a STATUS enum with the given status string.
     *
     * @param status
     *            the status string
     */
    STATUS(String status) {
      this.status = status;
    }
  }

  /**
   * Format a date string from one format to another.
   *
   * @param inputDateStr
   *            The input date string.
   * @param inputDateFormat
   *            The input date format.
   * @param outputDateFormat
   *            The output date format.
   * @return the formatted date string.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static String formatDate(String inputDateStr, String inputDateFormat, String outputDateFormat) {
    DateFormat inputFormatter = new SimpleDateFormat(inputDateFormat);
    DateFormat outputFormatter = new SimpleDateFormat(outputDateFormat);
    try {
      Date inputDate = inputFormatter.parse(inputDateStr);
      return outputFormatter.format(inputDate);
    } catch (ParseException e) {
      Log.error("Parse Exception...", e);
      return null;
    }
  }
}