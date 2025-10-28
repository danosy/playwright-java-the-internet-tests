package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;

public class BrokenImagesPage extends BasePage {

    @Inject
    public BrokenImagesPage(Page page) {
        super(page);
    }

}
