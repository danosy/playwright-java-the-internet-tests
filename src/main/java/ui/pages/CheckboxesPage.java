package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckboxesPage extends BasePage{

    @Inject
    public CheckboxesPage(Page page) {
        super(page);
    }

    private Locator getCheckboxLocator(int number) {
        return page.locator(String.format("#checkboxes input[type='checkbox']:nth-of-type(%s)", number));
    }

    public void clickOnCheckbox(int number) {
        getCheckboxLocator(number).check();
    }

    public boolean isChecked(int number) {
        return getCheckboxLocator(number).isChecked();
    }
}
