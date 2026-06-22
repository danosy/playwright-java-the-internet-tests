package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DropDownPage extends BasePage {
    private final Locator dropDown;

    @Inject
    public DropDownPage(Page page) {
        super(page);
        this.dropDown = page.locator("#dropdown");
    }

    public void selectOption(int optionNum) {
        dropDown.selectOption(String.valueOf(optionNum));
    }

    public String getSelectValue() {
        return dropDown.inputValue();
    }
}
