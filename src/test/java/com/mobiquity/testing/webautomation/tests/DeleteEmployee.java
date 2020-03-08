package com.mobiquity.testing.webautomation.tests;

import com.mobiquity.testing.webautomation.base.TestBase;
import org.testng.annotations.Test;

public class DeleteEmployee extends TestBase {


    @Test(groups = {"deleteEmployee", "mainPage"}, description = "Main page. Test Delete Employee from the list", dependsOnGroups = "editEmployee")
    public void testDeleteEmployee() throws InterruptedException {
        System.out.println("*** Main page. Test Delete Employee from the list ***");
        System.out.println("Step: log in");
        cafetownsendSite.loginPage().logIn();
        System.out.println("Assertion: Check main page uploaded");
        cafetownsendSite.mainPage().checkMainPageUploaded();
        System.out.println("Assertion: Check Employee list is uploaded and has entries");
        cafetownsendSite.mainPage().checkEmployeeListVisible();
        System.out.println("Step: click on entry name which was created in createEmployee test");
        cafetownsendSite.mainPage().clickEntryName("ChangedFirstName_" + uniqueStamp + " ChangedLastName");
        System.out.println("Step: Click Delete button");
        cafetownsendSite.mainPage().clickDeleteButton();
        System.out.println("Step: confirm deletion");
        cafetownsendSite.mainPage().acceptConfirmationAlert();
        System.out.println("Step: click 'create' button and 'cancel' to refresh the list");
        cafetownsendSite.mainPage().clickCreateButton();
        cafetownsendSite.mainPage().clickCancelButton();
        System.out.println("Assertion: Check Employee list is uploaded");
        cafetownsendSite.mainPage().checkEmployeeListVisible();
        System.out.println("Step: Check deleting entry has been deleted");
        cafetownsendSite.mainPage().checkEntryNotExists("ChangedFirstName_" + uniqueStamp + " ChangedLastName");
    }
}
