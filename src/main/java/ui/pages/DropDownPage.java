package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;

public class DropDownPage extends BasePage {
    private final String dropDown = "#dropdown";

    @Inject
    public DropDownPage(Page page) {
        super(page);
    }

    public void selectOption(int optionNum) {
        page.selectOption(dropDown, String.valueOf(optionNum));
    }

    public String getSelectValue() {
        return page.inputValue(dropDown);
    }
}
