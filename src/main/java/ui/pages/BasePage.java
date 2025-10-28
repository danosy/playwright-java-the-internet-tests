package ui.pages;

import com.microsoft.playwright.Page;
import ui.constants.ArrowKeys;
import ui.constants.Constants;

public class BasePage {
    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void click(String selector) {
        page.click(selector);
    }

    public void fill(String selector, String value) {
        page.fill(selector, value);
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
