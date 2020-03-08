package com.mobiquity.testing.webautomation.pageobjects;

import com.mobiquity.testing.webautomation.base.TestBase;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class MainPage extends TestBase {
    private WebDriver webDriver;
    private WebDriverWait wait;
    public MainPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);
        PageFactory.initElements(webDriver, this);
    }

    //<editor-fold desc="Objects">
    @FindBy(id = "greetings")
    public WebElement greetings;
    @FindBy(xpath = "//p[@ng-click='logout()']")
    public WebElement logoutButton;
    @FindBy(id = "bAdd")
    public WebElement createButton;
    @FindBy(id = "bEdit")
    public WebElement editButton;
    @FindBy(id = "bDelete")
    public WebElement deleteButton;
    @FindBy(xpath = "//li[@ng-repeat='employee in employees']")
    List<WebElement> entriesFromEmployeeList;
    @FindBy(id = "employee-list")
    WebElement employeeListArea;

    //<editor-fold desc="Actions upon elements from create form">
    @FindBy(xpath = "//a[@class='subButton bCancel']")
    WebElement cancelButtonOfCreateForm;
    @FindBy(xpath = "//button[text()='Add']")
    WebElement addButtonCreateForm;
    //</editor-fold>

    //<editor-fold desc="Action elements from edit form">
    @FindBy(id = "//a[@class='subButton bBack']")
    WebElement backButtonOfEditForm;
    @FindBy(xpath = "//button[text()='Update']")
    WebElement updateButtonEditForm;
    @FindBy(xpath = "//p[@ng-click='deleteEmployee()']")
    WebElement deleteButtonEditForm;
    //</editor-fold>

    //<editor-fold desc="Employee input elements for create and delete forms">
    @FindBy(xpath = "//input[@ng-model='selectedEmployee.firstName']")
    WebElement firstNameEmployeeForm;
    @FindBy(xpath = "//input[@ng-model='selectedEmployee.lastName']")
    WebElement lastNameEmployeeForm;
    @FindBy(xpath = "//input[@ng-model='selectedEmployee.startDate']")
    WebElement startDateEmployeeForm;
    @FindBy(xpath = "//input[@ng-model='selectedEmployee.email']")
    WebElement emailEmployeeForm;
    //</editor-fold>

    //</editor-fold>

    //<editor-fold desc="Actions">
    @Step("Click Create button")
    public void clickCreateButton(){
        wait.until(ExpectedConditions.visibilityOf(createButton));
        createButton.click();
    }

    @Step("Click Create button")
    public void clickCancelButton(){
        wait.until(ExpectedConditions.visibilityOf(cancelButtonOfCreateForm));
        cancelButtonOfCreateForm.click();
    }

    @Step("Input employee {firstName} Employee form:")
    public void inputEmployeeFirstNameInForm(String firstName){
        wait.until(ExpectedConditions.visibilityOf(firstNameEmployeeForm));
        firstNameEmployeeForm.clear();
        firstNameEmployeeForm.sendKeys(firstName);
    }

    @Step("Input employee {lastName} Employee form:")
    public void inputEmployeeLastNameInForm(String lastName){
        wait.until(ExpectedConditions.visibilityOf(lastNameEmployeeForm));
        lastNameEmployeeForm.clear();
        lastNameEmployeeForm.sendKeys(lastName);
    }

    @Step("Input employee {startDate} Employee form:")
    public void inputEmployeeStartDateInForm(String startDate){
        wait.until(ExpectedConditions.visibilityOf(startDateEmployeeForm));
        startDateEmployeeForm.clear();
        startDateEmployeeForm.sendKeys(startDate);
    }

    @Step("Input employee {email} Employee form:")
    public void inputEmployeeEmailInForm(String email){
        wait.until(ExpectedConditions.visibilityOf(emailEmployeeForm));
        emailEmployeeForm.clear();
        emailEmployeeForm.sendKeys(email);
    }

    @Step("Input following data in Employee form: {firstName}, {lastName}, {startDate}, {email}")
    public void inputEmployeeData(String firstName, String lastName, String startDate, String email){
        wait.until(ExpectedConditions.visibilityOf(firstNameEmployeeForm));
        inputEmployeeFirstNameInForm(firstName);
        inputEmployeeLastNameInForm(lastName);
        inputEmployeeStartDateInForm(startDate);
        inputEmployeeEmailInForm(email);
        System.out.println("\u001B[32m " +
            "Form populated: \u001B[0m" +
            "\nFirst Name: " +  firstName +
            "\nLast Name: " + lastName +
            "\nStart Date: " + startDate +
            "\nEmail: " + email
        );
    }

    @Step("Submit Create form")
    public void clickAddButtonInCreateForm(){
        wait.until(ExpectedConditions.visibilityOf(addButtonCreateForm));
        addButtonCreateForm.click();
}

    @Step("Click on entry with name {entryName} from employee list")
    public void clickUpdateButtonInEditForm(){
        wait.until(ExpectedConditions.visibilityOf(updateButtonEditForm));
        updateButtonEditForm.click();
    }

    @Step("Click on entry with name {entryName} from employee list")
    public void clickEntryName(String name) {
        boolean success = false;
        new Actions(webDriver).moveToElement(employeeListArea).perform();
        for (int i = 0; i < entriesFromEmployeeList.size() && !success; i++) {
            if (entriesFromEmployeeList.get(i).getText().equals(name)) {
                //new Actions(webDriver).moveToElement(entriesFromEmployeeList.get(i)).perform();
                scrollToWebElement(entriesFromEmployeeList.get(i));
                entriesFromEmployeeList.get(i).click();
                success = true;
            }
        }
    }

    @Step("Submit Edit button")
    public void clickEditButton(){
        wait.until(ExpectedConditions.visibilityOf(editButton));
        editButton.click();
    }

    @Step("Submit Delete button")
    public void clickDeleteButton(){
        wait.until(ExpectedConditions.visibilityOf(deleteButton));
        deleteButton.click();
    }

    @Step("Accept confirmation alert")
    public void acceptConfirmationAlert() throws InterruptedException {
        Thread.sleep(1000);
        //new Actions(webDriver).sendKeys("Keys.RETURN");
        webDriver.switchTo().alert().accept();

    }

    //</editor-fold>

    //<editor-fold desc="Checkers">
    @Step ("Check employee list is visible and has at least one entry")
    public void checkEmployeeListVisible() throws InterruptedException {
        boolean success = false;
        for (int i = 0; i < 100 && !success; i++) {
            try {
                Assert.assertTrue(entriesFromEmployeeList.size() > 0);
                success = true ;
                System.out.println("\u001B[32m Expected result obtained\u001B[0m");
            } catch (AssertionError e) {
                Thread.sleep(100);
            }
        }
    }

    @Step("Check if entry {name} exists in employee list")
    public void checkEntryExists(String name) {
        boolean entryExists = false;
        for (int i = 0; i < entriesFromEmployeeList.size() && !entryExists; i++){
            if (entriesFromEmployeeList.get(i).getText().equals(name))
                entryExists = true;
        }
        if (entryExists) {
            System.out.println("\u001B[32m Expected result obtained\u001B[0m");
        } else {
            Assert.fail("Entree does not exists!");
        }
    }

    @Step("Check if entry {name} does NOT exists in employee list")
    public void checkEntryNotExists(String name) {
        boolean entryExists = false;
        for (int i = 1; i < entriesFromEmployeeList.size(); i++){
            if (entriesFromEmployeeList.get(i).getText().equals(name))
                entryExists = true;
        }
        if (!entryExists) {
            System.out.println("\u001B[32m Expected result obtained\u001B[0m");
        } else {
            Assert.fail("Entree DOES exists!");
        }
    }

    @Step("Check Main Page uploaded")
    public void checkMainPageUploaded() {
        wait.until(ExpectedConditions.urlToBe("http://cafetownsend-angular-rails.herokuapp.com/employees"));
        System.out.println("\u001B[32m Expected result obtained\u001B[0m");
    }

    //</editor-fold>
}
