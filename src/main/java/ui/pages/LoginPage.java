package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    private final String usernameInput = "#username";
    private final String passwordInput = "#password";
    private final String loginButton = "button[type='submit']";
    private final String message = "#flash";
    //It's better to be as specific as it can be to avoid issues if the page structure is changed.
    private final String secureAreaHeader = "h2:nth-of-type(1)";
    private final String loginPageHeader = "h2:nth-of-type(1)";

    private final String logoutButton = "a[href='/logout']";

    @Inject
    private LoginPage(Page page) {
        super(page);
    }

    public void login(String username, String password) {
        page.navigate("https://the-internet.herokuapp.com/login");
        fill(usernameInput, username);
        fill(passwordInput, password);
        click(loginButton);
    }

    public String getMessage() {
        return page.innerText(message);
    }

    public String getSecureAreaHeader() {
        return page.textContent(secureAreaHeader);
    }

    public String getLoginPageHeader() {
        return page.textContent(loginPageHeader);
    }

    public void logout() {
        page.click(logoutButton);
    }
}