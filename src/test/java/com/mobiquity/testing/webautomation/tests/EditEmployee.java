package com.mobiquity.testing.webautomation.tests;

import com.mobiquity.testing.webautomation.base.TestBase;
import org.testng.annotations.Test;

public class EditEmployee extends TestBase {


    @Test(groups = {"editEmployee", "mainPage"}, description = "Main page. Test Edit Employee from the list", dependsOnGroups = "createEmployee")
    public void testEditEmployee() throws InterruptedException {
        System.out.println("*** Main page. Test Edit Employee from the list ***");
        System.out.println("Step: log in");
        cafetownsendSite.loginPage().logIn();
        System.out.println("Assertion: Check main page uploaded");
        cafetownsendSite.mainPage().checkMainPageUploaded();
        System.out.println("Assertion: Check Employee list is uploaded and has entries");
        cafetownsendSite.mainPage().checkEmployeeListVisible();
        System.out.println("Step: click on entry name which was created in createEmployee test");
        cafetownsendSite.mainPage().clickEntryName("TestName_" + uniqueStamp + " lastNameTest");
        System.out.println("Step: Click Edit button");
        cafetownsendSite.mainPage().clickEditButton();
        System.out.println("Step: Replace 'First Name' and 'Last Name' in form");
        cafetownsendSite.mainPage().inputEmployeeFirstNameInForm("ChangedFirstName_" + uniqueStamp);
        cafetownsendSite.mainPage().inputEmployeeLastNameInForm("ChangedLastName");
        System.out.println("Step: click Update button");
        cafetownsendSite.mainPage().clickUpdateButtonInEditForm();
        System.out.println("Assertion: Check Employee list is uploaded");
        cafetownsendSite.mainPage().checkEmployeeListVisible();
        System.out.println("Step: Check editing entry has been changed");
        cafetownsendSite.mainPage().checkEntryExists("ChangedFirstName_" + uniqueStamp + " ChangedLastName");
    }
}
