package ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import ui.constants.ArrowKeys;
import ui.constants.Constants;

public abstract class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected Locator locator(String selector) {
        return page.locator(selector);
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void reload() {
        page.reload();
    }

    public void moveMouseTo(int x, int y) {
        page.mouse().move(x, y);
    }

    public void clickKeyboardArrowKey(ArrowKeys arrowKey) {
        page.keyboard().press(Constants.arrowKeysStringMap.get(arrowKey));
    }
}