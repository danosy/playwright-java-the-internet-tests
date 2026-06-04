package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import config.TestConfig;
import tests.ui.base.BaseUITest;
import ui.constants.ArrowKeys;
import ui.pages.HorizontalSliderPage;

public class HorizontalSliderTest extends BaseUITest {
    private HorizontalSliderPage horizontalSliderPage;
    Logger log = LogManager.getLogger(AddElementsTest.class);

    @BeforeEach
    public void setUp() {
        log.info("Setting up for login test");
        this.horizontalSliderPage = getPageFactory().getHorizontalSliderPage();
    }

    @Test
    void moveSliderUpValidation() {
        horizontalSliderPage.navigate(TestConfig.getBaseUIUrl() + "/horizontal_slider");
        horizontalSliderPage.focusOnSlider();
        horizontalSliderPage.clickKeyboardArrowKey(ArrowKeys.UP);
        log.info(horizontalSliderPage.getSliderValue());
        Assert.assertEquals(horizontalSliderPage.getSliderValue(), 0.5);
    }
}
