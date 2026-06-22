package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.Utils;

import java.util.function.Predicate;

public class IFramePage extends BasePage {
    private final String iframeSelector = "#mce_0_ifr";
    private final Locator textEditor;

    @Inject
    public IFramePage(Page page) {
        super(page);
        this.textEditor = page.frameLocator(iframeSelector).locator("#tinymce");
    }

    public String getTextFromTextEditor() throws InterruptedException {
        Utils.poll(textEditor::textContent, textNotEmpty(), 2500, 5);
        return textEditor.textContent().trim();
    }

    private static Predicate<String> textNotEmpty() {
        return text -> text != null && !text.isEmpty();
    }
}
