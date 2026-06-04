package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import tests.ui.base.BaseUITest;
import ui.pages.EntryAdPage;

public class EntryAdTest extends BaseUITest {
    Logger log = LogManager.getLogger(EntryAdTest.class);
    private EntryAdPage entryAdPage;

    @BeforeEach
    public void setUp() {
        log.info("Setting up for login test");
        entryAdPage = getPageFactory().getEntryAdPage();
    }

    @Test
    public void modalVisibilityTest() {
        entryAdPage.navigate("https://the-internet.herokuapp.com/entry_ad");
        entryAdPage.waitForModalToLoad();
        entryAdPage.clickOnModalClose();
        entryAdPage.waitForModalToBeHidden();
        Assert.assertTrue(entryAdPage.getModal().isHidden(), "Modal is not hidden");
    }
}
