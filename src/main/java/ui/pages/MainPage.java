package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainPage extends BasePage {
    private final Locator checkboxesLink;

    @Inject
    public MainPage(Page page) {
        super(page);
        this.checkboxesLink = page.locator("a[href='/checkboxes']");
    }

    public void clickOnCheckboxesLink() {
        checkboxesLink.click();
    }
}
