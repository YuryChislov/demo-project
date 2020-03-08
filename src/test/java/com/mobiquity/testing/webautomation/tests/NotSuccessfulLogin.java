package com.mobiquity.testing.webautomation.tests;

import com.mobiquity.testing.webautomation.base.TestBase;
import org.testng.annotations.Test;
import java.io.IOException;
import static com.mobiquity.testing.webautomation.base.CafetownsendSite.invalidPasswords;

public class NotSuccessfulLogin extends TestBase {

    @Test(groups = {"login"}, description = "LOGIN page. Test login NOT successful if to enter incorrect password (different variations)")
    public void testNotSuccessfulLogin() throws IOException {
        System.out.println("*** LOGIN. NOT Successful ***");
        System.out.println("Step: Check login page is uploaded");
        cafetownsendSite.loginPage().CheckLoginPageUploaded();
        System.out.println("Step: Insert login credentials: correct login and different variations of incorrct password");
        for (int i = 0; i < invalidPasswords.size(); i++) {
            System.out.println("Step: Checking with a password: " + invalidPasswords.get(i));
            cafetownsendSite.loginPage().insertLoginCredentials("Luke", invalidPasswords.get(i));
            System.out.println("Step: click login button");
            cafetownsendSite.loginPage().clickLoginButton();
            System.out.println("Assertion: Check login unsuccessful");
            cafetownsendSite.loginPage().CheckLoginUnsuccessful();
            takeScreenshot("notSuccess_login_" + i);
        }
    }
}
