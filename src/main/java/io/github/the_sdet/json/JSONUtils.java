package io.github.the_sdet.json;

import com.jayway.jsonpath.JsonPath;
import io.github.the_sdet.logger.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * This class handles all JSON related Utilities and Helper methods
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings("unused")
public class JSONUtils {
  /**
   * This method reads a JSON file and returns the content as String
   *
   * @param filePath
   *            JSON file path
   * @return String content of the file
   * @throws IOException
   *             throws IOException
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static String readJsonFromFile(String filePath) throws IOException {
    try {
      Path path = new File(filePath).toPath();
      return new String(Files.readAllBytes(path));
    } catch (IOException e) {
      throw new IOException("Unable to read JSON file: " + filePath, e);
    }
  }

  /**
   * This method writes JSON String to a JSON file
   *
   * @param jsonString
   *            JSON String
   * @param filePath
   *            JSON file path
   * @throws IOException
   *             throws IOException
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void writeJsonToFile(String jsonString, String filePath) throws IOException {
    try (FileWriter fileWriter = new FileWriter(filePath)) {
      fileWriter.write(jsonString);
    } catch (IOException e) {
      throw new IOException("Unable to write JSON string to file: " + filePath, e);
    }
  }

  /**
   * This method reads the value of an element from JSON String using json path
   *
   * @param jsonString
   *            JSON String
   * @param elementPath
   *            element path to extract value
   * @return String value of element from JSON
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static String getElementValueFromJsonString(String jsonString, String elementPath) {
    return JsonPath.read(jsonString, elementPath).toString();
  }

  /**
   * This method updates the value of an element in JSON String using json path
   * and returns the JSON String
   *
   * @param jsonString
   *            JSON String
   * @param elementPath
   *            element path which value is to be replaced
   * @param elementValueToReplaced
   *            new value to replace
   * @return updated JSON String
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static String updateElementFromJsonString(String jsonString, String elementPath,
      String elementValueToReplaced) {
    Log.info("Replacing existing value of Element " + getElementValueFromJsonString(jsonString, elementPath)
        + " with " + elementValueToReplaced);
    return JsonPath.parse(jsonString).set(elementPath, elementValueToReplaced).jsonString();
  }

  /**
   * This method reads the values from JSON String using JSON path
   *
   * @param jsonString
   *            JSON String
   * @param elementPath
   *            element path to extract value
   * @return List of String values of elements from JSON
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  @SuppressWarnings("unchecked")
  public static List<String> getElementsFromJsonString(String jsonString, String elementPath) {
    Object value = JsonPath.read(jsonString, elementPath);
    if (value instanceof List)
      return (List<String>) value;
    else
      return Collections.singletonList(value.toString());
  }

  /**
   * This method reads the values of multiple elements from JSON String using JSON
   * path
   *
   * @param jsonString
   *            JSON String
   * @param elementPaths
   *            element paths
   * @return List of String values of elements from JSON
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static List<String> getElementsFromJsonString(String jsonString, String... elementPaths) {
    List<String> values = new ArrayList<>();
    for (String elementPath : elementPaths) {
      values.add(getElementValueFromJsonString(jsonString, elementPath.trim()));
    }
    return values;
  }

  /**
   * This method reads the values of multiple elements from JSON String using JSON
   * path
   *
   * @param jsonString
   *            JSON String
   * @param elementPaths
   *            element paths
   * @return Map of keys and values from JSON
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Map<String, String> getElementsFromJsonString(String jsonString, Map<String, String> elementPaths) {
    Map<String, String> values = new HashMap<>();
    for (Map.Entry<String, String> element : elementPaths.entrySet()) {
      values.put(element.getKey(), getElementValueFromJsonString(jsonString, element.getValue().trim()));
    }
    return values;
  }
}
