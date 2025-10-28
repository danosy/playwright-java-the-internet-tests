package tests.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import ui.pages.DragAndDropPage;
import ui.pages.IFramePage;

public class IFrameTest extends BaseTest {
    IFramePage iFramePage;
    Logger log = LogManager.getLogger(IFrameTest.class);

    @BeforeEach
    public void setUp() {
        log.info("Setting up for login test");
        this.iFramePage = getPageFactory().getIFramePage();
    }

    @Test
    public void validateTextInTextEditor() throws InterruptedException {
        iFramePage.navigate("https://the-internet.herokuapp.com/iframe");
        String textFromTextEditor = iFramePage.getTextFromTextEditor();
        Assert.assertEquals(textFromTextEditor, "Your content goes here.");
    }
}
