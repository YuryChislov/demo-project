package com.mobiquity.testing.webautomation.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class LoginPage {
    private WebDriver webDriver;
    private WebDriverWait wait;
    private Actions action;
    public LoginPage(WebDriver driver) {
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);
        action = new Actions(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    //<editor-fold desc="Objects">
    @FindBy(xpath = "//input[contains(@ng-model,'user.name')]")
    WebElement userNameBox;
    @FindBy(xpath = "//input[contains(@ng-model,'user.password')]")
    WebElement passwordBox;
    @FindBy(xpath = "//button[@class='main-button']")
    WebElement loginBatton;
    @FindBy (xpath = "//p[text()='Invalid username or password!']")
    WebElement errorMessageLogin;
    //</editor-fold>

    //<editor-fold desc="Actions">
    @Step("Login with {username} and {password}")
    public void insertLoginCredentials(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(userNameBox));
        wait.until(ExpectedConditions.visibilityOf(passwordBox));
        userNameBox.clear();
        userNameBox.sendKeys(username);
        passwordBox.clear();
        passwordBox.sendKeys(password);

    }

    @Step("Click login button")
    public  void clickLoginButton(){
        wait.until(ExpectedConditions.elementToBeClickable(loginBatton));
        loginBatton.click();
    }

    @Step("Log in")
    public  void logIn(){
        CheckLoginPageUploaded();
        insertLoginCredentials("Luke", "Skywalker");
        clickLoginButton();
    }
    //</editor-fold>

    //<editor-fold desc="Checkers">
    @Step("Check Login Page uploaded")
    public void CheckLoginPageUploaded() {
        wait.until(ExpectedConditions.urlToBe("http://cafetownsend-angular-rails.herokuapp.com/login"));
        System.out.println("\u001B[32m Expected result obtained\u001B[0m");
    }

    @Step("Check login unsuccessful")
    public void CheckLoginUnsuccessful() {
        Assert.assertEquals(webDriver.getCurrentUrl(), "http://cafetownsend-angular-rails.herokuapp.com/login");
        wait.until(ExpectedConditions.visibilityOf(errorMessageLogin));
        System.out.println("\u001B[32m Expected result obtained\u001B[0m");
    }

    //</editor-fold>


}
