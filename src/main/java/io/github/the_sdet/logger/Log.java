package io.github.the_sdet.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

/**
 * Utility class for logging messages using Log4j. This class provides methods
 * for logging messages at different levels: INFO, ERROR, DEBUG, and WARN.
 * <p>
 * The logger is initialized statically, ensuring that it's configured and ready
 * for use. The static block configures the logger, setting its log level to
 * DEBUG, and then initializes it. This ensures that the logger is properly
 * configured before any logging operations are performed.
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings("unused")
public class Log {

  private static final Logger logger;

  static {
    // Configure the logger (example: setting log level to DEBUG)
    Configurator.setLevel(LogManager.getRootLogger().getName(), org.apache.logging.log4j.Level.DEBUG);

    // Initialize the logger
    logger = LogManager.getLogger(Log.class);
  }

  /**
   * Log an informational message.
   *
   * @param message
   *            The message to log.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void info(String message) {
    logger.info(message);
  }

  /**
   * Log an error message.
   *
   * @param message
   *            The message to log.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void error(String message) {
    logger.error(message);
  }

  /**
   * Log an error message with an associated exception.
   *
   * @param message
   *            The message to log.
   * @param e
   *            The exception to include in the log.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void error(String message, Exception e) {
    logger.error(message, e);
  }

  /**
   * Log a debug message.
   *
   * @param message
   *            The message to log.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void debug(String message) {
    logger.debug(message);
  }

  /**
   * Log a warning message.
   *
   * @param message
   *            The message to log.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void warn(String message) {
    logger.warn(message);
  }
}
