package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;

public class CheckboxesPage extends BasePage{
    private final String checkbox = "#checkboxes input[type='checkbox']:nth-of-type(%s)";

    @Inject
    public CheckboxesPage(Page page) {
        super(page);
    }

    public void clickOnCheckbox(int number) {
        page.locator(String.format(checkbox, number)).check();
    }

    public boolean isChecked(int number) {
        return page.isChecked(String.format(checkbox, number));
    }
}
