package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DragAndDropPage extends BasePage {
    private final String boxA = "#column-a";
    private final String boxB = "#column-b";

    @Inject
    public DragAndDropPage(Page page) {
        super(page);
    }

    public void dragAndDropAToB() {
        Locator boxALocator = page.locator(boxA);
        Locator boxBLocator = page.locator(boxB);

        boxALocator.dragTo(boxBLocator);
    }

    public String getBoxBTextContent() {
        return page.locator(boxB).textContent();
    }
}
