package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class EntryAdPage extends BasePage{
    private final Locator modal;
    private final Locator modalClose;

    @Inject
    public EntryAdPage(Page page) {
        super(page);
        this.modal = page.locator("#modal");
        this.modalClose = page.locator("#modal .modal-footer p:has-text('Close')");
    }

    public Locator getModal() {
        return modal;
    }

    public void waitForModalToBeHidden() {
        modal.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

    public void waitForModalToLoad() {
        modal.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void clickOnModalClose() {
        modalClose.click();
    }
}
