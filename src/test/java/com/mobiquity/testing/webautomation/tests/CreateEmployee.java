package com.mobiquity.testing.webautomation.tests;

import com.mobiquity.testing.webautomation.base.TestBase;
import org.testng.annotations.Test;

public class CreateEmployee extends TestBase {

    @Test(groups = {"createEmployee", "mainPage"}, description = "Main page. Test Create Employee")
    public void testCreateEmployee() throws InterruptedException {
        System.out.println("*** Main page. Test Create Employee ***");
        System.out.println("Step: log in");
        cafetownsendSite.loginPage().logIn();
        System.out.println("Assertion: Check main page uploaded");
        cafetownsendSite.mainPage().checkMainPageUploaded();
        System.out.println("Step: click Create button");
        cafetownsendSite.mainPage().clickCreateButton();
        System.out.println("Step: fill out the form correctly");
        cafetownsendSite.mainPage().inputEmployeeData("TestName_" + uniqueStamp, "lastNameTest", "2020-01-01", "test@email");
        System.out.println("Step: Click Add button");
        cafetownsendSite.mainPage().clickAddButtonInCreateForm();
        System.out.println("Assertion: Check Employee list is uploaded and has entries");
        cafetownsendSite.mainPage().checkEmployeeListVisible();
        System.out.println("Assertion: Check Employee list contains added entry");
        cafetownsendSite.mainPage().checkEntryExists("TestName_" + uniqueStamp + " lastNameTest");
    }
}
