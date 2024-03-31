package io.github.the_sdet.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class handles all JSON related Utilities and Helper methods
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings("unused")
public class XMLUtils {
  /**
   * Reads an XML file and returns a Document object representing its structure.
   *
   * @param filePath
   *            The path to the XML file to be read.
   * @return A Document object representing the XML structure.
   * @throws Exception
   *             if an error occurs during parsing or reading the file.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Document readXML(String filePath) throws Exception {
    // Create a DocumentBuilderFactory
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    // Create a DocumentBuilder
    DocumentBuilder builder = factory.newDocumentBuilder();

    // Parse the XML file and return the Document object
    return builder.parse(new File(filePath));
  }

  /**
   * Saves an XML document to a file.
   *
   * @param doc
   *            The Document object representing the XML structure.
   * @param filePath
   *            The path to the file where the XML document will be saved.
   * @throws Exception
   *             if an error occurs during the transformation or writing to the
   *             file.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void saveXML(Document doc, String filePath) throws Exception {
    // Create a TransformerFactory
    TransformerFactory transformerFactory = TransformerFactory.newInstance();

    // Create a Transformer
    Transformer transformer = transformerFactory.newTransformer();

    // Create a DOMSource from the Document
    DOMSource source = new DOMSource(doc);

    // Create a StreamResult with the specified file path
    StreamResult result = new StreamResult(new File(filePath));

    // Transform and save the XML document
    transformer.transform(source, result);
  }

  /**
   * Updates the value of an XML element identified by its XPath.
   *
   * @param doc
   *            The Document object representing the XML structure.
   * @param elementXpath
   *            The XPath expression to identify the XML element.
   * @param value
   *            The new value to set for the XML element.
   * @throws Exception
   *             if an error occurs during XPath evaluation or updating the XML
   *             element.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void updateXMLData(Document doc, String elementXpath, String value) throws Exception {
    XPath xpath = XPathFactory.newInstance().newXPath();
    XPathExpression expr = xpath.compile(elementXpath);
    Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
    if (node != null) {
      node.setTextContent(value);
    }
  }

  /**
   * Deletes an XML node identified by its XPath.
   *
   * @param doc
   *            The Document object representing the XML structure.
   * @param elementXpath
   *            The XPath expression to identify the XML node.
   * @throws Exception
   *             if an error occurs during XPath evaluation or deleting the XML
   *             node.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static void deleteXMLNode(Document doc, String elementXpath) throws Exception {
    XPath xpath = XPathFactory.newInstance().newXPath();
    XPathExpression expr = xpath.compile(elementXpath);
    Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
    if (node != null) {
      node.getParentNode().removeChild(node);
    }
  }

  /**
   * Extracts content from an XML response using a regular expression.
   *
   * @param response
   *            The XML response string.
   * @param regex
   *            The regular expression to match the content.
   * @return The content extracted from the XML response.
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static String getContentFromXmlResponse(String response, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(response);
    if (matcher.find()) {
      return matcher.group(1); // Assuming the first group captures the desired content
    }
    return null;
  }
}