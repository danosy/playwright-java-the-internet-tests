package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator message;
    private final Locator secureAreaHeader;
    private final Locator loginPageHeader;
    private final Locator logoutButton;

    @Inject
    private LoginPage(Page page) {
        super(page);
        this.usernameInput = page.locator("#username");
        this.passwordInput = page.locator("#password");
        this.loginButton = page.locator("button[type='submit']");
        this.message = page.locator("#flash");
        this.secureAreaHeader = page.locator("h2:nth-of-type(1)");
        this.loginPageHeader = page.locator("h2:nth-of-type(1)");
        this.logoutButton = page.locator("a[href='/logout']");
    }

    public void login(String username, String password) {
        page.navigate("https://the-internet.herokuapp.com/login");
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
    }

    public String getMessage() {
        return message.innerText();
    }

    public String getSecureAreaHeader() {
        return secureAreaHeader.textContent();
    }

    public String getLoginPageHeader() {
        return loginPageHeader.textContent();
    }

    public void logout() {
        logoutButton.click();
    }
}
