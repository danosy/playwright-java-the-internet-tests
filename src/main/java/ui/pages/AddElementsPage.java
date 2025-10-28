package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;

public class AddElementsPage extends BasePage {
    private final String addElementButton = "button[onclick='addElement()']";
    private final String deleteElementButton = "button[onclick='deleteElement()']";

    @Inject
    public AddElementsPage(Page page) {
        super(page);
    }

    public void clickOnAddElementButton() {
        page.click(addElementButton);
    }

    public void clickOnDeleteElementButton() {
        page.click(deleteElementButton);
    }

    public boolean isDeleteButtonVisible() {
        return page.locator(deleteElementButton).isVisible();
    }
}
