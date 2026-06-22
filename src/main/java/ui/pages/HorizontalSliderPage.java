package ui.pages;

import com.google.inject.Inject;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HorizontalSliderPage extends BasePage {
    private final Locator slider;
    private final Locator range;

    @Inject
    public HorizontalSliderPage(Page page) {
        super(page);
        this.slider = page.locator("#content input[type='range']");
        this.range = page.locator("#range");
    }

    public void focusOnSlider() {
        slider.focus();
    }

    public float getSliderValue() {
        return Float.parseFloat(range.textContent().trim());
    }
}
