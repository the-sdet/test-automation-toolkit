package io.github.the_sdet.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * This class handles all API related Utilities and Helper methods
 *
 * @author Pabitra Swain (contact.the.sdet@gmail.com)
 */
@SuppressWarnings("unused")
public class APIUtils {
  /**
   * Sends a GET request to the specified URL and returns the response.
   *
   * @param url
   *            The URL to send the request to
   * @param contentType
   *            Content Type
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response getRequest(String url, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).contentType(contentType).when().log().all()
        .get(url).then().log().all().extract().response();
  }

  /**
   * Sends a GET request to the specified URL with custom headers and returns the
   * response.
   *
   * @param url
   *            The URL to send the request to
   * @param headers
   *            The headers to include in the request
   * @param contentType
   *            Content Type
   * @return The response received Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response getRequest(String url, Map<String, String> headers, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).contentType(contentType)
        .when().log().all().get(url).then().log().all().extract().response();
  }

  /**
   * Sends a GET request with query parameters to the specified URL and returns
   * the response.
   *
   * @param url
   *            The base URL to send the request to
   * @param queryParams
   *            The query parameters to include in the request
   * @param contentType
   *            Content Type
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response getRequest(String url, ContentType contentType, Map<String, String> queryParams) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).queryParams(queryParams)
        .contentType(contentType).when().log().all().get(url).then().log().all().extract().response();
  }

  /**
   * Sends a GET request with query parameters to the specified URL and returns
   * the response.
   *
   * @param url
   *            The base URL to send the request to
   * @param headers
   *            The headers to include in the request
   * @param queryParams
   *            The query parameters to include in the request
   * @param contentType
   *            Content Type
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response getRequest(String url, Map<String, String> headers, Map<String, String> queryParams,
      ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).queryParams(queryParams)
        .contentType(contentType).when().log().all().get(url).then().log().all().extract().response();
  }

  /**
   * Sends a POST request to the specified URL with the given request body and
   * returns the response.
   *
   * @param url
   *            The URL to send the request to
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            Content Type
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response postRequest(String url, String requestBody, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).body(requestBody).contentType(contentType)
        .when().log().all().post(url).then().log().all().extract().response();
  }

  /**
   * Sends a POST request to the specified URL with custom headers and returns the
   * response.
   *
   * @param url
   *            The URL to send the request to
   * @param headers
   *            The headers to include in the request
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            Content Type
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response postRequest(String url, Map<String, String> headers, String requestBody,
      ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).body(requestBody)
        .contentType(contentType).when().log().all().post(url).then().log().all().extract().response();
  }

  /**
   * Sends a POST request to the specified URL with base URI, endpoint, and the
   * given request body, and returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response postRequest(String baseURI, String endpoint, String requestBody, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).body(requestBody)
        .contentType(contentType).when().log().all().post(endpoint).then().log().all().extract().response();
  }

  /**
   * Sends a POST request to the specified URL with base URI, endpoint, custom
   * headers, request body, and content type, and returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param headers
   *            The headers to include in the request
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response postRequest(String baseURI, String endpoint, Map<String, String> headers, String requestBody,
      ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).headers(headers)
        .body(requestBody).contentType(contentType).when().log().all().post(endpoint).then().log().all()
        .extract().response();
  }

  /**
   * Sends a PUT request to the specified URL with the given request body and
   * returns the response.
   *
   * @param url
   *            The URL to send the request to
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            Content Type
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response putRequest(String url, String requestBody, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).body(requestBody).contentType(contentType)
        .when().log().all().put(url).then().log().all().extract().response();
  }

  /**
   * Sends a PUT request to the specified URL with custom headers and returns the
   * response.
   *
   * @param url
   *            The URL to send the request to
   * @param headers
   *            The headers to include in the request
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            Content Type
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response putRequest(String url, Map<String, String> headers, String requestBody,
      ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).body(requestBody)
        .contentType(contentType).when().log().all().put(url).then().log().all().extract().response();
  }

  /**
   * Sends a PUT request to the specified URL with base URI, endpoint, and the
   * given request body, and returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response putRequest(String baseURI, String endpoint, String requestBody, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).body(requestBody)
        .contentType(contentType).when().log().all().put(endpoint).then().log().all().extract().response();
  }

  /**
   * Sends a PUT request to the specified URL with base URI, endpoint, custom
   * headers, request body, and content type, and returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param headers
   *            The headers to include in the request
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response putRequest(String baseURI, String endpoint, Map<String, String> headers, String requestBody,
      ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).headers(headers)
        .body(requestBody).contentType(contentType).when().log().all().put(endpoint).then().log().all()
        .extract().response();
  }

  /**
   * Sends a DELETE request to the specified URL and returns the response.
   *
   * @param url
   *            The URL to send the request to
   * @param contentType
   *            Content Type
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response deleteRequest(String url, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).when().log().all().contentType(contentType)
        .delete(url).then().log().all().extract().response();
  }

  /**
   * Sends a DELETE request to the specified URL and returns the response.
   *
   * @param url
   *            The URL to send the request to
   * @param headers
   *            Headers
   * @param contentType
   *            Content Type
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response deleteRequest(String url, Map<String, String> headers, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).when().log().all()
        .contentType(contentType).delete(url).then().log().all().extract().response();
  }

  /**
   * Sends a DELETE request to the specified URL with base URI and endpoint, and
   * returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response deleteRequest(String baseURI, String endpoint, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).contentType(contentType)
        .when().log().all().delete(endpoint).then().log().all().extract().response();
  }

  /**
   * Sends a DELETE request to the specified URL with base URI, endpoint, custom
   * headers, and content type, and returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param headers
   *            The headers to include in the request
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response deleteRequest(String baseURI, String endpoint, Map<String, String> headers,
      ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).headers(headers)
        .contentType(contentType).when().log().all().delete(endpoint).then().log().all().extract().response();
  }

  /**
   * Sends a PATCH request to the specified URL with the given request body and
   * returns the response.
   *
   * @param url
   *            The URL to send the request to
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            Content Type
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response patchRequest(String url, String requestBody, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).body(requestBody).contentType(contentType)
        .when().log().all().patch(url).then().log().all().extract().response();
  }

  /**
   * Sends a PATCH request to the specified URL with custom headers and returns
   * the response.
   *
   * @param url
   *            The URL to send the request to
   * @param headers
   *            The headers to include in the request
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            Content Type
   * @return The response received Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response patchRequest(String url, Map<String, String> headers, String requestBody,
      ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).body(requestBody)
        .contentType(contentType).when().log().all().patch(url).then().log().all().extract().response();
  }

  /**
   * Sends a PATCH request to the specified URL with base URI and endpoint, the
   * given request body, and returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response patchRequest(String baseURI, String endpoint, String requestBody, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).body(requestBody)
        .contentType(contentType).when().log().all().patch(endpoint).then().log().all().extract().response();
  }

  /**
   * Sends a PATCH request to the specified URL with base URI, endpoint, custom
   * headers, request body, and content type, and returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param headers
   *            The headers to include in the request
   * @param requestBody
   *            The request body to send
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response patchRequest(String baseURI, String endpoint, Map<String, String> headers,
      String requestBody, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).headers(headers)
        .body(requestBody).contentType(contentType).when().log().all().patch(endpoint).then().log().all()
        .extract().response();
  }

  /**
   * Sends a HEAD request to the specified URL and returns the response.
   *
   * @param url
   *            The URL to send the request to
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response headRequest(String url, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).contentType(contentType).when().log().all()
        .head(url).then().log().all().extract().response();
  }

  /**
   * Sends a HEAD request to the specified URL with custom headers and returns the
   * response.
   *
   * @param url
   *            The URL to send the request to
   * @param headers
   *            The headers to include in the request
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response headRequest(String url, Map<String, String> headers, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).contentType(contentType)
        .when().log().all().head(url).then().log().all().extract().response();
  }

  /**
   * Sends a HEAD request to the specified URL with base URI and endpoint and
   * returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param contentType
   *            The content type of the request\
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response headRequest(String baseURI, String endpoint, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).contentType(contentType)
        .when().log().all().head(endpoint).then().log().all().extract().response();
  }

  /**
   * Sends a HEAD request to the specified URL with base URI, endpoint, custom
   * headers, and returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param headers
   *            The headers to include in the request
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response headRequest(String baseURI, String endpoint, Map<String, String> headers,
      ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).headers(headers)
        .contentType(contentType).when().log().all().head(endpoint).then().log().all().extract().response();
  }

  /**
   * Sends an OPTIONS request to the specified URL and returns the response.
   *
   * @param url
   *            The URL to send the request to
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response optionsRequest(String url, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).contentType(contentType).when().log().all()
        .options(url).then().log().all().extract().response();
  }

  /**
   * Sends an OPTIONS request to the specified URL with custom headers and returns
   * the response.
   *
   * @param url
   *            The URL to send the request to
   * @param headers
   *            The headers to include in the request
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response optionsRequest(String url, Map<String, String> headers, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).headers(headers).contentType(contentType)
        .when().log().all().options(url).then().log().all().extract().response();
  }

  /**
   * Sends an OPTIONS request to the specified URL with base URI and endpoint and
   * returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response optionsRequest(String baseURI, String endpoint, ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).contentType(contentType)
        .when().log().all().options(endpoint).then().log().all().extract().response();
  }

  /**
   * Sends an OPTIONS request to the specified URL with base URI, endpoint, custom
   * headers, and returns the response.
   *
   * @param baseURI
   *            The base URI of the API
   * @param endpoint
   *            The endpoint of the API
   * @param headers
   *            The headers to include in the request
   * @param contentType
   *            The content type of the request
   * @return The response received
   * @author Pabitra Swain (contact.the.sdet@gmail.com)
   */
  public static Response optionsRequest(String baseURI, String endpoint, Map<String, String> headers,
      ContentType contentType) {
    return given().relaxedHTTPSValidation().urlEncodingEnabled(false).baseUri(baseURI).headers(headers)
        .contentType(contentType).when().log().all().options(endpoint).then().log().all().extract().response();
  }
}