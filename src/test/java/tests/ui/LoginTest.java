package tests.ui;

import ui.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.LoginPage;

public class LoginTest extends BaseTest {
    Logger log = LogManager.getLogger(LoginTest.class);
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        log.info("Setting up for login test");
        loginPage = getPageFactory().getLoginPage();
    }

    @Test
    public void testFailedLogin() {
        loginPage.login("wrongUsername", "WrongPassword");
        Assertions.assertEquals(Constants.usernameIsInvalidMessage, loginPage.getMessage());
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.login("tomsmith", "SuperSecretPassword!");
        Assertions.assertEquals(Constants.secureAreaHeader, loginPage.getSecureAreaHeader());
        Assertions.assertEquals(Constants.loginSuccessMessage, loginPage.getMessage());
    }

    @Test
    public void testLogout() {
        testSuccessfulLogin();
        loginPage.logout();
        Assertions.assertEquals(Constants.loginPageHeader, loginPage.getLoginPageHeader());
    }
}
