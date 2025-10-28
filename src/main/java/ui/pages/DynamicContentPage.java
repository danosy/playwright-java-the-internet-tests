package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DynamicContentPage extends BasePage {
    private final String imagesLocator = "img[src^='/img/avatars/Original-Facebook-Geek-Profile-Avatar']";

    @Inject
    public DynamicContentPage(Page page) {
        super(page);
    }

    public Locator getAllRandomImageSrc() {
        return page.locator(imagesLocator);
    }
}
