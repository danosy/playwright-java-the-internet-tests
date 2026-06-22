package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class AddElementsPage extends BasePage {
    private final Locator addElementButton;
    private final Locator deleteElementButton;

    @Inject
    public AddElementsPage(Page page) {
        super(page);
        this.addElementButton = page.locator("button[onclick='addElement()']");
        this.deleteElementButton = page.locator("button[onclick='deleteElement()']");
    }

    public void clickOnAddElementButton() {
        addElementButton.click();
    }

    public void clickOnDeleteElementButton() {
        deleteElementButton.click();
    }

    public boolean isDeleteButtonVisible() {
        return deleteElementButton.isVisible();
    }
}
