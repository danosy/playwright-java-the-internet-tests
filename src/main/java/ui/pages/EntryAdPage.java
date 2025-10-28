package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class EntryAdPage extends BasePage{
    private final String modal = "#modal";
    private final String modalClose = "#modal .modal-footer p:has-text('Close')";

    @Inject
    public EntryAdPage(Page page) {
        super(page);
    }

    public Locator getModal() {
        return page.locator(modal);
    }

    public void waitForModalToBeHidden() {
        getModal().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

    public void waitForModalToLoad() {
        getModal().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void clickOnModalClose() {
        page.click(modalClose);
    }
}
