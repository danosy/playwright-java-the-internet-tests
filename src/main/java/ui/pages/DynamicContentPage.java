package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DynamicContentPage extends BasePage {
    private final Locator imagesLocator;

    @Inject
    public DynamicContentPage(Page page) {
        super(page);
        this.imagesLocator = page.locator("img[src^='/img/avatars/Original-Facebook-Geek-Profile-Avatar']");
    }

    public Locator getAllRandomImageSrc() {
        return imagesLocator;
    }
}
