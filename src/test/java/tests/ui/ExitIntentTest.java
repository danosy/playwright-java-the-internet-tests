package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import tests.ui.base.BaseUITest;
import ui.pages.ExitIntentPage;

public class ExitIntentTest extends BaseUITest {
    Logger log = LogManager.getLogger(ExitIntentTest.class);
    private ExitIntentPage exitIntentPage;

    @BeforeEach
    public void setUp() {
        log.info("Setting up for login test");
        exitIntentPage = getPageFactory().getExitIntentPage();
    }

    @Test
    public void validateModalPopsWhenMouseIsOutOfBounds() {
        exitIntentPage.navigate("https://the-internet.herokuapp.com/exit_intent");
        exitIntentPage.moveMouseTo(0, 0);
        exitIntentPage.moveMouseTo(0, -10);
        exitIntentPage.waitForModalToAppear();
        exitIntentPage.clickOnClose();
        exitIntentPage.waitForModalToBeHidden();
        Assert.assertTrue(exitIntentPage.getOutOfBoundsModal().isHidden(), "Modal not hidden");
    }
}
