package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class DynamicLoadingPage extends BasePage{
    private final Locator startButton;
    private final Locator finishContainer;

    @Inject
    public DynamicLoadingPage(Page page) {
        super(page);
        this.startButton = page.locator("#start > button");
        this.finishContainer = page.locator("#finish");
    }

    public void clickOnStart() {
        startButton.click();
    }

    public Locator getFinishContainer() {
        return finishContainer;
    }

    public String waitForFinishedText() {
        finishContainer.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return finishContainer.textContent().trim();
    }
}
