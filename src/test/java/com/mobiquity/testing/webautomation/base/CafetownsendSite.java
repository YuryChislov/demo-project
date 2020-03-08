package com.mobiquity.testing.webautomation.base;

import com.mobiquity.testing.webautomation.pageobjects.LoginPage;
import com.mobiquity.testing.webautomation.pageobjects.MainPage;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import java.util.List;

public class CafetownsendSite {

    public CafetownsendSite (WebDriver driver) {
        webDriver = driver;
    }
    //<editor-fold desc="List of site pages">
    public LoginPage loginPage() {
        return new LoginPage(webDriver);
    }
    public MainPage mainPage() {
        return new MainPage(webDriver);
    }
    //</editor-fold>

    WebDriver webDriver;


    //TEST DATA

    public static List<String> invalidPasswords = Arrays.asList(
        //<editor-fold desc="Invalid passwords validation list">
        //Super long password:
        "passsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",
        //try to break DB password:
        "http://cafetownsend-angular-rails.herokuapp.com/login/delete?name=file.txt;rm%20/",
        //multiple whiteSpaces
        "         ",
        //TAB
        "\t",
        //multiple TABs
        "\t\t",
        //return
        "\r",
        //multiple returns
        "\n\n",
        //new line
        "\n",
        //multiple new lines
        "\n\n",
        //SQL injection(1):
        "SELECT * FROM Users WHERE User_Name = 'Luke' or 'x'='x' AND Password = 'Something’ or ‘x’=’x’;",
        //SQL injection (2):
        "SELECT Password FROM Users WHERE Username = 'Luke';"
        //</editor-fold>
    );

    static List<String> criticalConsoleErrorsList = Arrays.asList(
        //<editor-fold desc="criticalConsoleErrorsList">
        "undefined",
        "not defined",
        "unknown",
        "server error",
        "uncaught",
        "'null' is not",
        "doesn’t support property",
        "Cannot read property 'length'"
        //</editor-fold>
    );
}




