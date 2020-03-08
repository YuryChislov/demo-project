package com.mobiquity.testing.webautomation.tests;

import com.mobiquity.testing.webautomation.base.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;

public class SuccessfulLogin extends TestBase {

    @Test(groups = {"successLogin", "login"}, description = "LOGIN page. Test successful login")
    public void testSuccessfulLogin() throws IOException {
        System.out.println("*** LOGIN. Successful ***");
        System.out.println("Step 1: Check login page is uploaded");
        cafetownsendSite.loginPage().CheckLoginPageUploaded();
        System.out.println("Step 2: Insert login credentials: 'Luke'/'Skywalker'");
        cafetownsendSite.loginPage().insertLoginCredentials("Luke", "Skywalker");
        System.out.println("Step 3: click login button");
        cafetownsendSite.loginPage().clickLoginButton();
        System.out.println("Assertion: Check main page uploaded");
        cafetownsendSite.mainPage().checkMainPageUploaded();
        System.out.println("Take a screenshot of Login page");
        allureScreenshot("success_login");
    }
}
