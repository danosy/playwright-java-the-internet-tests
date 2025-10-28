package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class DynamicLoadingPage extends BasePage{
    //For a button inside a div. The button doesn't have an id. But it's the only button.
    private final String startButton = "#start > button";
    private final String finishContainer = "#finish";

    @Inject
    public DynamicLoadingPage(Page page) {
        super(page);
    }

    public void clickOnStart() {
        page.click(startButton);
    }

    public Locator getFinishContainer() {
        return page.locator(finishContainer);
    }

    public String waitForFinishedText() {
        getFinishContainer().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        return getFinishContainer().textContent().trim();
    }
}
