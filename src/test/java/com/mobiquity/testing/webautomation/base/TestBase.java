package com.mobiquity.testing.webautomation.base;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Scanner;

public class TestBase {
    public static String website;
    public static WebDriver webDriver;
    static Properties property;
    public CafetownsendSite cafetownsendSite;
    private WebDriverWait wait;
    FileInputStream fs;
    public static String snapBrowserName; //this uses for screenshots and to detect what browser is being used in some methods
    public static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); //this uses for screenshots and error logs
    public static String testName; //this uses for screenshots and error logs
    public static boolean setUpInitialized = false; //this flags if test set up was initialized
    public static String uniqueStamp; //this String is initialised in setUp.It is used in a few dependent test cases during test suite execution



    //This method makes SCREENSHOT to AutoScreenshots folder
    public static void takeScreenshot(String snapName) throws IOException {
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(property.getProperty("screenshotsPath") + snapName + "_" + timeStamp + "_" + snapBrowserName + ".png"));
    }

    @BeforeClass(alwaysRun = true, description = "Starting and setting up web browser with selenium driver")
    @Parameters({"browser", "site"})
    @Step("Open {site} with {browser}")
    public void setUp(String browser, String site) throws IOException {
        //this line initialise the unique and static time stamp which is used in a few dependent test cases during test suite execution
        uniqueStamp = timeStamp;
        fs = new FileInputStream(System.getProperty("user.dir") + "/local.properties");
        property = new Properties();
        property.load(fs);
        website = site;
        setUpInitialized = true;
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", property.getProperty("fireFoxDriverBinaryPath"));
            System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
            webDriver = new FirefoxDriver();
            snapBrowserName = "firefox";
        } else if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", property.getProperty("internetExplorerDriverBinaryPath"));
            snapBrowserName = "IE";
            //DesiredCapabilities caps = DesiredCapabilities.htmlUnit();
            DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
            caps.setCapability("ignoreZoomSetting", true);
            caps.setCapability("nativeEvents", true);
            caps.setCapability("ie.ensureCleanSession", true);
            webDriver = new InternetExplorerDriver(caps);
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", property.getProperty("chromeDriverBinaryPath"));
            snapBrowserName = "chrome";
            webDriver = new ChromeDriver();
        }
        wait = new WebDriverWait(webDriver, 30);
        cafetownsendSite = new CafetownsendSite(webDriver);
        webDriver.manage().window().maximize();
        webDriver.get(website);
        System.out.println(webDriver.getTitle());
    }

    @AfterMethod(alwaysRun = true, description = "Check If failed then to make extra screenshot")
    //Takes screenshot if failed
    public void checkIfFailed(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus() && !result.getThrowable().getMessage().equals("\u001B[41m***Forced failure after try/catch block***\u001B[0m")) {
            try {
                allureScreenshot("!ERROR!_" + result.getName());
                System.out.println("\u001B[31mSuccessfully captured a screenshot in failure case\u001B[0m");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
        //This defines test name for logging and screenshot names
        testName = result.getName();
    }

    @AfterClass(alwaysRun = true, description = "close web browser, checks Errors in browser console")
    @Parameters({"browser"})
    public void tearDown(ITestResult result, String browser) {
        //Checking all the console Error messages in Chrome and adding them in ErrorLogs.txt
        if (browser.equals("chrome")) {
            for (LogEntry entry : webDriver.manage().logs().get(LogType.BROWSER)) {
                String msg = entry.getMessage();
                if (msg.length() != 0) {
                File log = new File(property.getProperty("screenshotsPath") + "!ERROR_Logs.csv");
                    try {
                        FileWriter fileWriter = new FileWriter(log, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write("------" + timeStamp + "------" + testName + msg + "\n");
                        bufferedWriter.close();
                    } catch (IOException e) {
                        System.out.println("COULD NOT LOG!!");
                    }
                }
                System.err.println("ERROR MESSAGE DETECTED: " + msg);
            }
        }
        //Checking if possible to close browser with no error
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @AfterSuite(alwaysRun = true, description = "Checks If browser console had a critical errors")
    public static void main() {
        if (setUpInitialized) {
            String errorNotification;
            boolean hasPotentialCriticalErrors = false;
            File file = new File(property.getProperty("screenshotsPath") + "!ERROR_Logs.csv");
            try {
                Scanner scanner = new Scanner(file);
                //now read the file line by line...
                int lineNum = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    lineNum++;
                    for (int i = 0; i < CafetownsendSite.criticalConsoleErrorsList.size(); i++) {
                        if (line.contains(CafetownsendSite.criticalConsoleErrorsList.get(i))) {
                            hasPotentialCriticalErrors = true;
                            String alertMessage = "*_ATTENTION!!!_* Detected concole ERRORS that might be critical:";
                            System.out.println("\u001B[41m" + alertMessage+ "\u001B[0m");
                            //Error notification potentially might be used in Email or Slack notifications
                            errorNotification = lineNum + "--" + line + "--" + CafetownsendSite.criticalConsoleErrorsList.get(i) + "-- found text: " + CafetownsendSite.criticalConsoleErrorsList.get(i);
                            System.out.println(errorNotification);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("***!ERROR_Logs file not found. This means no console error was detected!***");
            }
            if (hasPotentialCriticalErrors) {
                Assert.fail("has potential critical errors! \nPlease Review " + property.getProperty("screenshotsPath") + "!ERROR_Logs.csv");
            }
        } else {
            System.out.println("'setUp' method was not invoked. Check if you specified at least one test ro run");
        }
    }

    @Attachment(value = "{attachmentName}", type = "image/png")
    public static byte[] allureScreenshot(String attachmentName) throws IOException {
        takeScreenshot(attachmentName);
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);

    }

    @Step ("Scroll page to webElement {webElement}")
    public void scrollToWebElement(WebElement webElement){
        ((JavascriptExecutor)
            webDriver).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }
}



