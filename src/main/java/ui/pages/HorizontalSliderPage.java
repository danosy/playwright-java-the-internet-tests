package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Page;

public class HorizontalSliderPage extends BasePage {
    private final String slider = "#content input[type='range']";
    private final String range = "#range";

    @Inject
    public HorizontalSliderPage(Page page) {
        super(page);
    }

    public void focusOnSlider() {
        page.locator(slider).focus();
    }

    public float getSliderValue() {
        return Float.parseFloat(page.locator(range).textContent().trim());
    }
}
