package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DragAndDropPage extends BasePage {
    private final Locator boxA;
    private final Locator boxB;

    @Inject
    public DragAndDropPage(Page page) {
        super(page);
        this.boxA = page.locator("#column-a");
        this.boxB = page.locator("#column-b");
    }

    public void dragAndDropAToB() {
        boxA.dragTo(boxB);
    }

    public String getBoxBTextContent() {
        return boxB.textContent();
    }
}
