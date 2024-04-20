package io.github.the_sdet.common;

import io.github.the_sdet.logger.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Calendar;

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
   * Represents the String '.com'
   */
  public static String DOT_COM = ".com";
  /**
   * Represents the String '@'
   */
  public static String AT = "@";

  /**
   * Holds the generated ten-digit number by method
   * getPhoneNumberBasedOnTimestamp()
   */
  public static String generatedTenDigitNumber = null;

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

  /**
   * Generates a ten-digit phone number based on timestamp using format ddMMHHmmss
   *
   * @return ten-digit number
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public String getPhoneNumberBasedOnTimestamp() {
    Calendar calendar = Calendar.getInstance();

    // Format the date and time components as strings
    SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMHHmmss");
    String formattedDate = dateFormat.format(calendar.getTime());

    // Concatenate the formatted date components to generate a 10-digit number
    String tenDigitNumber = formattedDate.substring(0, 2) + formattedDate.substring(2, 4)
        + formattedDate.substring(4, 6) + formattedDate.substring(6, 8) + formattedDate.substring(8, 10);
    Log.info("Generated Phone Number: " + tenDigitNumber);
    generatedTenDigitNumber = tenDigitNumber;
    return tenDigitNumber;
  }

  /**
   * Generates an email based on timestamp using format ddMMHHmmss E.g., if prefix
   * is test and domain is gmail, it will generate - test20041149@gmail.com
   *
   * @param prefix
   *            prefix of email
   * @param domain
   *            domain of email
   * @return ten-digit number
   * @author Pabitra Swain (pabitra.swain.work@gmail.com)
   */
  public String getEmailBasedOnTimestamp(String prefix, String domain) {
    if (generatedTenDigitNumber == null)
      return prefix + getPhoneNumberBasedOnTimestamp() + AT + domain + ".com";
    else
      return prefix + generatedTenDigitNumber + AT + domain + DOT_COM;
  }
}