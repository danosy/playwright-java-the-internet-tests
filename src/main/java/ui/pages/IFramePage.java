package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.Utils;

import java.util.function.Predicate;

public class IFramePage extends BasePage {
    private final String iframe = "#mce_0_ifr";
    private final String textEditor = "#tinymce";

    @Inject
    public IFramePage(Page page) {
        super(page);
    }

    private FrameLocator getTextEditor() {
        return page.frameLocator(iframe);
    }

    public String getTextFromTextEditor() throws InterruptedException {
        Locator textArea = getTextEditor().locator(textEditor);
        Utils.poll(textArea::textContent, textNotEmpty(), 2500, 5);
        return textArea.textContent().trim();
    }

    private static Predicate<String> textNotEmpty() {
        return text -> text != null && !text.isEmpty();
    }
}
