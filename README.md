[//]: # (# Test Automation Toolkit)

<img src="src/test/resources/logo.jpg" alt="Test Automation Toolkit"/>

[![Maven Central](https://img.shields.io/maven-central/v/io.github.the-sdet/test-automation-toolkit)](https://search.maven.org/artifact/io.github.the-sdet/test-automation-toolkit)
[![javadoc](https://javadoc.io/badge2/io.github.the-sdet/test-automation-toolkit/javadoc.svg)](https://javadoc.io/doc/io.github.the-sdet/test-automation-toolkit)

## Streamlining Test Automation for Web, API, Mobile and Database Testing and more

Welcome to the Test Automation Toolkit – Your comprehensive solution for streamlining test automation across
various domains. Whether you're testing Web applications, Mobile Apps, APIs, or interacting with databases, XML, JSON,
Excel, our library
provides a rich set of reusable utility methods tailored to your needs.

With support for popular automation frameworks including Selenium, Playwright, Appium, RestAssured, and JDBC, our
toolkit empowers testers and developers to write efficient and maintainable automation scripts with ease. Say goodbye to
repetitive tasks and boilerplate code – leverage our library to accelerate your testing workflows and ensure the quality
and reliability of your software products.

Start automating smarter, not harder, with our comprehensive suite of utilities. Join our community of testers and
developers, and together, let's redefine test automation. Unlock the potential of automation with the Automation Toolkit
today!

## Usage

Test Automation Toolkit requires Java 11 or newer.

To use Test Automation Toolkit simply add following dependency to your Maven project:

```xml

<dependency>
    <groupId>io.github.the-sdet</groupId>
    <artifactId>test-automation-toolkit</artifactId>
    <version>1.0.4</version>
</dependency>
```

## Example

Multiple ways you can use test-automation-toolkit in your test automation framework.

This code snippet give you a glimpse of how your selenium, playwright, appium code all can look very similar.

### Option 1:

Extend SeleniumUtils/PlaywrightUtils/AppiumUtils to your Page Object Classes.

SeleniumUtils Use in PageObject model

```java
package io.github.the_sdet;

import io.github.the_sdet.web.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageSelenium extends SeleniumUtils {
    public LoginPageSelenium(WebDriver driver) {
        super(driver);
    }

    By logo = By.id("logo");
    By username = By.className("username");
    By password = By.xpath("//input[contains(@name,'password')]");
    By loginButton = By.id("login-btn");

    public boolean isLogoVisible() {
        return isVisible(logo);
    }

    public void login(String email, String pass) {
        fillText(username, email);
        fillText(password, pass);
        click(loginButton);
    }

}
```

AppiumUtils Use in PageObject model

```java
package io.github.the_sdet;

import io.appium.java_client.AppiumDriver;
import io.github.the_sdet.mobile.AppiumUtils;
import org.openqa.selenium.By;

public class LoginPageAppium extends AppiumUtils {
    public LoginPageAppium(AppiumDriver driver) {
        super(driver);
    }

    By logo = By.id("logo");
    By username = By.className("username");
    By password = By.xpath("//input[contains(@name,'password')]");
    By loginButton = By.id("login-btn");

    public boolean isLogoVisible() {
        return isVisible(logo);
    }

    public void login(String email, String pass) {
        fillText(username, email);
        fillText(password, pass);
        click(loginButton);
    }

}
```

PlaywrightUtils Use in PageObject model

```java
package io.github.the_sdet;

import com.microsoft.playwright.Page;
import io.github.the_sdet.web.PlaywrightUtils;

public class LoginPagePlaywright extends PlaywrightUtils {
    public LoginPagePlaywright(Page page) {
        super(page);
    }

    String logo = "#logo";
    String username = ".username";
    String password = "//input[contains(@name,'password')]";
    String loginButton = "#login-btn";

    public boolean isLogoVisible() {
        return isVisible(logo);
    }

    public void login(String email, String pass) {
        fillText(username, email);
        fillText(password, pass);
        click(loginButton);
    }
}
```

### Option 2:

Create an Object of SeleniumUtils/PlaywrightUtils/AppiumUtils wherever you need to use the utils.

SeleniumUtils, PlaywrightUtils and AppiumUtils Use in General Context

```java
package io.github.the_sdet;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.appium.java_client.AppiumDriver;
import io.github.the_sdet.mobile.AppiumUtils;
import io.github.the_sdet.web.PlaywrightUtils;
import io.github.the_sdet.web.SeleniumUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestingTestAutomationToolkit {
    public static void main(String[] args) {
        String url = "https://www.selenium.dev/";
        String readModeAboutSeleniumWebDriver = "//a[contains(@class,'selenium-button selenium-webdriver')]";
        String getStartedButton = "//a[contains(text(),'Getting started')]";

        //Using Selenium Utilities
        WebDriver driver = new ChromeDriver();
        SeleniumUtils seleniumUtils = new SeleniumUtils(driver);
        seleniumUtils.setDefaultTimeOut(Duration.ofSeconds(10));
        seleniumUtils.openPage(url);
        seleniumUtils.setScreenSize(1200, 700);
        seleniumUtils.maximizeScreen();
        seleniumUtils.javaScriptClick(readModeAboutSeleniumWebDriver);
        seleniumUtils.waitAndClick(getStartedButton, Duration.ofSeconds(10));
        seleniumUtils.waitFor(Duration.ofSeconds(1));
        seleniumUtils.getScreenshot();
        seleniumUtils.takeScreenshot("screenshot-by-selenium.png");
        seleniumUtils.takeFullPageScreenshot("full-screenshot-by-selenium.png");
        driver.quit();

        //Using Playwright Utilities
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        Page page = browser.newPage();
        PlaywrightUtils playwrightUtils = new PlaywrightUtils(page);
        playwrightUtils.setDefaultTimeOut(Duration.ofSeconds(10));
        playwrightUtils.openPage(url);
        playwrightUtils.setScreenSize(1200, 700);
        playwrightUtils.maximizeScreen();
        playwrightUtils.javaScriptClick(readModeAboutSeleniumWebDriver);
        playwrightUtils.waitAndClick(getStartedButton, Duration.ofSeconds(10));
        playwrightUtils.waitFor(Duration.ofSeconds(1));
        playwrightUtils.takeScreenshot("screenshot-by-playwright.png");
        playwrightUtils.takeFullPageScreenshot("full-screenshot-by-playwright.png");
        page.close();
        playwright.close();

        //Using Appium Utilities
        AppiumDriver appiumDriver = new AppiumDriver(new MutableCapabilities());
        AppiumUtils appiumUtils = new AppiumUtils(appiumDriver);
        appiumUtils.waitAndClick(getStartedButton, Duration.ofSeconds(10));
        playwrightUtils.takeScreenshot("screenshot-by-playwright.png");
        appiumUtils.swipeDownAndRefreshPage();
        appiumUtils.swipeLeft();
        appiumUtils.swipeRight();
        appiumUtils.scrollByPercent(50);
    }
}
```

Other Utils Use in General Context

```java
package io.github.the_sdet;

import io.github.the_sdet.api.APIUtils;
import io.github.the_sdet.db.DatabaseUtils;
import io.github.the_sdet.json.JSONUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.the_sdet.excel.ExcelUtils.*;
import static io.github.the_sdet.excel.ExcelUtils.readExcelSheet;

public class TestingOtherUtils {
    public static void main(String[] args) throws IOException {

        // Testing Database Utilities
        Connection connection;
        try {
            connection = DriverManager.getConnection("db.url");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DatabaseUtils databaseUtils = new DatabaseUtils(connection);
        List<List<String>> employeesData = databaseUtils.readDataFromDb("Select * from Employees");
        List<String> employeeOneData = databaseUtils.readSingleRowFromDb("Select * from Employees where Emp_Id=001");
        String ageOfEmployeeOne = databaseUtils.readSingleDataFromDb("Select Emp_Name from Employees where Emp_Id=001");
        List<String> employees = databaseUtils.readSingleColumnFromDb("Select Emp_Name from Employees");
        System.out.println(employeesData);
        System.out.println(employeeOneData);
        System.out.println(ageOfEmployeeOne);
        System.out.println(employees);


        // Testing Excel Utilities
        System.out.println(readExcelSheet("src/test/resources/file.xlsx", "sheet1"));
        System.out.println(getHeaderList("src/test/resources/file.xlsx", "sheet1"));
        System.out.println(getValuesOfRow("src/test/resources/file.xlsx", "sheet1", "Row 4", true));
        System.out.println(getValuesOfColumn("src/test/resources/file.xlsx", "sheet1", "Header 4", true));
        System.out.println(readExcelSheet("src/test/resources/file.xlsx", "sheet1"));
        System.out.println(readExcelSheet("src/test/resources/file.xlsx", "sheet1", true));


        //Testing Rest Assured Utils
        Response response1 = APIUtils.getRequest("url", ContentType.JSON);
        System.out.println(response1.statusCode());

        Map<String, String> headers = new HashMap<>();
        Response response2 = APIUtils.getRequest("url", headers, ContentType.JSON);
        System.out.println(response2.statusCode());

        Map<String, String> queryParams = new HashMap<>();
        Response response3 = APIUtils.getRequest("url", headers, queryParams, ContentType.JSON);
        System.out.println(response3.statusCode());

        Response response4 = APIUtils.postRequest("url", "request body", ContentType.JSON);
        System.out.println(response4.statusCode());

        Response response5 = APIUtils.putRequest("url", "request body", ContentType.JSON);
        System.out.println(response5.statusCode());

        Response response6 = APIUtils.deleteRequest("url", ContentType.JSON);
        System.out.println(response6.statusCode());


        //Testing JSON Utils
        String firstUserName = JSONUtils.getElementFromJsonString(response1.asString(), "$.data.user[0].name");
        System.out.println("First User's Name: " + firstUserName);
        List<String> allUserNames = JSONUtils.getElementsFromJsonString(response1.asString(), "$.data.user[.id");
        System.out.println("All Users: " + allUserNames);
    }
}
```

## Authors

<a href="https://github.com/the-sdet"><img align="center" src="https://github.githubassets.com/assets/GitHub-Mark-ea2971cee799.png" alt="pabitra-qa" height="40" width="40" /></a>
[@the-sdet](https://github.com/the-sdet)

<a href="https://github.com/the-sdet"><img align="center" src="https://github.githubassets.com/assets/GitHub-Mark-ea2971cee799.png" alt="pabitra-qa" height="40" width="40" /></a>
[@pabitra-qa](https://github.com/pabitra-qa)

## 🚀 About Me

I'm a dedicated and passionate Software Development Engineer in Test (SDET) trying to help the community in focusing in
building great automation frameworks rather than writing the same utilities again and again and again...

## Connect With Me

<a href="https://linkedin.com/in/pswain7"><img align="center" src="https://content.linkedin.com/content/dam/me/business/en-us/amp/brand-site/v2/bg/LI-Logo.svg.original.svg" alt="pabitra-qa" height="35"/></a>
&nbsp; <a href="https://pabitra-qa.github.io/"><img align="center" src="https://chromeenterprise.google/static/images/chrome-logo.svg" height="40" width="40"/></a>

## Feedback

If you have any feedback, please reach out to us at [contact.the.sdet@gmail.com](mailto:contact.the.sdet@gmail.com).

[//]: # (<img align="center" src="https://pabitra-qa.github.io/dp.png" width="200px"/>)