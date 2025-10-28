package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ExitIntentPage extends BasePage{
    private final String outOfBoundsModal = "#ouibounce-modal";
    //In this example you can see that you can skip one element. you just need the end element. You don't even need another element. You can just go stright to it.
    private final String closeButton = "#ouibounce-modal p:has-text('Close')";

    @Inject
    public ExitIntentPage(Page page) {
        super(page);
    }

    public Locator getOutOfBoundsModal() {
        return page.locator(outOfBoundsModal);
    }

    public void waitForModalToAppear() {
        getOutOfBoundsModal().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public void waitForModalToBeHidden() {
        getOutOfBoundsModal().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
    }

    public void clickOnClose() {
        page.click(closeButton);
    }
}
