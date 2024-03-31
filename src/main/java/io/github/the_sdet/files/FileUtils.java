package io.github.the_sdet.files;

import io.github.the_sdet.logger.Log;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/**
 * This class handles all File Handling related Utilities and Helper methods
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings("unused")
public class FileUtils {
  /**
   * checks existence of a file
   *
   * @param fileNameWithPath
   *            provide file name with path
   * @return true/false - returns true if the file exists
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static boolean checkFileExistence(String fileNameWithPath) {
    File file = new File(fileNameWithPath);
    return file.exists();
  }

  /**
   * This method deletes the files and folders inside a directory
   *
   * @param folderPath
   *            folder path
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @SuppressWarnings("ResultOfMethodCallIgnored")
  public static void cleanOrCreateDirectory(String folderPath) {
    try {
      org.apache.commons.io.FileUtils.cleanDirectory(new File(folderPath));
    } catch (Exception e) {
      File directory = new File(folderPath);
      if (!directory.exists()) {
        directory.mkdirs();
        Log.info("Directory '" + folderPath + "' Created.. ");
      }
    }
  }

  /**
   * Copies a file from the source to the destination.
   *
   * @param source
   *            The source file.
   * @param destination
   *            The destination file.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void copyFile(File source, File destination) {
    try {
      org.apache.commons.io.FileUtils.copyFile(source, destination);
      Log.info("File saved to: " + destination);
    } catch (IOException e) {
      Log.error("Could NOT save screenshot...", e);
    }
  }

  /**
   * Copies a file from the source to the destination with metadata.
   *
   * @param source
   *            The source file.
   * @param destination
   *            The destination file.
   * @param metadata
   *            The metadata information.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void copyFile(File source, File destination, String metadata) {
    try {
      org.apache.commons.io.FileUtils.copyFile(source, destination);
      Log.info(metadata + " saved to: " + destination);
    } catch (IOException e) {
      Log.error("Could NOT save " + metadata + "...", e);
    }
  }

  /**
   * Converts a byte array to a file.
   *
   * @param data
   *            The byte array data.
   * @param type
   *            jpg/png
   * @return The file created from the byte array.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static File byteArrayToFile(byte[] data, String type) {
    File tempFile = null;
    try {
      // Wrap byte array into ByteArrayInputStream
      InputStream inputStream = new ByteArrayInputStream(data);

      // Create temporary file
      tempFile = File.createTempFile("temp", "." + type);

      // Create FileOutputStream without specifying a file path
      OutputStream outputStream = new FileOutputStream(tempFile);

      // Read from input stream and write to output stream
      byte[] buffer = new byte[1024];
      int length;
      while ((length = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, length);
      }

      // Close streams
      inputStream.close();
      outputStream.close();
    } catch (IOException e) {
      Log.error("Couldn't save data to a file...", e);
    }
    return tempFile;
  }

  /**
   * creates filename appending timestamp
   *
   * @param fileName
   *            provide filename
   * @param timeStampPattern
   *            provide time stamp pattern
   * @return Custom filename
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static String fileNameWithTimeStamp(String fileName, String timeStampPattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(timeStampPattern);
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    return fileName + "_" + sdf.format(timestamp);
  }
}
