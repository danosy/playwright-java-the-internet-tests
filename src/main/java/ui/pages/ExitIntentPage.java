package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ExitIntentPage extends BasePage{
    private final Locator outOfBoundsModal;
    private final Locator closeButton;

    @Inject
    public ExitIntentPage(Page page) {
        super(page);
        this.outOfBoundsModal = page.locator("#ouibounce-modal");
        this.closeButton = page.locator("#ouibounce-modal p:has-text('Close')");
    }

    public Locator getOutOfBoundsModal() {
        return outOfBoundsModal;
    }

    public void waitForModalToAppear() {
        outOfBoundsModal.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void waitForModalToBeHidden() {
        outOfBoundsModal.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

    public void clickOnClose() {
        closeButton.click();
    }
}
