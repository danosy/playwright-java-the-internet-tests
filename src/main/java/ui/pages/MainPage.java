package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;

public class MainPage extends BasePage {
    private final String checkboxesLink = "a[href='/checkboxes']";

    @Inject
    public MainPage(Page page) {
        super(page);
    }

    public void clickOnCheckboxesLink() {
        page.click(checkboxesLink);
    }
}
